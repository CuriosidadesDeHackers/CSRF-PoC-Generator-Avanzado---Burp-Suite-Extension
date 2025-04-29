package com.curiosidadesdehackers.burp;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorPeticion {
    private String metodo;
    private String url;
    private Map<String, String> parametros;
    private Map<String, String> cabeceras;
    private String cuerpo;

    public AnalizadorPeticion(String peticionHttp) throws Exception {
        this.parametros = new HashMap<>();
        this.cabeceras = new HashMap<>();
        parsearPeticion(peticionHttp);
    }

    private void parsearPeticion(String peticion) throws Exception {
        String[] lineas = peticion.split("\n");

        // Primera línea: Método y URL
        String primeraLinea = lineas[0].trim();
        String[] partesPrimeraLinea = primeraLinea.split(" ");
        if (partesPrimeraLinea.length < 2) {
            throw new Exception("Formato de petición HTTP inválido");
        }

        this.metodo = partesPrimeraLinea[0];
        this.url = partesPrimeraLinea[1];

        // Cabeceras
        int i = 1;
        for (; i < lineas.length; i++) {
            String linea = lineas[i].trim();
            if (linea.isEmpty()) break; // Fin de cabeceras

            int separador = linea.indexOf(':');
            if (separador > 0) {
                String nombre = linea.substring(0, separador).trim();
                String valor = linea.substring(separador + 1).trim();
                cabeceras.put(nombre, valor);
            }
        }

        // Asegurarse de que el host esté presente
        if (!cabeceras.containsKey("Host")) {
            cabeceras.put("Host", new URL("https://" + url).getHost());
        }

        // Cuerpo (si existe)
        StringBuilder cuerpoBuilder = new StringBuilder();
        for (i++; i < lineas.length; i++) {
            cuerpoBuilder.append(lineas[i]).append("\n");
        }
        this.cuerpo = cuerpoBuilder.toString().trim();

        // Parsear parámetros del cuerpo
        if (this.cuerpo.contains("=")) {
            String[] pares = this.cuerpo.split("&");
            for (String par : pares) {
                String[] keyValue = par.split("=", 2);
                if (keyValue.length == 2) {
                    parametros.put(keyValue[0], keyValue[1]);
                } else {
                    parametros.put(keyValue[0], "");
                }
            }
        }
    }

    // Getters
    public String getMetodo() { return metodo; }
    public String getUrl() { return "http://" + cabeceras.get("Host") + url; }
    public Map<String, String> getParametros() { return parametros; }
    public Map<String, String> getCabeceras() { return cabeceras; }
    public String getCuerpo() { return cuerpo; }
}
