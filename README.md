# MVC Android
*Modelo Vista Controlador en Android*
En este proyecto vemos la forma de usar el Modelo Vista Controlador en Android
este modelo tiene una desventaja en Android con respecto a por ejemplo, usarlo en web usando Laravel o NodeJS o en aplicaciones multiplataforma
Y es que el **controlador y la vista siempre están unidos** por medio de las Activity  que no se pueden separar de sus clases principales ni en Java ni en Kotlin

Sin embargo sigue siendo un modelo más ordenado que haciendolo sin ningun patrón de diseño. Y esto se debe a la simplicidad de las clases principales de conectarse con la API, es donde se observa más las caracteristicas de SOLID. Pues el **modelo** se mantiene simple y mantiene las llamadas a la API en el mínimo

[![Ejemplo de Modelo Vista Controlador](Mozilla "Ejemplo de Modelo Vista Controlador")](https://mdn.mozillademos.org/files/16042/model-view-controller-light-blue.png "Ejemplo de Modelo Vista Controlador")
