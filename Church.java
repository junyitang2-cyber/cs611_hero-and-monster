import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Church {
    public Player player;
    public List<Skill> fire_list;
    public List<Skill> lighten_list;
    public List<Skill> ice_list;
    public String[] type;
    public Church(Player player) throws FileNotFoundException {
        this.player = player;
        this.type = new String[]{"FireSpells","LighteningSpells","IceSpells"};
        fire_list = new ArrayList<>();
        lighten_list= new ArrayList<>();
        ice_list = new ArrayList<>();
        for(String item:type)
            get_data(item);
    }
    public void learn(List<Skill> skill_set,int index){
        Skill cur = skill_set.remove(index-1);
        if(player.money<cur.cost){
            System.out.println("You cannot afford to learn this skill.");
        }else{
            player.money-=cur.cost;
            player.skills.add(skill_set.remove(index-1));
            System.out.println("Wise choice! You've learned new skill!");
        }

    }
    public void display(List<Skill> skill_set,int input){
        boolean isSet = false;
        while (!isSet){

            if(input == 1)
                GameLogic.printHeading("FireSpell:");
            else if(input == 2)
                GameLogic.printHeading("LightenSpells:");
            else GameLogic.printHeading("IceSpells:");
            int index = 1;
            if(skill_set.size() == 0){
                System.out.println("Null");
            }else{
                for(Skill item:skill_set){
                    System.out.print(index+". ");
                    item.ChurchInfo();
                    index++;
                }
            }
            System.out.println(index+". Exit");
            int a = GameLogic.ReadInput("Please the spell you want to learn or exit.",index);
            if(a == index)
                isSet = true;
            else
                learn(skill_set,a);
        }

    }
    public void GetInfo(){
        boolean isSet = false;
        while (!isSet){
            GameLogic.printHeading("Welcome to blessed church, hero.");
            System.out.println("Your current money: "+player.money);
            System.out.println("1.FireSpells");
            System.out.println("2.LighteningSpells");
            System.out.println("3.IceSpells");
            System.out.println("4.Exit");
            int input = GameLogic.ReadInput("Please choose the type of spell you want view or exit:",4);
            if(input == 4)
                isSet = true;
            else{
                if(input == 1)
                    display(this.fire_list,input);
                else if(input == 2)
                    display(this.lighten_list,input);
                else
                    display(this.ice_list,input);
            }
        }
    }
    public void get_data(String type) throws FileNotFoundException {
        File file = new File("src/"+type+".txt");
        Scanner reader = new Scanner(file);
        int index = 0;
        while (reader.hasNextLine()){
            String cur = reader.nextLine();
            if(index > 0){
                String[] temp = cur.split(" ");
                List<String> list = new ArrayList<>();
                for(String item:temp){
                    if(item!=""){
                        list.add(item);
                    }
                }
                if(type == "FireSpells")
                    fire_list.add(new Skill(type,list));
                else if(type == "LighteningSpells")
                    lighten_list.add(new Skill(type,list));
                else
                    ice_list.add(new Skill(type,list));

            }
            index++;
        }
    }
}
