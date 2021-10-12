package battle;

import java.util.Random;

/**
 * Gear in form of a belt. Belts can only increase or decrease two distinct abilities.
 */
public class Belt extends AbstractGear {
  private final Size size;

  protected enum Size {
    SMALL(1),
    MEDIUM(2),
    LARGE(4);
    private final int SIZE;

    Size(int size) {
      this.SIZE = size;
    }

  }

  public Belt(Effect effect, String name, Long seed) {
    super(effect, name, seed);

    int strengthHolder = (-1)*this.ability.getStrength();
    int constitutionHolder = (-1)*this.ability.getConstitution();
    int dexterityHolder = (-1)*this.ability.getDexterity();
    int charismaHolder = (-1)*this.ability.getCharisma();

    int holder=-1;
    int i=-1;
    for (int j = 0; j < 2; j++) {
      while (holder==i) {
        i = random.nextInt() % 4;
      }
      if (i == 0) {
        strengthHolder = 0;
        holder = i;
      } else if (i == 1) {
        constitutionHolder = 0;
        holder = i;
      } else if (i == 2) {
        dexterityHolder = 0;
        holder = i;
      } else {
        charismaHolder = 0;
        holder = i;
      }

    }
    Ability subtract = new AbilityPower(strengthHolder, constitutionHolder, dexterityHolder,
            charismaHolder);
    this.ability = this.ability.addAbility(subtract);
    Size[] sizes = Size.values();
    this.size = sizes[random.nextInt(sizes.length)];
  }

  @Override
  public int getUnit() {
    return this.size.SIZE;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractGear) {
      AbstractGear gear = (AbstractGear) other;
      return gear.equalsBelt(this);
    }
    return false;
  }

  @Override
  public boolean equalsBelt(Belt other) {
    return this.ability.equals(other.ability) && this.getUnit() == other.getUnit();
  }

  @Override
  public int compareTo(Gear o) {
    return ((AbstractGear) o).compareToBelt(this);
  }

  @Override
  protected int compareToBelt(Gear o) {
    return super.compareTo(o);
  }

  @Override
  protected int compareToFootwear(Gear o) {
    return CompareResult.GREATER.getResult();
  }

  @Override
  public boolean equalsBelt() {
    return true;
  }
}
