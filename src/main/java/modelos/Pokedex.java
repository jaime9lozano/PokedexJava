package modelos;


import java.util.List;

import lombok.Data;


@Data
public class Pokedex {
	private List<Pokemon> pokemon;

	public List<Pokemon> getPokemon() {
		return pokemon;
	}
}