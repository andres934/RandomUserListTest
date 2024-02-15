## RandomUserListTest

### Descripción
El proyecto RandomUserListTest fue creado desde cero siguiendo una estructura basada en Clean Architecture. Está dividido en tres módulos principales: app para la capa de presentación, data para la capa de datos y domain para la lógica de negocio.

### Tecnologías utilizadas
- **Jetpack Compose**: El diseño de las pantallas se realizó utilizando Jetpack Compose, la biblioteca moderna de UI para aplicaciones de Android.
- **Paging 3**: Se implementó la paginación utilizando la biblioteca de Paging 3 de Jetpack para cargar datos de forma eficiente.
- **MockK**: Se utilizó MockK para escribir pruebas unitarias.
- **Librería de Testing para Jetpack Compose**: Para las pruebas instrumentales de Jetpack Compose.
- **Retrofit 2 y Moshi**: Las llamadas a la red se realizaron utilizando Retrofit 2 para las solicitudes HTTP y Moshi para el análisis JSON.
- **Coroutines y Flows**: Para el manejo asíncrono de la información y la notificación de cambios a la pantalla.
- **Coil para Compose**: La carga de imágenes en Jetpack Compose se realizó utilizando la biblioteca Coil.

### Problemas encontrados
Durante el desarrollo, surgió el desafío de implementar la paginación con Jetpack Compose mientras se seguía una estructura estricta de Clean Architecture. La biblioteca de Paging 3 expone un `Flow<PagingData<T>>` que debe ser llamado en Compose con `collectAsLazyPagingItems` para poder manejarlo como un estado, lo cual rompía el principio de que la capa de dominio no debe tener ninguna dependencia de Android. A pesar de una investigación exhaustiva, no se encontró una forma de evitar agregar una dependencia de Android a la capa de dominio. Además, la implementación de la paginación también fue una tarea nueva y tomó algún tiempo aprender y comprender completamente su funcionamiento.

Otro desafío fue el diseño de la pantalla de detalles, especialmente debido a la peculiaridad de tener una TopAppBar transparente y una cabecera con la imagen de perfil superpuesta. Aunque no fue un problema insuperable, llevó algo de tiempo encontrar la solución adecuada.

### Estado del proyecto
El proyecto no está completamente terminado. Sin embargo, debido a limitaciones de tiempo, no fue posible cubrir todas las implementaciones deseadas y asegurar que el código quedara completamente limpio.

