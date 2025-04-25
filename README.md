<a href='https://ko-fi.com/O4O3W3IIA' target='_blank'>
  <img height='36' style='border:0px;height:36px;' src='https://storage.ko-fi.com/cdn/kofi5.png?v=6' border='0' alt='Buy Me a Coffee at ko-fi.com' />
</a>

# Generador CSRF POC Avanzado - Extensión de Burp Suite
![Burp Suite](https://img.shields.io/badge/Burp_Suite-Compatible-green)  
![Versión](https://img.shields.io/badge/Versión-1.0-blue)  

¡Bienvenido al repositorio del **Generador CSRF POC Avanzado**! Esta extensión para Burp Suite está diseñada para simplificar y potenciar tus pruebas de seguridad, permitiéndote crear Pruebas de Concepto (PoC) de ataques CSRF directamente desde la interfaz de Burp Suite.

## 📋 **Índice**

- [Características](#características)
- [Para quién es este plugin](#para-quién-es-este-plugin)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Demo](#Demo)
- [Cómo empezar](#cómo-empezar)
  - [Requisitos](#requisitos)
  - [Instalación](#instalación)
- [Uso](#uso)
- [Contribuir](#contribuir)

## 🌟 **Características**

- **Generación Automática de PoCs:** Crea PoCs en formatos HTML, cURL y JavaScript con solo un clic.
- **Interfaz Intuitiva:** Diseño amigable que facilita la visualización y edición de peticiones HTTP.
- **Análisis Detallado:** Desglosa las peticiones HTTP para identificar parámetros y cabeceras críticas.
- **Compatibilidad Total:** Funciona perfectamente con Burp Suite, aprovechando al máximo sus capacidades.
- **Personalización:** Añade tus propios ejemplos y ajusta las configuraciones según tus necesidades.

## 🎯 **Para quién es este plugin**

- **Bug Bounty Hunters:** Encuentra y reporta vulnerabilidades CSRF de manera más eficiente.
- **Desarrolladores:** Integra pruebas de seguridad en tu flujo de trabajo de desarrollo.
- **Pentesters:** Acelera tus pruebas de penetración con PoCs generados automáticamente.
- **Investigadores de Seguridad:** Analiza y documenta vulnerabilidades CSRF de manera detallada.

## 📂 **Estructura del Proyecto**

El proyecto sigue una estructura organizada con Maven para facilitar la compilación y gestión de dependencias. A continuación, se detalla la estructura del proyecto:

 ```
generador-csrf-poc/
├── pom.xml
└── src/
└── main/
├── java/
│   └── com/
│       └── curiosidadesdehackers/
│           └── burp/
│               ├── GeneradorCsrfPoc.java      # Clase principal
│               ├── AnalizadorPeticion.java    # Analiza la petición HTTP
│               ├── GeneradorHtmlPoc.java      # Genera el POC HTML
│               ├── GeneradorCurlPoc.java      # Genera el POC cURL
│               ├── GeneradorJsPoc.java        # Genera el POC JavaScript
│               └── PanelInterfaz.java         # Interfaz gráfica
└── resources/
├── icono.png                             # Icono para Burp
└── banner.txt                             # Banner personalizado
```
## 📸 Demo
![1](https://github.com/user-attachments/assets/00574069-aa73-4d54-93e7-b11257569050) ![2](https://github.com/user-attachments/assets/0efba056-cbeb-480a-919b-89cc77c01ad2) ![3](https://github.com/user-attachments/assets/d414c41e-6a71-4da9-ac5e-4786db744f2d) ![4](https://github.com/user-attachments/assets/4d9df483-6fe2-4a48-a3bc-07144a2704ad)


### **Archivo `pom.xml`**

El archivo `pom.xml` está configurado para gestionar las dependencias y el proceso de compilación del proyecto. Aquí se incluyen las dependencias necesarias para la API de Burp Extender y Swing para la interfaz gráfica.

### **Clase Principal (`GeneradorCsrfPoc.java`)**

La clase principal `GeneradorCsrfPoc.java` gestiona la extensión y registra los menús contextuales. También inicializa la interfaz gráfica y maneja la generación de PoCs.

### **Analizador de Petición (`AnalizadorPeticion.java`)**

Esta clase se encarga de analizar las peticiones HTTP para extraer datos esenciales como el método, la URL, los parámetros y las cabeceras.

### **Generadores de PoC**

- **GeneradorHtmlPoc.java:** Genera PoCs en formato HTML.
- **GeneradorCurlPoc.java:** Genera PoCs en formato cURL.
- **GeneradorJsPoc.java:** Genera PoCs en formato JavaScript.

### **Panel de Interfaz (`PanelInterfaz.java`)**

Esta clase crea la interfaz gráfica que se integra en Burp Suite. Permite visualizar y editar las peticiones HTTP, así como generar los PoCs en diferentes formatos.

## 🚀 **Cómo empezar**

### **Requisitos**

- Burp Suite (versión compatible con la API de Burp Extender).

### **Instalación**

1. **Descargar el Plugin:**
   - Descarga el archivo JAR compilado desde la [página de releases](https://github.com/tu-usuario/generador-csrf-poc/releases).

2. **Instalar en Burp Suite:**
   - Abre Burp Suite.
   - Ve a la pestaña "Extender".
   - Haz clic en "Add" y selecciona el archivo JAR descargado.

## 🔧 **Uso**

1. **Seleccionar una Petición:**
   - En Burp Suite, selecciona una petición HTTP desde el "Proxy" o "Repeater".

2. **Generar PoC:**
   - Haz clic derecho en la petición y selecciona "Generar POC CSRF" desde el menú contextual.
   - La interfaz del plugin se abrirá con la petición seleccionada.

3. **Visualizar y Editar:**
   - Puedes visualizar y editar la petición en la interfaz del plugin.
   - Haz clic en "Generar POC CSRF" para crear los PoCs en formatos HTML, cURL y JavaScript.

## 🤝 **Contribuir**

¡Tu contribución es bienvenida! Si encuentras algún problema o tienes sugerencias para mejorar el plugin, por favor abre un issue o envía un pull request.


