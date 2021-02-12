# REST Test

# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.
	> Se crea el fork https://github.com/caxsolutions/examen
	La rama se llama "carlospolanco"

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos.
- ABM de clientes.
- Consulta de transacciones de compra.
- Aprobación de compras.
 
3) Los servicios deben contar con logs que indiquen si el servicio respondió y proceso correctamente o no.
  
4) Documentar brevemente los servicios implementados.
	>[swagger del proyecto](http://104.197.232.113:8090/payments/swagger-ui/index.html?url=/payments/v3/api-docs)
	El servicio está desplegado en compute engine de google
	esta url no tiene un certificado.
5) Todos los servicios deben contar, al menos, con test unitarios.
	 > Todos los servicios tienen pruebas unitarias, pero debido a la carga laboral actual, no profundice en estas pruebas.
6) Enviar un Pull Request con todos los cambios realizados. 

Para correr la aplicación se puede utilizar maven: 

mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

Pueden probar el servicio echo con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/payments/echo`

Bonus

1) ABM de vendedores.
	>se realiza ABM de vendedores
2) Agregar test de integración.
3) Calcular la cobertura de los tests.
4) Correr pruebas con base de datos en memoria.
5) Crear Docker Image.
	>Se crea una imagen del proyecto en [dockerhub](https://hub.docker.com/repository/docker/luxos/tecsoflexibilitysrl)
	`docker push luxos/tecsoflexibilitysrl:tagname`

6) Hostear la app en un cloud computing libre y enviar la URL para consultar.
	> El servicio está desplegado en google compute engine
	[get clientes en google cloud](http://cloud.r-fast.com:8090/payments/api/v1/clients/getclients)


