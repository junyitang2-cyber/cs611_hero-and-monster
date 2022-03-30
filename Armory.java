import java.net.Inet4Address;
import java.util.List;

public class Armory extends Item{
    int damage_reduction;
    List<String> info;
    public Armory(List<String> info) {
        super(info);
        this.info = info;
        this.damage_reduction = Integer.parseInt(info.get(3));
    }
    public static Armory get_copy(Armory a){
        return new Armory(a.info);
    }
    public void StoreInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage_reduction: "+this.damage_reduction+ " Cost: "+this.cost);
    }
    public void printInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage_reduction: "+this.damage_reduction);
    }
}
