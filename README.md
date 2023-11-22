# parcialGrupo3Whale
Integrantes
Ariel Joaquin		
Juan Pulleiro	
Lautaro Nola		
Mateo Manente	
Matias Mazzucchelli
Ursula Martinez	


Preguntas

1.	En el caso que se pida extender la app para otros tipos de mascotas, por ejemplo, gatos. ¿la app es flexible? ¿Qué cambios realizaría?
La app se diseñó de forma escalable, ya que la clase que contempla el alta de perros es genérica para mascotas, lo cual permite poder utilizarla para otros tipos de animalitos, agregando subclases en el caso de ser necesaria la implementación de nuevos atributos propios de determinada mascota, esa subclase heredaría de esta genérica los atributos comunes.
Los cambios principalmente a realizar sería cambiar las API de consumo de datos, para ampliar la información no solo para obtener fotos de perros, sino de otras mascotas.
2.	¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla?
Usamos la arquitectura en 2 capas, por un lado, la interfaz de usuario y por otro la capa de datos, para tener independencia en la persistencia de datos con respecto a la información que se muestra en el frontend. Se podría agregar una 3er capa de negocio para separar más las reglas de negocio de la capa de IU con el fin de que quede lo más limpia posible, solo con la información de necesaria para mostrar de cara al usuario.  
3.	¿Qué mejoras detectan que podrían realizarle a la app?
Se pueden aplicar varias mejoras a nivel diseño y de funcionalidad.
•	Se podrían agregar animaciones.
•	Nuevas pantallas con información adicional de la mascota, como su alimentación, cuidados necesarios a considerar, datos médicos, vacunas etc.
•	Agregar la funcionalidad en la publicación para subir videos de la mascota.
•	Fecha de publicación.
