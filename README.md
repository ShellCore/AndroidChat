# AndroidChat

Aplicación de Chat con el uso de los servicios de Firebase.

Aplicación 2 del curso de desarrollo de aplicaciones Android a nivel profesional, impartido en la plataforma Edx.

Como primera instancia, la aplicación tiene una ventana de login, en el cual el usuario será el correo del usuario, junto con el campo de contraseña. La misma ventana muestra los botones de inicio de sesión y crear contraseña.

Cuando el usuario tenga acceso a su sesión, la aplicacion muestra una vista de la lista de contactos, así como un botón para agregar nuevos contactos.

Cuando se de click en el botón para agregar un nuevo contacto, la aplicación muestra un cuadro de diálogo donde podrá agregar el email del contacto y guardar o cancelar dicho contacto.

Al dar click en algún contacto de la lista, la aplicación muestra una nueva ventana con el historial de conversaciones que ha tenido el usuario con el contacto.

La base de datos que contendrá la lista de usuarios, se encuentra almacenado en los servicios de Firebase, el cual contiene los siguientes modelo de datos:

Users
- ID
- email
- online (boolean)
- Contacts:
- - email: status(online|offline)
Chats
- User_User (alfabético)
- - mensaje
- - - contenido
- - - usuario_que_envía