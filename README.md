# PokedexJava
Trabajo realizado por Jaime lozano y Kevin sanchez.
Lo primero que hacemos es crear nuestra clase pollo desde nuestro fichero pokedex.json: 
el cual esta en nuestra carpeta modelos y incluye la clase pokemon con los datos de cada uno, una clase para que evolucion hacia delante y otra hacia atras y una clase pokedex que crea una lista de la clase pokemon para tener almacenados todos los pokemon.
Despues tenemos una carpeta controladores que es donde vamos a realizar las acciones, las tres tienen el constructor singleton para llamarlo en la clase main y que funcione la aplicacion.
En la primera que es la de pokemon primeros leemos el fichero y cargamos los datos en nuestra lista de pokedex: tenemos un metodo que coge la ubicacion del archivo y lo lee, despues tenemos un metodo run que carga los metodos en los cuales buscamos lo que se nos pide en el ejercico.
En la segunda parte(controladorcsv) crearemos un archivo csvel cual saca del archivo json solo los atributos nombre altura peso y id de cada pokemon.
En la tercera parte (controladorH2) crearemos la tabla con los datos que nos pedian del csv y leyendo el csv rellenaremos la tabla con los datos del csv que creamos antes,ya con los datos cargados en la base de datos realizaremos dos consultas un select de todos y un select de los datos de pikachu.

