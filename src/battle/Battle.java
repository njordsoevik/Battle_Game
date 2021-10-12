package battle;

import java.util.*;

public class Battle {

  protected Random random;
  private List<Player> players = new ArrayList<>();
  private final int NUMBER_DICE=4;
  private int[] diceRolls;
  private int playerTurn;
  private int p1Health;
  private int p2Health;
  private Map<String, Integer> roundStats;
  private int round = 0;
  private Long seed;

  public Battle() {
    this.random = new Random();
  }

  public Battle(Long seed) {
    this();
    this.random.setSeed(seed);
    this.seed = seed;
  }

  public Player createPlayer() {
    if (players.size() >= 2) {
      throw new IllegalStateException("No more players can be added");
    }

    int strength = rollDice(NUMBER_DICE);
    int constitution = rollDice(NUMBER_DICE);
    int charisma = rollDice(NUMBER_DICE);
    int dexterity = rollDice(NUMBER_DICE);

    Player player;
    if (seed!=null) {
      player = new BattlePlayer(strength, constitution
              , dexterity, charisma, seed);
    }
    else {
      player = new BattlePlayer(strength, constitution
              , dexterity, charisma);
    }
    this.players.add(player);
    return player;

  }

  private Integer rollDice(int numberDice) {
    diceRolls = new int[numberDice];
    int diceRollsSum = 0;
    int diceRoll;
    for (int i = 0; i < numberDice; i++) {
      diceRoll = random.nextInt(6) + 1;
      while (diceRoll == 1) {
        diceRoll = random.nextInt(6) + 1;
      }
      diceRolls[i]=diceRoll;
    }
    Arrays.sort(diceRolls);
    for (int j = 1; j<diceRolls.length; j++){
      diceRollsSum+=diceRolls[j];
    }
    return diceRollsSum;
  }

  public void startBattle() {
    if (players.size() != 2) {
      throw new IllegalStateException("Need 2 players to start the battle");
    }
    Player player1 = players.get(0);
    Player player2 = players.get(1);
    p1Health = player1.getAbility().getMaxHealth();
    p2Health = player2.getAbility().getMaxHealth();
    roundStats = new HashMap<>();
    roundStats.put("Round ", 0);
    roundStats.put("Player 1 Health", p1Health);
    roundStats.put("Player 2 Health", p2Health);
    playerTurn = players.indexOf(getFirstTurnDazzle(player1, player2));
    roundStats.put("Most dazzling player: ", (playerTurn+1));
  }

  public Player getFirstTurnDazzle(Player p1, Player p2) {
    int p1Charisma = p1.getAbility().getCharisma();
    int p2Charisma = p2.getAbility().getCharisma();
    if (p1Charisma > p2Charisma) {
      return p1;
    } else {
      return p2;
    }
  }

  public void startRound() {
    if (p1Health == 0 || p2Health == 0) {
      throw new IllegalStateException("Must start a new battle before starting a round");
    }
    if (isGameOver()) {
      throw new IllegalStateException("Must start a new battle before starting a round");
    }
    roundStats.clear();
    round += 1;
    roundStats.put("Round", round);
    Player attacker = getAttacker();
    roundStats.put("Attacking Player", players.indexOf(attacker)+1);
    Player defender = getDefender();
    roundStats.put("Defending Player", players.indexOf(defender)+1);
    attack(attacker, defender);

    nextPlayer();

  }

  private int getDamageDone(Player attacker, Player defender) {
    int attackDamage = attacker.getAttackDamage();
    int defendDamage = defender.getAbility().getConstitution();
    int damageDone = attackDamage - defendDamage;
    if (damageDone < 0) {
      damageDone = 0;
    }
    roundStats.put("Damage Done: ", damageDone);
    return damageDone;
  }

  private Player getAttacker() {
    return players.get(playerTurn);
  }

  private Player getDefender() {
    return players.get((playerTurn + 1) % 2);
  }

  private void nextPlayer() {
    playerTurn = (playerTurn + 1) % 2;
  }

  public boolean isGameOver() {
    return (p1Health <= 0 || p2Health <= 0);
  }

  private void attack(Player attacker, Player defender) {
    if (attacker.getStrikingPower() > defender.getAvoidance()) {
      int damageDone = getDamageDone(attacker, defender);
      if (players.indexOf(defender) == 0) {
        p1Health = p1Health - damageDone;
      } else {
        p2Health = p2Health - damageDone;
      }
    } else {
      roundStats.put("Damage done", 0);
    }
  }

  public Map<String, Integer> getRoundStats() {
    return roundStats;
  }

  public void rematch() {
    startBattle();
  }


}
