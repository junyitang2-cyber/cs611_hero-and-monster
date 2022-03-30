import java.security.PublicKey;
import java.util.List;

public abstract class Monster {
    public String name;
    public int level;
    public int damage;
    public int defense;
    public int dodge_chance;
    public Monster(List<String> list){
        this.name = list.get(0);
        this.level = Integer.parseInt(list.get(1));
        this.damage = Integer.parseInt(list.get(2));
        this.defense = Integer.parseInt(list.get(3));
        this.dodge_chance = Integer.parseInt(list.get(4));
    }

}
