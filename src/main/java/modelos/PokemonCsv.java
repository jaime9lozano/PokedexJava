package modelos;




public class PokemonCsv {
    private String num;
    private String weight;
    private String name;
    private int id;
    private String height;

    public PokemonCsv(String num, String weight, String name, int id, String height) {
        this.num = num;
        this.weight = weight;
        this.name = name;
        this.id = id;
        this.height = height;
    }

    public String getNum() {
        return num;
    }

    public String getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "PokemonCsv{" +
                "num='" + num + '\'' +
                ", weight='" + weight + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", height='" + height + '\'' +
                '}';
    }
}
