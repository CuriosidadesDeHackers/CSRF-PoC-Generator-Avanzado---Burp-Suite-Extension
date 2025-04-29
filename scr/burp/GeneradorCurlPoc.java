package com.curiosidadesdehackers.burp;

import java.util.Map;

public class GeneradorCurlPoc {
    public String generar(AnalizadorPeticion analizador) {
        StringBuilder curlPoc = new StringBuilder();

        curlPoc.append("# Generado por CSRF POC Avanzado de Curiosidades De Hackers\n");
        curlPoc.append("curl -X ").append(analizador.getMetodo()).append(" '").append(analizador.getUrl()).append("' \\\n");

        // Añadir cabeceras
        for (Map.Entry<String, String> entry : analizador.getCabeceras().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("Content-Length")) {
                curlPoc.append("  -H '").append(entry.getKey()).append(": ").append(entry.getValue()).append("' \\\n");
            }
        }

        // Añadir cuerpo si es necesario
        if (!analizador.getParametros().isEmpty()) {
            StringBuilder cuerpo = new StringBuilder();
            for (Map.Entry<String, String> entry : analizador.getParametros().entrySet()) {
                if (cuerpo.length() > 0) cuerpo.append("&");
                cuerpo.append(entry.getKey()).append("=").append(entry.getValue());
            }
            curlPoc.append("  --data '").append(cuerpo.toString()).append("'");
        }

        return curlPoc.toString();
    }
}
