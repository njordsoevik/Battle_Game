package battle;

import java.util.Objects;
import java.util.Random;


public abstract class AbstractGear implements Gear {
  protected Ability ability;
  private String name;
  protected Random random;
  protected Effect effect;
  private final int EFFECT_SCALE = 4;

  public enum CompareResult {
    GREATER(1), LESSER(-1);

    private int result;

    CompareResult(int i) {
      this.result = i;
    }

    public int getResult() {
      return this.result;
    }
  }

  /**
   * Constructs a consumable piece of gear in terms of its effect (Enhance/Diminish), and name.
   *
   * @param effect The effect this gear has on the wielder.
   * @param name   Name of the gear.
   */
  protected AbstractGear(Effect effect, String name, Long seed) {
    this.random = new Random();
    if (seed != null) {
      random.setSeed(seed);
    }
    this.ability = new AbilityPower((effect.getEffect() * random.nextInt(EFFECT_SCALE)) + 1,
            (effect.getEffect() * random.nextInt(EFFECT_SCALE)) + 1,
            (effect.getEffect() * random.nextInt(EFFECT_SCALE)) + 1,
            (effect.getEffect() * random.nextInt(EFFECT_SCALE)) + 1);
    this.effect = effect;
    this.name = name;
    this.random = random;
  }

  @Override
  public int getUnit() {
    return 0;
  }

  @Override
  public String getName() {
    String protect = this.name;
    return protect;
  }

  @Override
  public Ability getAbility() {
    return this.ability;
  }

  public boolean equalsBelt(){
    return false;
  }
  public boolean equalsHeadgear() {
    return false;
  }
  public boolean equalsFootwear() {
  return false;
  }
  public boolean equalsPotion() {
    return false;
  }
  protected boolean equalsHeadgear(Headgear other) {
    return false;
  }

  /**
   * Determine whether this shape is equal to a rectangle object.
   *
   * @param other the Potion object to which this gear must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsPotion(Potion other) {
    return false;
  }

  /**
   * Determine whether this shape is equal to a rectangle object.
   *
   * @param other the Belt object to which this gear must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsBelt(Belt other) {
    return false;
  }

  /**
   * Determine whether this shape is equal to a rectangle object.
   *
   * @param other the Footwear object to which this gear must be compared
   * @return false by default, subclasses may override
   */
  protected boolean equalsFootwear(Footwear other) {
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getAbility(), this.getUnit());
  }

  @Override
  public int compareTo(Gear o) {
    return this.getName().compareTo(o.getName());
  }

  protected int compareToBelt(Gear o) {
    return CompareResult.LESSER.getResult();
  }

  protected int compareToHeadGear(Gear o) {
    return CompareResult.LESSER.getResult();
  }

  protected int compareToFootwear(Gear o) {
    return CompareResult.LESSER.getResult();
  }

  protected int compareToPotion(Gear o) {
    return CompareResult.LESSER.getResult();
  }


}
