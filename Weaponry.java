import java.util.ArrayList;
import java.util.List;

public class Weaponry extends Item{
    public int damage;
    public int hands;
    List<String> info;
    public Weaponry(List<String> info) {
        super(info);
        this.info = info;
        this.damage = Integer.parseInt(info.get(3));
        this.hands = Integer.parseInt(info.get(4));
    }
    public static Weaponry get_copy(Weaponry a){
        return new Weaponry(a.info);
    }
    public void printInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage: "+this.damage+" hands: "+this.hands);
    }
    public void StoreInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage: "+this.damage+" hands: "+this.hands+" Cost: "+this.cost);
    }
}
