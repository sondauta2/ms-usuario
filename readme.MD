# Título del Proyecto

_Resolución prueba técnica_

## Comenzando 🚀

_Existen al menos 2 formas de poder realizar la instalacion y pruebas del programa._

Una es mediante un **Deployment** en una instalacion que tenga al menos **IntelliJ** y otra es mediante el uso del archico **jar**.


### Descripcion y estructura del proyecto 📋

_Se resumirá la solución implementada y a continuacion se mostrará la estructura del proyecto_

Para dar la solucion, se procedio a generar una aproximacion a la arquitectura hexagonal.
Se basa en los principios SOLID, realizando una separacion de capas, para la exposicion de datos y sus componentes, permitiendo una separacion entra la obtencion de datos y el proceso de expisicion de datos.


```
ms.usuario/
├── src/main/java/com.manuelcastillo
│   ├── controller
│   ├── dto
│   ├── entities
│   ├── mapper
│   ├── repositories
│   └── services
└── readme.MD
```

### Descripcion de la asolucion 📌
La segregacion de las capas, a fin de aplicar patrones de diseño y clean code se detalla a conitnuacion:

```
ms.usuario/
├── capa de presentacion
│   ├── controller
│   ├── mapper
│   └── dto
├── capa de datos
│   ├── services
│   ├── entities
│   └── repositories
└── ./target/ms-usuario-0.0.1-SNAPSHOT.jar
```


### Instalación 🔧

* _Opcion 1:  copiar y abrir el codgo fuente, en un IntelliJ_
* _Opcion 2:  Ejecutar el archivo JAR provisto como parte de la solucion_


## Ejecucion de la aplicacion ⚙️

_A continuacion se describen las formas de ejecutar la apliccion y luego accionar los metodos_

* [**InrelliJ**] - Aqui se copia y abre el proyecto, en una maquina con IntelliJ instalado, ademas de **Maven**, lo que permite simplemente ejecutar la aplicacion normal desde el ide,  Ahora se puede consultar la base de datos local y ejecutar los procesos desde el navegador web.
* [**JAR**] - Aqui, la idea es ejecutar la aplicacion con el archivo JAR entregado.  Luego se puede consultar la base de datos local interna y ejecutar los MS desde el navegador web.

 _Una vez que la aplicacion est{e funcionando, a traves del navegasor web se pueden accionar los metodos.  A continuacion se puestan los detalles de la apertura mediante el ide_

_Para el caso de la creacion de un usuario_
```
http://localhost:8095/api/createuser
```
_Para el caso realizar un login_
```
http://localhost:8095/api/login
```

_Para la situacion en que se haga un desplieque de la aplicacion mediante JAR, seria de la siguiente forma:_
```
java -jar ./target/ms-usuario-0.0.1-SNAPSHOT.jar
```
_Y luego ya se puede acceder al navegador web, de la misma forma que lo hicimos desde el IDE_

_Para el caso de la creacion de un usuario_
```
http://localhost:8095/api/createuser
```
_Para el caso realizar un login_
```
http://localhost:8095/api/login
```

Hay que considerar tambien, que para la ejecucion de los metodos, se pueden utilizar una coleccion de request (la cual tambien es provista) ademas de la opcion de Swagger.



## Construido con 🛠️

_A continuacion se enumera un listado de las herramientas utilizadas para la construccion de la aplicacion_

* [Spring Initializr](https://start.spring.io/) - Herramienta web, que permite crear el esquelon basico de una aplicacion Java Springboot
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) - IDE utilizado para el desarrollo
* [H2 IDEA](https://www.h2database.com/html/main.html) - H2 Database Engine
* [SWAGGER](https://swagger.io/tools/open-source/open-source-integrations/) - Tools and Integrations
* [JPA](https://spring.io/projects/spring-data-jpa) - Java Persistence API

## Endpoints 🖇️
* [/api/createuser](http://localhost:8095/api/createuser) - Permite la creacion de un usuario y su listado de telefonos
* [/api/deleteuser/{id}](http://localhost:8095/api/deleteuser/78d6779e-5161-46ef-a039-b4a75a48c376) - Permite eliminar un usuario completo
* [/api/login](http://localhost:8095/api/login) - Permite hacer ligin y actualizar la fecha de logueo
* [/api/getuser/{id}](http://localhost:8095/api/getuser/363d5fc6-7879-4a62-b091-9ba2029ba14e) - Permite obtener la informacion completa de un usuario


## Autor ✒️

* **Manuel Castillo** - *Trabajo Inicial, Documentacion* - [villanuevand](https://github.com/villanuevand)

# ms-usuario
