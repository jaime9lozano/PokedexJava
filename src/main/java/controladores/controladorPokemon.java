package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelos.Pokedex;
import modelos.Pokemon;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class controladorPokemon {
    private static controladorPokemon instance;
    Pokedex pokedex = new Pokedex();

    private controladorPokemon() {
        loadPokedex();
        run();
    }

    public static controladorPokemon getInstance() {
        if (instance == null) {
            instance = new controladorPokemon();
        }
        return instance;
    }

    private void loadPokedex() {
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "Data";
        String paisesFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {
            }.getType());
            System.out.println("pokedex cargada: " + pokedex.getPokemon().size());
            System.out.println(pokedex.getPokemon().get(5));
        } catch (Exception e) {
            System.out.println("Error al cargar la Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void run() {
        cincoUltimos();
        evolucionCharmander();
        debaguaElectrico();
        masDebilidades();
        evolucionSinFuego();
        mediaPeso();
        mediaEvoluciones();
        diezPrimeros();
        datosPikachu();
        tipoFuego();
        pokemonConUnaDebilidad();
        pokemonConMenosEvol();
        masPesado();
        masAlto();
        nomMasLargo();
        mediaAltura();

    }

    private void cincoUltimos() {
        System.out.println();
        System.out.println("5 últimos pokemons");
        pokedex.getPokemon().stream()
                .sorted(java.util.Collections.reverseOrder())
                .limit(5)
                .forEach(System.out::println);
    }

    private void evolucionCharmander() {
        System.out.println();
        System.out.println("evolución de Charmander");
        pokedex.getPokemon().stream()
                .filter(pokemon ->
                        pokemon.getName().startsWith("Charmander")
                )
                .map(Pokemon::getNextEvolution)
                .forEach(System.out::println);
    }

    private void debaguaElectrico() {
        System.out.println();
        System.out.println("pokemons con debilidad water o electric");
        pokedex.getPokemon().stream()
                .filter(pokemon -> {
                    List<String> tipo = pokemon.getWeaknesses();
                    return tipo.equals("water") || tipo.equals("electric");
                })
                .forEach(System.out::println);
    }

    private void masDebilidades() {
        System.out.println();
        Optional<Pokemon> pokemonConMasDebilidades = pokedex.getPokemon().stream()
                .max(Comparator.comparingInt(pokemon -> pokemon.getWeaknesses().size()));
        Pokemon masDebilidades = pokemonConMasDebilidades.get();
        System.out.println("El Pokémon con más debilidades es: " + masDebilidades.getName());
    }

    private void evolucionSinFuego() {
        System.out.println();
        System.out.println("Pokemon con una evolución que no es de tipo fire");
        pokedex.getPokemon().stream()
                .filter(pokemon -> {
                    List<String> tipo = pokemon.getWeaknesses();
                    return tipo.equals("water") || tipo.equals("electric");
                })
                .map(Pokemon::getPrevEvolution)
                .forEach(System.out::println);
    }

    private void mediaPeso() {
        System.out.println();
        List<Double> pesosNumericos = (List<Double>) pokedex.getPokemon().stream()
                .map(pokemon -> Double.parseDouble(pokemon.getWeight().replace(" kg", "")));
        double mediaDePeso = pesosNumericos.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        System.out.println("La media de peso es: " + mediaDePeso + " kg");
    }

    private void mediaEvoluciones() {
        System.out.println();
        double mediaDeEvoluciones = pokedex.getPokemon().stream()
                .mapToDouble(pokemon -> (pokemon.getPrevEvolution().size() + pokemon.getNextEvolution().size()) / 2.0)
                .average()
                .orElse(0);

        System.out.println("La media de las evoluciones es: " + mediaDeEvoluciones);
    }

    private void diezPrimeros() {
        System.out.println();
        System.out.println("Primeros 10 pokemons");
        pokedex.getPokemon().stream()
                .limit(10)
                .forEach(System.out::println);
    }

    //Obtener los datos de Pikachu.
    private void datosPikachu() {
        System.out.println();
        System.out.println("Datos de Pikachu");
        pokedex.getPokemon().stream()
                .filter(name -> name.equals("Pikachu"))
                .forEach(System.out::println);
    }

    // Pokemon de tipo fire
    private void tipoFuego() {
        System.out.println();
        System.out.println("Pokemon tipo Fuego");
        pokedex.getPokemon().stream()
                .filter(type -> type.equals("fire"))
                .forEach(System.out::println);

    }

    private void pokemonConUnaDebilidad() {
        System.out.println();
        System.out.println("Pokemon con una debilidad");
        pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getWeaknesses().size() == 1)
                .forEach(System.out::println);
    }

    private void pokemonConMenosEvol() {
        System.out.println();
        System.out.println("Pokemon con menos evoluciones");
        pokedex.getPokemon().stream()
                .filter(pokemon -> pokemon.getNextEvolution().size() == 0)
                .forEach(System.out::println);
    }

    private void masPesado() {
        System.out.println();
        System.out.println("Pokemon mas pesado");
        pokedex.getPokemon().stream()
                .max(Comparator.comparing(pokemon -> Double.parseDouble(pokemon.getWeight().substring(0, pokemon.getWeight().length() - 3))))
                .ifPresent(System.out::println);
    }

    private void masAlto() {
        System.out.println();
        System.out.println("Pokemon mas alto");
        pokedex.getPokemon().stream()
                .max(Comparator.comparing(pokemon -> Double.parseDouble(pokemon.getHeight().substring(0, pokemon.getHeight().length() - 2))))
                .ifPresent(System.out::println);
    }

    private void nomMasLargo() {
        System.out.println();
        System.out.println("Pokemon con menos evoluciones");
        pokedex.getPokemon().stream()
                .max(Comparator.comparing(pokemon -> pokemon.getName().length()))
                .ifPresent(System.out::println);
    }

    private void mediaAltura() {
        System.out.println();
        List<Double> pesosNumericos = (List<Double>) pokedex.getPokemon().stream()
                .map(pokemon -> Double.parseDouble(pokemon.getHeight().replace(" m", "")));
        double mediaDePeso = pesosNumericos.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
        System.out.println("La media de altura es: " + mediaDePeso + " m");
    }
}



    
