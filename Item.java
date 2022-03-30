import java.util.List;

public class Item {
    public String name;
    public int cost;
    public int level;

    public Item(List<String> info){
        this.name = info.get(0);
        this.cost = Integer.parseInt(info.get(1));
        this.level = Integer.parseInt(info.get(2));
    }
}
