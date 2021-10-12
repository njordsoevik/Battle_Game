import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BattlePlayerTest {

  private Player p1;
  private Player p2;

  @Before
  public void setup() {
    p1 = new BattlePlayer(10,10,10,10,1L);
    p2 = new BattlePlayer(10,10,10,10,2L);
  }

  @Test
  public void equipBagAbilityUpdate() {
    p1.equipBag();
    Ability a = p1.getAbility();
    Assert.assertEquals(22,a.getStrength());
    Assert.assertEquals(10,a.getDexterity());
    Assert.assertEquals(10,a.getCharisma());
    Assert.assertEquals(22,a.getConstitution());
  }

  @Test
  public void equipBagMaximumAmounts() {
    int headCount;
    int footCount;
    int beltCount;
    for (int i = 0;i<100;i++) {
      headCount = 0;
      footCount = 0;
      beltCount = 0;
      Player p3 = new BattlePlayer(10,10,10,10);
      p3.equipBag();
      for (Gear gear:p3.getGear()) {
        if (gear.equalsFootwear()) {
          footCount++;
        }
        if (gear.equalsBelt()) {
          beltCount+= gear.getUnit();
        }
        if (gear.equalsHeadgear()) {
          headCount++;
        }
      }
      Assert.assertTrue(footCount<=1 && headCount <=1 && beltCount<=10);
    }
  }

  @Test
  public void requestWeapon() {
    p1.requestWeapon();
    Assert.assertEquals(Weapon.TWO_HANDED_SWORD,p1.getWeapon());
  }

  @Test(expected = IllegalStateException.class)
  public void requestTwoWeapons() {
    p1.requestWeapon();
    p1.requestWeapon();
  }

  @Test
  public void getDescriptionNoWeaponsOrGear() {
    List<List<String>> description = new ArrayList<>();

    List<String> summary = new ArrayList<>(); // Add total attributes
    summary.add("Player's Total Temporary Ability");
    summary.add(p1.getAbility().toString());
    description.add(summary);

    Assert.assertEquals(description,p1.getDescription());
  }

  @Test
  public void getDescriptionWithOnlyWeapons() {
    List<List<String>> description = new ArrayList<>();
    p1.requestWeapon();

    List<String> summary = new ArrayList<>(); // Add total attributes
    summary.add("Player's Total Temporary Ability");
    summary.add(p1.getAbility().toString());
    description.add(summary);

    List<String> weaponRow = new ArrayList<>(); // Add weapon
    weaponRow.add("Player Weapon");
    weaponRow.add(p1.getWeapon().toString());
    description.add(weaponRow);

    Assert.assertEquals(description,p1.getDescription());
  }

  @Test
  public void getDescriptionWithAllGearAndWeapons() {
    List<List<String>> description = new ArrayList<>();
    p1.equipBag();
    List<Gear> gearList = p1.getGear();
    for (Gear gear:gearList) { // Add gear from gear list
      List<String> row = new ArrayList<>();
      row.add(gear.getName());
      row.add(gear.getAbility().toString());
      description.add(row);
    }
    List<String> summary = new ArrayList<>(); // Add total attributes
    summary.add("Player's Total Temporary Ability");
    summary.add(p1.getAbility().toString());
    description.add(summary);

    List<String> weaponRow = new ArrayList<>(); // Add weapon
    weaponRow.add("Player Weapon");
    weaponRow.add(p1.getWeapon().toString());
    description.add(weaponRow);

    Assert.assertEquals(description,p1.getDescription());
  }

  @Test
  public void getAttackTurns() {
    for (int i =0; i<100;i++) {
      Player t = new BattlePlayer(10,10,10,10);
      t.requestWeapon();
      if (t.getWeapon()!=Weapon.KATANAS) {
        Assert.assertEquals(1,t.getWeapon().getAttackTurns());
      } else {
        Assert.assertEquals(2,t.getWeapon().getAttackTurns());
      }
    }
  }

  @Test
  public void getAttackDamageNoWeapon() {
    int s = p1.getAbility().getStrength();
    Assert.assertEquals(s,p1.getAttackDamage());
  }

  @Test
  public void getAttackDamageWithWeaponPenalty() {
    p1.requestWeapon();
    int s = p1.getAbility().getStrength();
    Assert.assertEquals(Weapon.TWO_HANDED_SWORD,p1.getWeapon());
    Assert.assertEquals(s+5,p1.getAttackDamage());
  }

  @Test
  public void getStrikingPower() {
  }

  @Test
  public void getAvoidance() {
  }

  @Test
  public void getAbility() {
  }
}