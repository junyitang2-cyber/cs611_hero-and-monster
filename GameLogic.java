import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {
    // this class used to generate basic logic of input
    //Player
    public static Player player;
    public static Store store;
    public static Church church;
    public static Board board;
    public static List<Spirits> spirits;
    public static List<Dragons> dragons;

    //scanner for input
    static  Scanner scanner = new Scanner(System.in);
    //boolean for check is game running
    public static boolean isrunning;

    //get user input from console
    public static int ReadInput(String text, int userChoices){
        int input = 0;
        do{
            System.out.println(text);
            try{
                input = Integer.parseInt(scanner.next());
                if(input < 1||input>userChoices){
                    System.out.println("Please input a valid num!");
                }
            }catch (Exception e){
                input = -1;
                System.out.println("It's time to make your valid choice now!");
            }
        }while (input < 1 || input>userChoices);

        return  input;
    }

    //to clear the whole board
    public static void clear(){
        for(int i = 0;i<50;i++)
            System.out.println(" ");
    }

    //to separate contents apart
    public static void printSeparator(){
        for(int i = 0 ;i<100;i++)
            System.out.print("-");
        System.out.println();
    }

    //to print heading
    public static void printHeading(String title){
        printSeparator();
        System.out.println(title);
        printSeparator();
    }

    //stop the game until get input
    public static void Continue(){
        System.out.println("\nEnter anything to continue!");
        scanner.next();
    }
    public static List<Spirits> loadSpirits() throws FileNotFoundException {
        List<Spirits> spirits = new ArrayList<>();

        File file = new File("src/Spirits.txt");
        Scanner reader = new Scanner(file);
        int index = 0;
        while (reader.hasNextLine()){
            String cur = reader.nextLine();
            if(index == 0){
                index++;
                continue;
            }else{
                List<String> list = new ArrayList<>();
                String[] temp = cur.split(" ");
                for(String item:temp){
                    if(item != ""){
                        list.add(item);
                    }
                }
                spirits.add(new Spirits(list));
            }
        }
        return  spirits;
    }
    public static List<Dragons> loadDragons() throws FileNotFoundException {
        List<Dragons> dragons = new ArrayList<>();
        File file = new File("src/Dragons.txt");
        Scanner reader = new Scanner(file);
        int index = 0;
        while (reader.hasNextLine()){
            String cur = reader.nextLine();
            if(index == 0){
                index++;
                continue;
            }else{
                List<String> list = new ArrayList<>();
                String[] temp = cur.split(" ");
                for(String item:temp){
                    if(item != ""){
                        list.add(item);
                    }
                }
                dragons.add(new Dragons(list));
            }
        }
        return dragons;
    }
    public static void gameStart() throws FileNotFoundException {
        boolean set = false;
        String[] type = {"Paladins","Sorcerers","Warriors"};
        List<String[]> detail = new ArrayList<>();
        for(String a:type){
            String[] cur = new String[7];
            File file = new File("src/"+a+".txt");
            Scanner reader = new Scanner(file);
            int index = 0;
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                cur[index++] = data;
            }
            detail.add(cur);
        }

        while (!set){
            clear();
            printHeading(Story.start());
            printSeparator();
            System.out.println("Please choose the hero type:");
            for(int i = 0; i < 3;i++)
                System.out.println(String.valueOf(i+1)+": "+type[i]);
            int hero_type = ReadInput("Your story begins here!",3)-1;
            printSeparator();

            printSeparator();
            String[] options = detail.get(hero_type);

            int index = 1;
            for(int i = 0;i<options.length;i++){
                String[] temp;
                if(i == 0 ){
                    temp = options[0].split("/");
                    for(String item:temp){
                        for(int j =item.length();j<21;j++)
                            item+=" ";
                        System.out.print(item);
                    }
                }else{
                    temp = options[i].split(" ");
                    System.out.print(String.valueOf(index)+". ");
                    for(String item:temp){
                        if(item!=""){
                            for(int j =item.length();j<19;j++)
                                item+=" ";
                            System.out.print(item);
                        }
                    }
                    index++;
                }
                System.out.println();
            }
            int hero = ReadInput("Choose your hero!",index-1);
            printSeparator();
            System.out.println("You select "+ type[hero_type] +" "+ detail.get(hero_type)[hero] +".\n Is that correct?");
            System.out.println("1. Yes");
            System.out.println("2. No I would like to change");
            if(ReadInput("",2) == 1){
                set = true;
                List<String> data = new ArrayList<>();
                String[] temp = detail.get(hero_type)[hero].split(" ");
                for(String item:temp){
                    if(item!=""){
                        data.add(item);
                    }
                }
                //player create
                player = new Player(type[hero_type],data);
                //Store generate
                store = new Store(player);
                //Church generate
                church = new Church(player);
                //load Spirits
                spirits = loadSpirits();

                //load Dragons
                dragons = loadDragons();
                //generate board
                board = new Board();
            }
        }
        //set isrunning to true
        isrunning = true;

        //start main loop
        gameloop();
    }

    public static void printMenu(){
        printSeparator();
        System.out.println("1.Heading to world map");
        System.out.println("2.Player info display and equipment management");
        System.out.println("3.Heading to hero equipment Store");
        System.out.println("4.Learn new skills from blessed church");
        System.out.println("5.Exit game");
    }
    //world map
    public static boolean Map(Player player,boolean isrunning){

        while (isrunning){

            printHeading("World map, H stands for hero, S stands for Spirits, D stands for Dragon");
            System.out.println("w:moveup, s:movedown, a:moveleft, d:moveright, q:backTohome");
            board.drawMap();
            String input = scanner.next();
            if(input.toLowerCase(Locale.ROOT).equals("w") )
                board.MoveUp();
            else if(input.toLowerCase(Locale.ROOT).equals("s"))
                board.MoveDown();
            else if(input.toLowerCase(Locale.ROOT).equals("a"))
                board.MoveLeft();
            else if(input.toLowerCase(Locale.ROOT).equals("d"))
                board.MoveRight();
            else if(input.toLowerCase(Locale.ROOT).equals("q")){
                break;
            }else{
                System.out.println("Please give a valid input!");
                continue;
            }
            int detect = board.detect();

            if(detect != 0){
                if(detect == 1){
                    int randomNum = ThreadLocalRandom.current().nextInt(0, spirits.size());
                    Spirits random = spirits.get(randomNum);
                    if(Combat.fight(random,player)){

                    }else{
                        System.out.println("Game over");
                        System.exit(0);
                    }
                }else{
                    int randomNum = ThreadLocalRandom.current().nextInt(0, spirits.size());
                    Spirits random = spirits.get(randomNum);
                    if(Combat.fight(random,player)){
                        System.out.println("You killed dragon and saved the world");
                        System.exit(1);
                    }else{
                        System.out.println("Game over");
                        System.exit(0);
                    }
                }
            }
            board.change();
        }
        return isrunning;
    }
    //get player info
    public static void GetInfo(Player player){
        player.GetInfo();
    }
    //Store
    public static void Store(Player player){
        store.GetInfo();
    }
    //Skill learn
    public static void Learn_skills(Player player){
        church.GetInfo();
    }
    //main loop
    public static void gameloop(){
        while (isrunning){
            printHeading("Welcome home, hero.");
            printMenu();
            int input = ReadInput("What do you want to do next",5);
            switch (input){
                case 1:
                    if(!Map(player,isrunning)){
                        isrunning = false;
                    }
                    break;
                case 2:
                    GetInfo(player);
                    break;
                case 3:
                    Store(player);
                    break;
                case 4:
                    Learn_skills(player);
                    break;
                case 5:
                    isrunning = false;
                    break;
            }
        }
    }
}
