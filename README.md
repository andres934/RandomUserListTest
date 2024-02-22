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
- Durante el desarrollo, surgió el desafío de implementar la paginación con Jetpack Compose mientras se seguía una estructura estricta de Clean Architecture. La biblioteca de Paging 3 expone un `Flow<PagingData<T>>` que debe ser llamado en Compose con `collectAsLazyPagingItems` para poder manejarlo como un estado, lo cual rompía el principio de que la capa de dominio no debe tener ninguna dependencia de Android. A pesar de una investigación exhaustiva, no se encontró una forma de evitar agregar una dependencia de Android a la capa de dominio. Además, el testeo de la paginación también fue una algo nuevo para mi y tomó algo de tiempo aprender y comprender la forma de testearlo.

- El diseño de la pantalla de detalles fue un desafio, especialmente debido a los pequeños detalles como el de tener una TopAppBar transparente y una cabecera con la imagen de perfil superpuesta. Aunque no fue un problema insuperable, llevó algo de tiempo encontrar la solución adecuada.

- La implementacion del buscador dio algunas complicaciones ya que el objeto que manejamos an nivel de UI es un LazyPagingItems el cual no ofrece ninguna opcion de modificacion, lo unico que ofrece es la opcion de tomar una snapshot de los elementos actuales lo cual desactivaria la carga del scroll infinito, tambien se intento filtrar por elemento dentro del LazyPagingItem pero al mantener la carga infinita activa bajaba considerablemente el rendimiento, ya que se activaba infinitamente por cada caracter escrito, por lo que la opcion propuesta fue usar 2 listas dependiendo de la ocasion, mientras se este buscando se usa el snapshot de los elementos actuales y cuando no se este buscando se usara directamente el LazyPagingItems para mantener funcional la carga del scroll infinito sin impactar el rendimiento del dispositivo

### Estado del proyecto
El proyecto esta terminado con algunas implementaciones que requieren una mayor investigacion para usar el metodo mas optimo posible para cada situacion

