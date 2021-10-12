package battle;

import java.util.List;

/**
 * Players are created to fight in the battle. Classes that implement this interface will
 * have unique ability scores, weapons, and gear.
 */
public interface Player {

  /**
   * Equip a bag full of gear, which gives the player temporary abilities.
   */
  void equipBag();

  /**
   * Equip a weapon, which enhances the player's attacks.
   */
  void requestWeapon();

  /**
   * Equip a weapon, which enhances the player's attacks.
   */
  List<List<String>> getDescription();


  /**
   * Get the attack damage value of the player, used to calculate the players damage on hit.
   *
   * @return the player's constitution
   */
  int getAttackDamage();

  /**
   * Get the striking power of the player, used to calculate the players damage on hit.
   *
   * @return the player's constitution
   */
  int getStrikingPower();

  /**
   * Get the avoidance of the player, used to calculate the players damage reduction.
   *
   * @return the player's avoidance
   */
  int getAvoidance();

  /**
   * Get the gear of the player.
   *
   * @return the gear.
   */
  List<Gear> getGear();

  /**
   * Get the weapon of the player.
   *
   * @return the gear.
   */
  Weapon getWeapon();


  /**
   * Get the ability power of the player.
   *
   * @return the Ability.
   */
  Ability getAbility();
}
