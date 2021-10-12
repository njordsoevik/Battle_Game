package battle;

import java.util.Random;

public enum Weapon {
  BARE_HANDED(0,0,1,new AbilityPower(0,0,0,0),1),
  KATANAS(4,6,2,new AbilityPower(0,0,0,0),1),
  BROADSWORD(6,10,1,new AbilityPower(0,0,0,0),1),
  TWO_HANDED_SWORD(8,12,1,new AbilityPower(14,0,0,0),0.5),
  AXE(6,10,1,new AbilityPower(0,0,0,0),1),
  FLAIL(8,12,1,new AbilityPower(0,0,14,0),1/2);

  private int lowerDamage;
  private int upperDamage;
  private int attackTurns;
  private double penalty;
  private Ability requiredAbility;

  Weapon(int lowerDamage, int upperDamage, int attackTurns, Ability ability, double penalty) {
    this.lowerDamage = lowerDamage;
    this.upperDamage = upperDamage;
    this.attackTurns = attackTurns;
    this.requiredAbility = ability;
    this.penalty = penalty;
  }

  public int getAttackTurns() {
    return this.attackTurns;
  }

  public int getLowerDamage() {
    return this.lowerDamage;
  }

  public int getUpperDamage() {
    return this.upperDamage;
  }

  public Ability getAbilityRequired() {
    return this.requiredAbility;
  }

  public double getPenalty() {
    return this.penalty;
  }

}
