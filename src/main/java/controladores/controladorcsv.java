package controladores;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import modelos.PokemonCsv;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class controladorcsv {
    private static controladorcsv instance;
    private controladorcsv() {
        pokemoncsv();
        mostrarCsv();
    }
    public static controladorcsv getInstance() {
        if (instance == null) {
            instance = new controladorcsv();
        }
        return instance;
    }
    private String json(){
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "Data";
        String paisesFile = dir + File.separator + "pokemon.json";
        return paisesFile;
    }
    private String csv(){
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "Data";
        String paisesFile = dir + File.separator + "pokemon.csv";
        return paisesFile;
    }

    /**
     * Genera un archivo csv a partir de un json y el arraylist pokedex
     */
    private void pokemoncsv(){
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(json()));

        FileWriter csvWriter = new FileWriter(csv());
        csvWriter.append("id,num,name,height,weight\n"); // Encabezados del archivo CSV

        Iterator<JsonNode> pokemonIterator = rootNode.iterator();
        while (pokemonIterator.hasNext()) {
            JsonNode pokemon = pokemonIterator.next();
            String id = pokemon.get("id").asText();
            String numero = pokemon.get("num").asText();
            String nombre = pokemon.get("name").asText();
            String altura = pokemon.get("height").asText();
            String peso = pokemon.get("weight").asText();

            csvWriter.append(id).append(",").append(numero).append(",").append(nombre).append(",").append(altura).append(",").append(peso).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

        System.out.println("Archivo CSV generado con éxito.");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /**
     * Lee un csv y muestra los datos por pantalla
     */
    private void mostrarCsv(){
    try (BufferedReader reader = new BufferedReader(new FileReader(csv()))) {
        Stream<PokemonCsv> pokemons = reader.lines()
                .map(linea -> linea.split(",")) // separar cada línea en un array de strings
                .map(valores -> new PokemonCsv(valores[0],valores[1],valores[2],Integer.parseInt(valores[3]),valores[4]));
                 pokemons.forEach(System.out::println);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

