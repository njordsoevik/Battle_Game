package battle;

/**
 * Abilities are created to hold strength, constitution, dexterity, and charisma values.
 * Classes that implement this interface will have unique ability scores.
 */
public interface Ability {

  /**
   * Add an ability to another ability.
   *
   * @return the ability summed
   */
  Ability addAbility(Ability other);

  /**
   * Get the strength value of an ability.
   *
   * @return the strength value
   */
  int getStrength();

  /**
   * Get the constitution value of an ability.
   *
   * @return the strength value
   */
  int getConstitution();

  /**
   * Get the dexterity value of an ability.
   *
   * @return the strength value
   */
  int getDexterity();

  /**
   * Get the charisma value of an ability.
   *
   * @return the strength value
   */
  int getCharisma();

  /**
   * Check if the ability matches a weapons penalty requirements.
   *
   * @return true or false if over penalty requirements.
   */
  boolean checkOverPenalty(Ability weaponAbility);

  /**
   * Get the max health value from an ability power.
   *
   * @return the max health
   */
  int getMaxHealth();
}
