import java.util.List;

public class Exoskeletons extends Item{
    public int damage;
    public int defense;
    public int dodge;
    public List<String> info;
    public Exoskeletons(List<String> info) {
        super(info);
        this.info = info;
        this.damage = Integer.parseInt(info.get(3));
        this.defense = Integer.parseInt(info.get(4));
        this.dodge = Integer.parseInt(info.get(5));
    }
    public static Exoskeletons get_copy(Exoskeletons a){
        return new Exoskeletons(a.info);
    }
    public void printInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage: "+this.damage + " defense: "+this.defense+" dodge: "+this.dodge);
    }
    public void StoreInfo(){
        System.out.println(this.name+" "+"level: "+this.level+"  damage: "+this.damage + " defense: "+this.defense+" dodge: "+this.dodge+" Cost: "+this.cost);
    }
}
