package com.curiosidadesdehackers.burp;

import java.util.Map;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class GeneradorHtmlPoc {
    private static final Pattern HTTP_PATTERN = Pattern.compile("^http://", Pattern.CASE_INSENSITIVE);

    public String generar(AnalizadorPeticion analizador) {
        StringBuilder htmlPoc = new StringBuilder();

        // Forzar HTTPS en la URL
        String urlSegura = forzarHttps(analizador.getUrl());

        // Estructura HTML con formato perfecto
        htmlPoc.append("<!-- Generado por CSRF PoC de Curiosidades De Hackers -->\n");
        htmlPoc.append("<html>\n");
        htmlPoc.append("  <body>\n");
        htmlPoc.append("    <form action=\"").append(urlSegura)
              .append("\" method=\"").append(analizador.getMetodo()).append("\">\n");

        // Parámetros con indentación exacta
        for (Map.Entry<String, String> entry : analizador.getParametros().entrySet()) {
            htmlPoc.append("      <input type=\"hidden\" name=\"")
                  .append(entry.getKey())
                  .append("\" value=\"")
                  .append(encodeFullValue(entry.getValue()))
                  .append("\" />\n");
        }

        // Cierre del formulario con formato consistente
        htmlPoc.append("      <input type=\"submit\" value=\"Submit request\" />\n");
        htmlPoc.append("    </form>\n");
        htmlPoc.append("  </body>\n");
        htmlPoc.append("</html>");

        return htmlPoc.toString();
    }

    private String forzarHttps(String url) {
        if (url == null) return "";
        // Reemplazar http:// por https:// manteniendo el resto de la URL
        return HTTP_PATTERN.matcher(url).replaceFirst("https://");
    }

    private String encodeFullValue(String input) {
        if (input == null) return "";
        
        try {
            String decoded = URLDecoder.decode(input, StandardCharsets.UTF_8.name());
            StringBuilder encoded = new StringBuilder();
            
            for (char c : decoded.toCharArray()) {
                switch (c) {
                    case '&': 
                        if (!isHtmlEntity(decoded, encoded.length())) {
                            encoded.append("&amp;");
                        } else {
                            encoded.append(c);
                        }
                        break;
                    case '<': encoded.append("&lt;"); break;
                    case '>': encoded.append("&gt;"); break;
                    case '"': encoded.append("&quot;"); break;
                    case '\'': encoded.append("&#39;"); break;
                    case ' ': encoded.append("&#32;"); break;
                    case '=': encoded.append("&#61;"); break;
                    case '(': encoded.append("&#40;"); break;
                    case ')': encoded.append("&#41;"); break;
                    case '/': encoded.append("&#47;"); break;
                    case '.': encoded.append("&#46;"); break;
                    case ':': encoded.append("&#58;"); break;
                    case ';': encoded.append("&#59;"); break;
                    default: 
                        if (c > 127) {
                            encoded.append("&#").append((int)c).append(";");
                        } else {
                            encoded.append(c);
                        }
                }
            }
            return encoded.toString();
        } catch (Exception e) {
            return input;
        }
    }

    private boolean isHtmlEntity(String str, int pos) {
        if (pos >= str.length() - 1) return false;
        String remaining = str.substring(pos);
        return remaining.startsWith("&amp;") || 
               remaining.startsWith("&lt;") || 
               remaining.startsWith("&gt;") || 
               remaining.startsWith("&quot;") || 
               remaining.startsWith("&#");
    }
}
