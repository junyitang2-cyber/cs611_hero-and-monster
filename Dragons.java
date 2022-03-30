import java.util.List;

public class Dragons extends Monster{
    public int HP;
    public Dragons(List<String> list) {
        super(list);
        HP = this.level*1200;
    }
    public void printInfo(){
        System.out.println("Spirits: "+this.name+" level: "+this.level+"HP: "+this.HP);
    }
    public int attack(){
        return this.damage;
    }
}
