package modelos;

import lombok.Data;

@Data
public class PrevEvolutionItem{
	private String num;
	private String name;
	@Override
	public String toString() {
		return "PrevEvolution{" + "num=" + num + ", name=" + name + '}';
	}
}