package battle;


public class Driver {
  public static void main(String[] args){

    Battle b = new Battle(1L);
    System.out.println("New battle created! \n");
    Player p1 = b.createPlayer();
    Player p2 = b.createPlayer();
    System.out.println("2 Characters created with base abilities! \n");
    System.out.println("Player 1 Gear Description: "+p1.getDescription());
    System.out.println("Player 2 Gear Description: "+p2.getDescription());
    p1.equipBag();
    p2.equipBag();
    p1.requestWeapon();
    p2.requestWeapon();
    System.out.println("\n Characters are now equipped with a bag and weapon! \n");
    System.out.println("Player 1 Gear Description: "+p1.getDescription());
    System.out.println("Player 2 Gear Description: "+p2.getDescription());
    b.startBattle();
    System.out.println("Battle starting! \n");
    System.out.println(b.getRoundStats());
    b.startRound();
    System.out.println(b.getRoundStats());
    b.startRound();
    System.out.println(b.getRoundStats());
    b.rematch();
    System.out.println(b.getRoundStats());
    b.startRound();
    System.out.println(b.getRoundStats());

    }

}
