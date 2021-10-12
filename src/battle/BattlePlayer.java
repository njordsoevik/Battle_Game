package battle;

import java.util.*;

public class BattlePlayer implements Player {

  protected Ability ability;
  private final int GEAR_CHOICE_LIMIT = 20;
  private final int MINIMUM_GEAR = 15;
  private Long seed;
  private Random random;
  private Weapon weapon = Weapon.BARE_HANDED;
  List<Gear> gearList = new ArrayList<>();

  public BattlePlayer(int strength, int constitution, int dexterity, int charisma) {
    this.ability = new AbilityPower(strength,constitution,dexterity,charisma);
    random = new Random();
  }

  public BattlePlayer(int strength, int constitution, int dexterity, int charisma, long seed) {
    this(strength,constitution,dexterity,charisma);
    random.setSeed(seed);
    this.seed=seed;
  }

  @Override
  public void equipBag() {
    if (!gearList.isEmpty()) {
      throw new IllegalStateException("This player already equipped a bag");
    }
    int randomChoice;
    int equippedUnits = 0;
    boolean headgearEquipped = false;
    boolean footwearEquipped = false;
    List<Gear> bag = createBag();
    List<Gear> chosenBag = new ArrayList<>();
    for (int i=0;i<GEAR_CHOICE_LIMIT;i++) {
      randomChoice = random.nextInt(bag.size());
      Gear newGear = bag.get(randomChoice);
      if (newGear.equalsBelt()) { //TODO remove instanceof
        if (equippedUnits+newGear.getUnit()<=10) {
          chosenBag.add(newGear);
          equippedUnits+= newGear.getUnit();
          this.ability=this.ability.addAbility(bag.get(i).getAbility());
        }
      }
      else if (newGear.equalsPotion()) {
        chosenBag.add(newGear);
        this.ability=this.ability.addAbility(bag.get(i).getAbility());
      }
      else if (newGear.equalsHeadgear()) {
        if (!headgearEquipped) {
          chosenBag.add(newGear);
          headgearEquipped=true;
          this.ability=this.ability.addAbility(bag.get(i).getAbility());
        }
      }
      else if (newGear.equalsFootwear()) {
        if (!footwearEquipped) {
          chosenBag.add(newGear);
          footwearEquipped=true;
          this.ability=this.ability.addAbility(bag.get(i).getAbility());
        }
      }
      bag.remove(newGear);
    }
    Gear[] gearArray = new Gear[chosenBag.size()];
    for (int i=0; i<chosenBag.size(); i++) {
      gearArray[i] = chosenBag.get(i);
    }
    Arrays.sort(gearArray);
    this.gearList = Arrays.asList(gearArray);


  }

  private List<Gear> createBag() {
    GearFactory factory = new GearFactory();
    List<Gear> bag = new ArrayList<>();
    for (GearFactory.GearType type:GearFactory.GearType.values()){
      for (Effect effect: Effect.values()) {
        int percent;
        if (effect==Effect.DIMINISH) {
          percent = MINIMUM_GEAR / 4;
        } else {
          percent = (3*MINIMUM_GEAR)/4;
        }
        for (int i = 0; i<percent;i++) {
          bag.add(factory.getGear(type,
                  String.format("%s %d: %s",type.toString(),i,effect.toString()),
                  effect,seed));
        }
      }
    }
    return bag;
  }

  @Override
  public void requestWeapon() {
    if (this.weapon!=Weapon.BARE_HANDED) {
      throw new IllegalStateException("This player already equipped a weapon");
    }
    Weapon[] weapons = Weapon.values();
    while (this.weapon==Weapon.BARE_HANDED) {
      this.weapon = weapons[random.nextInt(weapons.length)];
    }
  }

  @Override
  public Weapon getWeapon() {
    return this.weapon;
  }

  @Override
  public List<Gear> getGear() {
    List<Gear> description = new ArrayList<>();
    if (!gearList.isEmpty()) {
      for (Gear gear : gearList) { // Add gear from gear list
        description.add(gear);
      }
    }
    return description;
  }

  @Override
  public List<List<String>> getDescription() {
    List<List<String>> description = new ArrayList<>();
    if (!gearList.isEmpty()) {
      for (Gear gear:gearList) { // Add gear from gear list
        List<String> row = new ArrayList<>();
        row.add(gear.getName());
        row.add(gear.getAbility().toString());
        description.add(row);
      }
    }
    List<String> summary = new ArrayList<>(); // Add total attributes
    summary.add("Player's Total Temporary Ability");
    summary.add(this.ability.toString());
    description.add(summary);

    List<String> weaponRow = new ArrayList<>(); // Add weapon
    weaponRow.add("Player Weapon");
    weaponRow.add(this.weapon.toString());
    description.add(weaponRow);
    return description;
  }

  public int getAttackTurns() {
    return this.weapon.getAttackTurns();
  }

  public int getAttackDamage() {
    return getWeaponDamage() + this.ability.getStrength();
  }

  public int getStrikingPower() {
    return this.ability.getStrength()+(random.nextInt(10)+1);
  }

  public int getAvoidance() {
    return this.ability.getDexterity()+(random.nextInt(6)+1);
  }

  public Ability getAbility() {
    return this.ability;
  }

  private int getWeaponDamage() {
    double penalty;
    if (this.getAbility().checkOverPenalty(weapon.getAbilityRequired())) {
      penalty = 1;
    } else {
      penalty = weapon.getPenalty();
    }
    int weaponDamage;
    weaponDamage = (int) (randomizeDamage() * penalty);
    return weaponDamage;
  }

  private int randomizeDamage() {
    int upperDamage = this.getWeapon().getUpperDamage();
    int lowerDamage = this.getWeapon().getLowerDamage();
    int range = random.nextInt(upperDamage-lowerDamage+1);
    return this.getWeapon().getLowerDamage() + range;
  }

}
