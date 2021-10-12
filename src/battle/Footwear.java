package battle;

/**
 * Gear in form of a belt. Belts can only increase or decrease a player's dexterity ability.
 */
public class Footwear extends AbstractGear{
  Footwear(Effect effect, String name, Long seed) {
    super(effect, name, seed);
    Ability subtract = new AbilityPower(-this.ability.getStrength(),
            -this.ability.getConstitution(),
            0,
            -this.ability.getCharisma());
    this.ability = this.ability.addAbility(subtract);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear gear = (AbstractGear) other;
      return gear.equalsFootwear(this);
    }
    return false;
  }

  @Override
  public boolean equalsFootwear(Footwear other) {
    return this.ability.equals(other.ability);
  }

  @Override
  public boolean equalsFootwear() {
    return true;
  }

  @Override
  public int compareTo(Gear o) {
    return ((AbstractGear) o).compareToFootwear(this);
  }

  @Override
  protected int compareToFootwear(Gear o) {
    return super.compareTo(o);
  }

}
