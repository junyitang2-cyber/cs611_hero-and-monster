import java.util.List;

public class Skill extends Item{

    public int damage;
    public int mana_cost;
    public String type;
    public List<String> info;

    public Skill(String type,List<String> info) {
        super(info);
        this.type = type;
        this.info = info;
        this.damage = Integer.parseInt(info.get(3));
        this.mana_cost = Integer.parseInt(info.get(4));
    }
    public Skill  get_copy(Skill a){
        return new Skill(a.type,a.info);
    }
    public void printInfo(){
        System.out.println(this.type+" "+this.name+" "+"level: "+this.level+"  damage: "+this.damage+ " mana_cost: "+this.mana_cost);
    }
    public void ChurchInfo(){
        System.out.println(this.type+" "+this.name+" "+"level: "+this.level+"  damage: "+this.damage+ " mana_cost: "+this.mana_cost +" Cost: "+this.cost);
    }
}
