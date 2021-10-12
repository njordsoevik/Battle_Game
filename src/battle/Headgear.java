package battle;

/**
 * Gear in form of a belt. Belts can only increase or decrease a player's constitution ability.
 */
public class Headgear extends AbstractGear{
  Headgear(Effect effect, String name, Long seed) {
    super(effect, name, seed);
    Ability subtract = new AbilityPower(-this.ability.getStrength(),
            0,
            -this.ability.getDexterity(),
            -this.ability.getCharisma());
    this.ability = this.ability.addAbility(subtract);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear gear = (AbstractGear) other;
      return gear.equalsHeadgear(this);
    }
    return false;
  }

  @Override
  public boolean equalsHeadgear(Headgear other) {
    return this.ability.equals(other.ability);
  }

  @Override
  public boolean equalsHeadgear() {
    return true;
  }

  @Override
  public int compareTo(Gear o) {
    return ((AbstractGear) o).compareToHeadGear(this);
  }

  @Override
  protected int compareToHeadGear(Gear o) {
    return super.compareToHeadGear(o);
  }

  @Override
  protected int compareToBelt(Gear o) {
    return CompareResult.GREATER.getResult();
  }

  @Override
  protected int compareToFootwear(Gear o) {
    return CompareResult.GREATER.getResult();
  }

  @Override
  protected int compareToPotion(Gear o) {
    return CompareResult.GREATER.getResult();
  }

}
