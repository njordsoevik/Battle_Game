package battle;

/**
 * Gear represents the consumable items a player can use in battle. Classes that implement this interface will vary
 * depending upon the type of consumable it is.
 */
public interface Gear extends Comparable<Gear>{

    /**
     * Get the unit weight of the gear, most commonly used for belts.
     *
     * @return the unit
     */
    int getUnit();

    /**
     * Get the name of the gear. This usually has the format "<Gear-Type>: <Number>"
     *
     * @return the name
     */
    String getName();

    /**
     * Get the ability power of the piece of gear.
     *
     * @return the Ability.
     */
    Ability getAbility();

    /**
     * Returns true if the piece of gear is a belt.
     *
     * @return true or false
     */
    boolean equalsBelt();

    /**
     * Returns true if the piece of gear is headgear.
     *
     * @return true or false
     */
    boolean equalsHeadgear();

    /**
     * Returns true if the piece of gear is footwear.
     *
     * @return true or false
     */
    boolean equalsFootwear();

    /**
     * Returns true if the piece of gear is a potion.
     *
     * @return true or false
     */
    boolean equalsPotion();
}
