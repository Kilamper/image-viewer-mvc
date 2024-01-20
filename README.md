# Image Viewer - MVC

## Descripción

Image Viewer es una aplicación de visualización de imágenes desarrollada en Java que sigue el patrón de diseño **Model-View-Controller (MVC)**. Esta aplicación proporciona a los usuarios una interfaz intuitiva para cargar y visualizar sus imágenes de manera eficiente.

## Características

- **Interfaz Gráfica Intuitiva:** Una interfaz de usuario fácil de usar que permite a los usuarios navegar y visualizar imágenes de manera cómoda.

* **Carga de Imágenes:** La aplicación permite a los usuarios cargar imágenes desde su sistema de archivos local mediante un selector de carpeta.

+ **Gestión de Imágenes:** Los usuarios pueden realizar operaciones básicas como navegar hacia adelante y hacia atrás utilizando botones. También se puede ver el nombre de la imagen y el porcentaje al que ha sido escalada para permitir su visualización.

## Patrón de Diseño

La arquitectura de Image Viewer sigue el patrón de diseño MVC para asegurar una separación clara de las preocupaciones y facilitar el mantenimiento y la extensión del código.

- **Modelo (Model):** Se encarga de la gestión de datos, incluyendo la carga y almacenamiento de imágenes.

* **Vista (View):** Presenta la interfaz de usuario al usuario y maneja las interacciones con el usuario.

+ **Controlador (Controller):** Responde a las acciones del usuario, manipula el modelo y actualiza la vista en consecuencia.
