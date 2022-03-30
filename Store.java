import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {
    public String[] type ;
    public String[] arm_attribute;
    static List<Armory> arm_list;
    public String[] weapon_attribute;
    static List<Weaponry> weapon_list;
    public String[] exo_attribute;
    static List<Exoskeletons> exo_list;
    static Player player;
    public Store(Player player) throws FileNotFoundException {
        this.player = player;
        this.type = new String[]{"Armory", "Weaponry", "Exoskeletons"};
        this.arm_list = new ArrayList<>();
        this.weapon_list = new ArrayList<>();
        this.exo_list = new ArrayList<>();
        for(String item:type)
            getData(item);
    }
    //use Overloading to buy different type of item
    public static void buy(Weaponry item, Player buyer,int index){
        if(buyer.money<item.cost){
            System.out.println("You cannot afford this item!");
        }else{
            System.out.println("Wise choice!");
            buyer.money -= item.cost;
            buyer.weapon.add(weapon_list.remove(index));
        }
    }
    public static void buy(Armory item, Player buyer,int index){
        if(buyer.money<item.cost){
            System.out.println("You cannot afford this item!");
        }else{
            System.out.println("Wise choice!");
            buyer.money -= item.cost;
            buyer.armory.add(arm_list.remove(index));
        }
    }
    public static void buy(Exoskeletons item, Player buyer,int index){
        if(buyer.money<item.cost){
            System.out.println("You cannot afford this item!");
        }else{
            System.out.println("Wise choice!");
            buyer.money -= item.cost;
            buyer.exoskeletons.add(exo_list.remove(index));
        }
    }
    //read data from text
    public void getData(String type) throws FileNotFoundException {
        File file = new File("src/"+type+".txt");
        Scanner reader = new Scanner(file);
        int index = 0;
        while (reader.hasNextLine()){
            String cur = reader.nextLine();
            if(index == 0){
                if(type == "Armory")
                    this.arm_attribute = cur.split("/");
                else if(type == "Weaponry")
                    this.weapon_attribute = cur.split("/");
                else
                    this.exo_attribute = cur.split("/");
            }else{
                List<String> list = new ArrayList<>();
                String[] temp = cur.split(" ");
                for(String item:temp){
                    if(item != "")
                        list.add(item);
                }
                if(type == "Armory")
                    this.arm_list.add(new Armory(list));
                else if(type == "Weaponry")
                    this.weapon_list.add(new Weaponry(list));
                else
                    this.exo_list.add(new Exoskeletons(list));
            }
            index++;
        }
    }
    public static void display(int input){
        boolean isSet = false;
        while (!isSet){
            System.out.println("Your current money: "+player.money);
            int index = 1;
            if (input == 1){
                if(arm_list.size() != 0){
                    for(Armory item:arm_list){
                        System.out.print(index+".");
                        item.StoreInfo();
                        index++;
                    }
                }
            }else if(input == 2){
                if(weapon_list.size() != 0){
                    for(Weaponry item:weapon_list){
                        System.out.print(index+".");
                        item.StoreInfo();
                        index++;
                    }
                }
            }else{
                if(exo_list.size() != 0){
                    for(Exoskeletons item:exo_list){
                        System.out.print(index+".");
                        item.StoreInfo();
                        index++;
                    }
                }
            }
            System.out.println(index+". Exit");
            int a = GameLogic.ReadInput("Please choose the item you want to buy or exit:",index);
            if(a == index)
                isSet = true;
            else{
                if(input == 1){
                    buy(arm_list.get(a-1),player,a-1);
                }else if(input == 2){
                    buy(weapon_list.get(a-1),player,a-1);
                }else{
                    buy(exo_list.get(a-1),player,a-1);
                }

            }
        }


    }
    public static void GetInfo(){
        boolean isSet = false;
        while (!isSet){
            GameLogic.printHeading("Welcome to the hero store:");
            System.out.println("1.Armory");
            System.out.println("2.Weaponry");
            System.out.println("3.Exoskeletons");
            System.out.println("4.Exit");
            int input = GameLogic.ReadInput("Please choose the item type you want view",4);
            if(input == 4)
                isSet = true;
            else{
                display(input);
            }
        }

    }

}
