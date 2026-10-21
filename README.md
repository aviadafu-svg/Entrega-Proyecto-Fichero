AirTortilla - Procesador Inteligente de Reservas 
Proyecto desarrollado en Java y JavaScript diseñado para procesar, gestionar y generar localizadores de reservas de vuelos a partir de archivos de texto estructurados en formato CSV. El sistema cuenta tanto con una arquitectura orientada a consola/lógica en Java como con un procesador web interactivo.

Características Principales
Lectura y Procesamiento de Ficheros CSV: Importación automática de datos de pasajeros (origen, destino, nombre y fecha).

Generación Automática de Localizadores: Algoritmo inteligente que detecta coincidencias de pasajeros recurrentes y les asigna un identificador único de reserva (LOC-001, LOC-002, etc.).

Exportación Organizada: Guarda los resultados procesados en la carpeta salida/ utilizando una nomenclatura estandarizada (AirTortilla_[DESTINO]_[FECHA].csv).

Visualización Dual: Permite consultar los resultados procesados de forma completa o de manera interactiva (línea por línea).

Versión Web Interactiva: Interfaz en HTML5 y JavaScript puro para ejecutar todo el flujo directamente desde el navegador web.

Estructura del Código
El proyecto está compuesto por los siguientes módulos principales:

Reserva.java: Modelo de datos que representa una reserva individual. Contiene los atributos de la ruta, pasajero, fecha y el código localizador, además de la lógica para comparar pasajeros (coincidePasajero).

GestorReservas.java: Gestiona la lógica de negocio, lectura del fichero original, aplicación del algoritmo de localizadores, creación de ficheros de salida y métodos de consulta por consola.

index.html: Interfaz web visual con diseño moderno que replica la lógica de procesamiento en el navegador utilizando JavaScript.

Tecnologías Utilizadas
Java (Lectura de ficheros mediante BufferedReader, PrintWriter, colecciones ArrayList).

HTML5 & CSS3 (Diseño de la interfaz web moderna con variables CSS y diseño adaptativo).

JavaScript (ES6+) (FileReader, manipulación del DOM y lógica de filtrado de datos).
