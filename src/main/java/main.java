import controladores.controladorPokemon;
import controladores.controladorcsv;


public class main {
    public static void main(String[] args) {
        var pokeController = controladorPokemon.getInstance();
        var csvController = controladorcsv.getInstance();
    }

}
