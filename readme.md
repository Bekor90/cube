## Aplicación cube

Esta aplicación permite definir una matriz con un tamaño y un número de operaciones, actualmente solo tiene 2 operaciones las cuales son una de actualización y otra para realizar suma.

## Comenzando 🚀


La aplicación cuenta con un backend el cual está hecho en java, el servidor de aplicación utilizado es wildfly 10.x

En la parte de fronted se realizo en angular 7.

### Pre-requisitos 📋

- instalar node.js v10.15.3
- servidor de aplicación wildfly 10.x

### Poniendo en marcha la aplicación

- agregar el archivo .war a servidor de aplicaciones wildfly 10.x por medio de la plataforma, ingrese al host que tenga configurado por defecto para su servidor de aplicaciones.
- En Deployments dar clic en add, seleccionar la opción Upload a new deployment dar clic en next y seleccionar el archivo .war y finish.
- Por defecto en el fronted la aplicación esta apuntando a localhost en el puerto 8080 para realizar peticiones, si ha cambiado algunos deestos parametros se debe cambiar la url y el puerto.
- Para cambiar parametros de url y puerto debe ingresar a la caperta del proyecto fronted y en la ruta "src-> app-> services ->constant.service.ts modificar en la variable "API_URL". 

- Por medio de la consola de node.js ingresar al directorio donde se encuentra el fronted, ingrese a la carpeta cube, ejecute el comando: npm install
- Posteriormente ejecute el comando ng serve -o y espere que muestre en su navegador la aplicación.


### Uso de aplicación
- Se tiene 2 modalidades para el uso de la aplicación: automático y manual.
- El modo automático ejecutará automaticamente con valores por defecto 2 pruebas, una unitaria y otra manual.
- En el modo manual se debe configurar todo lo necesario para realizar una operación, llenando los diferentes campos que se van mostrando.

### Resultado
-La aplicación mostrará unas tablas con la información de las operaciones que se ejecutó y el resultado en el caso de usar querys.
- Si en la tabla en contenido o resultado muestra vacío es por que no se generó algún resultado la operación realizada.