package modelos;

import lombok.Data;

@Data
public class NextEvolutionItem{
	private String num;
	private String name;
	@Override
	public String toString() {
		return "NextEvolution{" +
				"num='" + num + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}