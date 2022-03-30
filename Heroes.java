import java.util.List;

public abstract class Heroes {
    //class to define heroes' data
    public String type;
    public String name;
    public int mana;
    public int strength;
    public int agility;
    public int dexterity;
    public int money;
    public int experience;
    public int level;
    public Heroes(String type,List<String> data){
        this.type = type;
        this.name = data.get(0);
        this.level = 1;
        this.mana = Integer.parseInt(data.get(1));
        this.strength = Integer.parseInt(data.get(2));
        this.agility = Integer.parseInt(data.get(3));
        this.dexterity = Integer.parseInt(data.get(4));
        this.money = Integer.parseInt(data.get(5));
        this.experience = Integer.parseInt(data.get(6));
    }

    public abstract int attack();
    public abstract int defend();
    public abstract boolean run();
}
