package battle;

/**
 * Gear in form of a belt. Potions can increase or decrease all of a player's abilities.
 */
public class Potion extends AbstractGear {
  Potion(Effect effect, String name, Long seed) {
    super(effect, name, seed);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear gear = (AbstractGear) other;
      return gear.equalsPotion(this);
    }
    return false;
  }

  @Override
  public boolean equalsPotion(Potion other) {
    return this.ability.equals(other.ability);
  }

  @Override
  public boolean equalsPotion() {
    return true;
  }

  @Override
  public int compareTo(Gear gear) {
    return ((AbstractGear) gear).compareToPotion(this);
  }

  @Override
  protected int compareToBelt(Gear o) {
    return CompareResult.GREATER.getResult();
  }

  @Override
  protected int compareToFootwear(Gear gear) {
    return CompareResult.GREATER.getResult();
  }

  @Override
  protected int compareToPotion(Gear gear) {
    return super.compareTo(gear);
  }

}


