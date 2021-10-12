import battle.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class BattleTest {

  private Battle b1;
  private Battle b2;

  @Before
  public void setup() {
    b1 = new Battle(1L);
    b2 = new Battle(2L);
  }

  @Test
  public void createPlayer() {
    Player p1 = b1.createPlayer();
    Player p2 = b1.createPlayer();
  }

  @Test(expected = IllegalStateException.class)
  public void createTooManyPlayer() {
    Player p1 = b1.createPlayer();
    Player p2 = b1.createPlayer();
    Player p3 = b1.createPlayer();
  }

  @Test
  public void getAbilitiesNoGear() {
    Player p1 = b1.createPlayer();
    Ability a = p1.getAbility();
    Assert.assertEquals(12,a.getCharisma());
    Assert.assertEquals(12,a.getDexterity());
    Assert.assertEquals(13,a.getConstitution());
    Assert.assertEquals(13,a.getStrength());
  }

  @Test
  public void checkAbilitiesBetween6and18() {
    for (int i=0; i<100; i++) {
      Battle bbb = new Battle();
      Player p1 = bbb.createPlayer();
      Ability a = p1.getAbility();
      Assert.assertTrue(a.getCharisma()>5 && a.getCharisma()<19);
      Assert.assertTrue(a.getConstitution()>5 && a.getConstitution()<19);
      Assert.assertTrue(a.getStrength()>5 && a.getStrength()<19);
      Assert.assertTrue(a.getStrength()>5 && a.getStrength()<19);
    }
  }





  @Test(expected = IllegalStateException.class)
  public void startNoPlayersBattle() {
    b1.startBattle();
  }

  @Test(expected = IllegalStateException.class)
  public void startOnePlayersBattle() {
    Player p1 = b1.createPlayer();
    b1.startBattle();
  }

  @Test
  public void getFirstTurnDazzle() {
    Player p1 = b1.createPlayer();
    Player p2 = b1.createPlayer();
    Assert.assertEquals(p2,b1.getFirstTurnDazzle(p1,p2));
  }

  @Test
  public void startBattleNoGear() {
    b1.createPlayer();
    b1.createPlayer();
    b1.startBattle();
    Map roundStats = new HashMap<>();
    roundStats.put("Round ", 0);
    roundStats.put("Player 1 Health", 50);
    roundStats.put("Player 2 Health", 60);
    roundStats.put("Most dazzling player: ", 2);
    Assert.assertEquals(roundStats, b1.getRoundStats());
  }

  @Test
  public void startRound() {
  }

  @Test
  public void getDamageDone() {
  }

  @Test
  public void getAttacker() {
  }

  @Test
  public void getDefender() {
  }

  @Test
  public void isGameOver() {
  }

  @Test
  public void attack() {
  }

  @Test
  public void getRoundStats() {
  }

  @Test
  public void rematch() {
  }
}