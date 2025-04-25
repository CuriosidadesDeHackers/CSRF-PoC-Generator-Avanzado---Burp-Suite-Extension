# CSRF PoC Generator Avanzado - Burp Suite Extension  

![Burp Suite](https://img.shields.io/badge/Burp_Suite-Compatible-green)  
![Versión](https://img.shields.io/badge/Versión-1.0-blue)  
![Licencia](https://img.shields.io/badge/Licencia-MIT-red)  

## 🌟 Descripción Avanzada  
**CSRF PoC Generator Avanzado** es una extensión profesional para Burp Suite diseñada para automatizar la creación de Proofs of Concept (PoC) sofisticados para vulnerabilidades CSRF (Cross-Site Request Forgery), incluyendo casos complejos con:  

- Parámetros con encoding especial  
- Headers personalizados  
- Tokens CSRF bypass  
- Payloads XSS combinados  

## 🎯 Público Objetivo  
- **Pentesters profesionales** que necesitan demostrar impactos reales  
- **Equipos Red Team** en ejercicios de simulación de ataques  
- **Auditores de seguridad** que requieren informes técnicos detallados  
- **Developers de aplicaciones críticas** que validan protecciones anti-CSRF  

## 🔥 Features Técnicas  

### 🛠️ Generación Automatizada  
```html
<!-- Generado por CSRF PoC de Curiosidades De Hackers -->
<html>
  <body>
    <form action="https://ejemplo.com/index.com" method="POST">
      <input type="hidden" name="subemail" value="test@gmail&#46;com" />
      <input type="hidden" name="subname" value="" />
      <input type="hidden" name="subscribe.y" value="3" />
      <input type="hidden" name="subscribe.x" value="ssssssss" />
      <input type="hidden" name="mlist" value="ensnc&#95804&#59;&#58;Canoga&#32;&lt;svg&#32;onload&#61;confirm&#40;1&#41;&gt;" />
      <input type="submit" value="Submit request" />
    </form>
  </body>
</html>
```

### 🔐 Codificación Avanzada
Escape inteligente de caracteres (<, >, &, etc.)

Soporte para:

URL Encoding

HTML Entities

JavaScript Unicode Escape

### ⚡ Integración con Burp
Click derecho en cualquier request

Seleccionar "Generar CSRF PoC Avanzado"

El HTML se copia automáticamente al portapapeles

### 🚀 Instalación
Descargar el .jar desde Releases

En Burp Suite:

Extender > Add > Seleccionar el archivo .jar  
## 📸 Demo
![1](https://github.com/user-attachments/assets/00574069-aa73-4d54-93e7-b11257569050) ![2](https://github.com/user-attachments/assets/0efba056-cbeb-480a-919b-89cc77c01ad2) ![3](https://github.com/user-attachments/assets/d414c41e-6a71-4da9-ac5e-4786db744f2d) ![4](https://github.com/user-attachments/assets/4d9df483-6fe2-4a48-a3bc-07144a2704ad)



 

### 🛡️ Casos de Uso Avanzados
Bypass de CAPTCHAs mediante CSRF

Chaining con XSS para ataques combo

Explotación de APIs REST con Content-Type: JSON
