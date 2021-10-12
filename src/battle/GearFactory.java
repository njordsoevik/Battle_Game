package battle;

public class GearFactory {
  public enum GearType {
    HEADGEAR,
    BELT,
    FOOTWEAR,
    POTION;
  }

  public Gear getGear(GearType type, String name, Effect effect, Long seed) {
    if (type == GearType.HEADGEAR) {
      return new Headgear(effect,name,seed);
    } else if (type == GearType.BELT) {
      return new Belt(effect,name,seed);
    }
    else if (type == GearType.POTION) {
      return new Potion(effect,name,seed);
    }
    else {
      return new Footwear(effect,name,seed);
    }
  }
}
