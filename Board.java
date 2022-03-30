import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public static String[] map;
    public int pos_x;
    public int pos_y;
    public int ori;
    public Board(){
        this.map= random_generate();
        this.pos_x = 0;
        this.pos_y = 0;
    }
    public void MoveUp(){
        ori = pos_x*4+pos_y;
        map[ori] = " ";
        int x = pos_x-1;
        int index = x*4+pos_y;
        if(x >= 0&&map[index] != "W"){
            pos_x = x;
        }else{
            System.out.println("Please make a valid move");
        }
    }
    public void MoveDown(){
        ori = pos_x*4+pos_y;

        int x = pos_x+1;
        int index = x*4+pos_y;
        if(x < 4 && map[index] != "W"){
            pos_x = x;
        }else{
            System.out.println("Please make a valid move");
        }
    }
    public void MoveLeft(){
        ori = pos_x*4+pos_y;

        int y = pos_y-1;
        int index = pos_x*4+y;
        if(y >= 0 &&map[index] != "W"){
            pos_y = y;
        }else{
            System.out.println("Please make a valid move");
        }
    }
    public void MoveRight(){
        ori = pos_x*4+pos_y;

        int y = pos_y+1;
        int index = pos_x*4+y;
        if(y < 4 && map[index] != "W"){
            pos_y = y;
        }else{
            System.out.println("Please make a valid move");
        }
    }
    public int detect(){
        int type = 0;
        int index = pos_x*4+pos_y;
        if(map[index] == "S")
            return 1;
        else if(map[index] == "D") {
            return 2;
        }
        return type;
    }
    public void change(){
        map[ori] = " ";
        int index = pos_x*4+pos_y;
        map[index] = "H";
    }
    public static void drawMap(){
        for(int i = 0;i<4;i++){
            for(int j = 0;j<4;j++){
                String cur = map[i*4+j];

                System.out.print("|   "+cur+"   |");
            }
            System.out.println();
        }
    }
    public static String[] random_generate(){
        String[] cur = new String[16];
        Arrays.fill(cur," ");
        //fix hero and dragon position
        cur[0] = "H";
        cur[15] = "D";

        //generate Spirits
        for(int i=0;i<3;i++){

            int randomNum = ThreadLocalRandom.current().nextInt(1, 9);
            while (cur[randomNum]!= " ")
                randomNum = ThreadLocalRandom.current().nextInt(1, 9);

            cur[randomNum] = "S";
        }
        //generate wall
        for(int i=0;i<3;i++){
            int randomNum = ThreadLocalRandom.current().nextInt(2, 9);
            while (cur[randomNum]!= " ")
                randomNum = ThreadLocalRandom.current().nextInt(2, 9);

            cur[randomNum] = "W";
        }
        return cur;
    }
}
