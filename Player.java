import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Heroes {
    public int HP;
    public List<Skill> skills;
    public List<Weaponry> weapon;
    public List<Armory> armory;
    public List<Exoskeletons> exoskeletons;

    public Weaponry cur_weapon;
    public Armory cur_armory;
    public Exoskeletons cur_exo;
    public Player(String type,List<String> data) {
        super(type,data);
        this.HP = 800*this.level;
        this.skills = new ArrayList<>();
        this.weapon = new ArrayList<>();
        this.armory = new ArrayList<>();
        this.exoskeletons = new ArrayList<>();
    }

    public void GetInfo(){
        boolean isSet = false;
        while (!isSet){
            GameLogic.printHeading("Hero Info:");
            this.printInfo();
            this.printCur_equip();
            GameLogic.printSeparator();
            System.out.println("1.Manage Equipments:");
            System.out.println("2.Exit");
            int input = GameLogic.ReadInput("What do you want to do next?",2);
            if(input == 1)
                this.printEquipment();
            else
                isSet = true;
        }
    }

    public void printInfo(){
        System.out.println("Name and Type: " + this.name + " "+this.type);
        System.out.println("HP: " + this.HP);
        System.out.println("Mana: " + this.mana);
        System.out.println("Level: " + this.level);
        System.out.println("Agility: " + this.agility);
        System.out.println("Dexterity: " + this.dexterity);
        System.out.println("Experience: " + this.experience);
    }
    public void printCur_equip(){
        System.out.println("Current equipment:");
        System.out.print("Armory:");
        if (cur_armory == null) {
            System.out.println(" null");
        } else {
            cur_armory.printInfo();
        }
        System.out.print("Weapon:");
        if (cur_weapon == null) {
            System.out.println(" null");
        } else {
            cur_weapon.printInfo();
        }
        System.out.print("Exoskeletons:");
        if (cur_exo == null) {
            System.out.println(" null");
        } else {
            cur_exo.printInfo();
        }
        System.out.print("Skills:");
        if (skills == null||skills.size() == 0) {
            System.out.println(" null");
        } else {
            for(Skill item:skills)
                System.out.println(item.name+"leve_need:"+item.level+" damage:"+item.damage+" mana_cost:"+ item.mana_cost);
        }
    }
    public void equip(int input){
        int len1 = armory.size();
        int len2 = len1 + weapon.size();
        int len3 = len2 + exoskeletons.size();
        if(input <= len1){
            if(cur_armory!=null)
                armory.add(Armory.get_copy(cur_armory));
            cur_armory = armory.remove(input-1);

        }else if(input <= len2){
            if(cur_weapon != null )
                weapon.add(Weaponry.get_copy(cur_weapon));
            cur_weapon = weapon.remove(input-len1-1);
        }else{
            if(cur_exo != null)
                exoskeletons.add(Exoskeletons.get_copy(cur_exo));
            cur_exo = exoskeletons.remove(input-len2-1);
        }
    }
    //print current equipment
    public void printEquipment(){
        boolean isSet = false;
        while (!isSet){
            GameLogic.printHeading("Equipment Holding:");
            GameLogic.printHeading("Weapon");
            int index = 1;
            if(weapon != null||weapon.size() != 0) {
                for (Weaponry item : weapon) {
                    System.out.print(index + ".");
                    item.printInfo();
                    index++;
                }
            }
            GameLogic.printHeading("Armory");
            if(armory != null||armory.size() != 0){
                for(Armory item:armory){
                    System.out.print(index + ".");
                    item.printInfo();
                    index++;
                }
            }
            GameLogic.printHeading("Exoskeletons");
            if(exoskeletons != null||exoskeletons.size() != 0){
                for(Exoskeletons item:exoskeletons){
                    System.out.print(index + ".");
                    item.printInfo();
                    index++;
                }
            }
            System.out.println(index+"." +"Exit");
            int input = GameLogic.ReadInput("Please choose the item you want to equip or exit:",index);
            if(input == index)
                isSet = true;
            else{
                equip(input);
            }
        }

    }
    @Override
    public int attack() {
        int base_attack = this.level*50 + this.strength;
        int equip = 0;
        if(cur_exo != null)
            equip+=cur_exo.damage;
        if(cur_weapon != null)
            equip+= cur_weapon.damage;

        return base_attack+equip;
    }

    @Override
    public int defend() {
        int base_defend = this.level*50 + this.dexterity;
        int equip = 0;
        if(cur_exo != null)
            equip+=cur_exo.defense;
        if(cur_armory != null)
            equip+=cur_armory.damage_reduction;
        return base_defend+equip;
    }

    @Override
    public boolean run() {
        int base_chance = 5*this.level;
        int equip = 0;
        if(cur_exo != null)
            equip+=cur_exo.dodge;
        if(base_chance + equip > 100)
            return true;
        int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
        if(randomNum>base_chance+equip)
            return false;
        else
            return true;
    }
}
