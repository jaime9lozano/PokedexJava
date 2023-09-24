package modelos;

import java.util.List;
import lombok.Data;

@Data
public class Pokemon {
	private String img;
	private String egg;
	private String candy;
	private String num;
	private String weight;
	private List<String> type;
	private List<String> weaknesses;
	private String name;
	private int avgSpawns;
	private List<Object> multipliers;
	private int id;
	private String spawnTime;
	private String height;
	private Object spawnChance;
	private List<PrevEvolutionItem> prevEvolution;
	private int candyCount;
	private List<NextEvolutionItem> nextEvolution;
	@Override
	public String toString() {
		return "Pokemon{" +
				"id=" + id +
				", num='" + num + '\'' +
				", name='" + name + '\'' +
				", img='" + img + '\'' +
				", type=" + type +
				", height='" + height + '\'' +
				", weight='" + weight + '\'' +
				", candy='" + candy + '\'' +
				", candy_count=" + candyCount +
				", egg='" + egg + '\'' +
				", spawn_chance=" + spawnChance +
				", avg_spawns=" + avgSpawns +
				", spawn_time='" + spawnTime + '\'' +
				", multipliers=" + multipliers +
				", weaknesses=" + weaknesses +
				", next_evolution=" + nextEvolution +
				", prev_evolution=" + prevEvolution +
				'}';
	}


}
