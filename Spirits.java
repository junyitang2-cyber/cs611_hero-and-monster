import java.util.List;

public class Spirits extends Monster{
    public int HP;
    public Spirits(List<String> list) {
        super(list);
        this.HP = this.level*600;
    }
    public void printInfo(){
        System.out.println("Spirits: "+this.name+" level: "+this.level+"HP: "+this.HP);
    }
    public int attack(){
        return this.damage;
    }
}
