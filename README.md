Enunciado Ejercicio

AD/PSP - 2º DAM
UD1. API REST CON SPRING BOOT

EJERCICIO COMPLETO


Resultados de Aprendizaje

AD-RA01: Desarrolla componentes y aplicaciones que acceden o producen información en formato JSON o XML.
PSP-RA01. Desarrolla servicios web sencillos utilizando la arquitectura REST, siguiendo sus principios en cuanto a estructura de rutas y códigos de respuesta.


Criterio de evaluación
AD-RA01.b Se diseñan y utilizan las clases necesarias para producir o consumir información en formato JSON o XML.
AD-RA01.c Se ha documentado correctamente la API REST (Open API + Swagger) 

PSP-RA01.b Se han utilizado las diferentes clases y anotaciones que ofrece Spring para la implementación de servicios REST sencillos, aplicando cada verbo HTTP de forma correcta.
PSP-RA01.c Se han manejado correctamente excepciones propias para gestionar los errores asociados a la lógica de negocio.


API REST de Gestión de Bibliotecas Públicas
Se pide implementar una API REST para gestionar una colección de Bibliotecas Públicas.
 De cada Biblioteca se desea almacenar la siguiente información:
ID (entero)
Nombre de la ciudad
Nombre de la biblioteca
Año de fundación
Número aproximado de libros
Descripción general
URL oficial de la biblioteca




Requisitos funcionales
La API debe permitir:
1. Obtener todas las bibliotecas
GET /library
200 OK si existen bibliotecas.
404 NOT_FOUND si no hay registros.


2. Obtener una biblioteca por su ID
GET /library/{id}
200 OK si existe.
404 NOT_FOUND si no existe.


3. Crear una nueva biblioteca
POST /library
201 CREATED si se crea correctamente.
400 BAD_REQUEST si hay errores de validación o campos incorrectos.


4. Actualizar una biblioteca existente
PUT /library/{id}
200 OK si la biblioteca existe y ha sido actualizada.
400 BAD_REQUEST si los datos enviados son incorrectos.
404 NOT_FOUND si la biblioteca no existe.


5. Eliminar una biblioteca
DELETE /library/{id}
204 NO_CONTENT si se elimina correctamente.
404 NOT_FOUND si la biblioteca no existe.




Requisitos técnicos obligatorios
1. Uso obligatorio de DTOs
Debes implementar dos tipos de DTOs:
DTOs de entrada (request DTOs) para recibir los datos en los endpoints POST y PUT.


DTOs de salida (response DTOs) que se utilizarán para responder a todos los endpoints.


Nunca debe exponerse la entidad JPA directamente.


Los mapeos entre entidades y DTOs pueden hacerse de forma manual (si te animas, con una librería como MapStruct)

2. Gestión de excepciones en la capa de servicios
La capa de servicios debe lanzar excepciones personalizadas, como:
LibraryNotFoundException
InvalidLibraryDataException


Estas excepciones deben reflejar casos de negocio como:
Biblioteca no encontrada
Datos inválidos
Problemas de integridad o lógica
IDs no válidos
…


3. Conversión de excepciones a ProblemDetail
Debe existir una clase anotada con:
@RestControllerAdvice
public class GlobalExceptionHandler { ... }

que se encargue de:
Interceptar las excepciones personalizadas y convertirlas en objetos ProblemDetail
Devolver el código HTTP adecuado (404, 400, etc.)
Proporcionar mensajes claros y una estructura estándar de error


Ejemplos:
404 NOT_FOUND → "Biblioteca no encontrada"
400 BAD_REQUEST → "Datos inválidos en la solicitud"



4. Documentación de Endpoints (OpenAPI / Springdoc)
Cada endpoint debe estar documentado utilizando:
@Tag
@Operation
@ApiResponses/@ApiResponse
@Schema, @Content
@ExampleObject
…




Debe incluir ejemplos tanto de:
Biblioteca devuelta correctamente
Errores 400 y 404 usando ProblemDetail




