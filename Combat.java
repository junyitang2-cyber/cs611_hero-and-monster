public class Combat {

    public static boolean fight(Spirits e, Player player){
        boolean over = false;
        while (!over){
            GameLogic.printHeading("Fight with " + e.name);
            GameLogic.printSeparator();
            e.printInfo();
            GameLogic.printSeparator();
            player.printInfo();
            GameLogic.printSeparator();
            System.out.println("1.attack");
            System.out.println("2.defend");
            System.out.println("3.run");
            int input = GameLogic.ReadInput("Make your choice",3);
            if(input == 1){
                System.out.println("your attack make "+player.attack()+" damage");
                e.HP-=player.attack();
                System.out.println(e.name+" attack make "+e.attack()+" damage");
                player.HP-=e.attack();
            }else if( input == 2){
                System.out.println("you defend");
                System.out.println(e.name+" attack make "+e.attack()+" damage");
                player.HP -= Math.max(0,e.attack()-player.defend());
            }else if(input == 3){
                if (player.run()){
                    System.out.println("You run away");
                }else{
                    System.out.println(e.name+" attack make "+e.attack()+" damage");
                    player.HP -= Math.max(0,e.attack()-player.defend());
                }
            }
            if(e.HP<= 0){
                System.out.println("You killed "+e.name);
                return true;
            }

            else if(player.HP <= 0){
                System.out.println("You has been killed by "+e.name);
                return false;
            }

        }
        return false;
    }
    public static boolean fight(Dragons e, Player player){
        boolean over = false;
        while (!over){
            GameLogic.printHeading("Fight with" + e.name);
            e.printInfo();
            player.printInfo();
            System.out.println("1.attack");
            System.out.println("2.defend");
            System.out.println("3.run");
            int input = GameLogic.ReadInput("Make your choice",3);
            if(input == 1){
                System.out.println("your attack make "+player.attack()+" damage");
                e.HP-=player.attack();
                System.out.println(e.name+" attack make "+e.attack()+" damage");
                player.HP-=e.attack();
            }else if( input == 2){
                System.out.println("you defend");
                System.out.println(e.name+" attack make "+e.attack()+" damage");
                player.HP -= Math.max(0,e.attack()-player.defend());
            }else if(input == 3){
                if (player.run()){
                    System.out.println("You run away");
                }else{
                    System.out.println(e.name+" attack make "+e.attack()+" damage");
                    player.HP -= Math.max(0,e.attack()-player.defend());
                }
            }
            if(e.HP<= 0){
                System.out.println("You killed "+e.name);
                return true;
            }

            else if(player.HP <= 0){
                System.out.println("You has been killed by "+e.name);
                return false;
            }

        }
        return false;
    }
}
