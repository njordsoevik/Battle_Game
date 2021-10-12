package battle;

public final class AbilityPower implements Ability {
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;

  protected AbilityPower(int strength, int constitution, int dexterity, int charisma) {
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;
  }

  public Ability addAbility(Ability other) {
    return new AbilityPower(this.getStrength() + other.getStrength(),
            this.getConstitution() + other.getConstitution(),
            this.getDexterity() + other.getDexterity(),
            this.getCharisma() + other.getCharisma());
  }

  public int getStrength() {
    int protect = strength;
    return protect;
  }

  public int getConstitution() {
    int protect = constitution;
    return protect;
  }

  public int getDexterity() {
    int protect = dexterity;
    return protect;
  }

  public int getCharisma() {
    int protect = charisma;
    return protect;
  }

  @Override
  public String toString() {
    return String.format("Strength: %d, Constitution: %d, Dexterity: %d, Charisma: %d",
            this.strength,this.constitution,this.dexterity,this.charisma);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Ability) {
      Ability ability = (Ability) other;
      return (this.getStrength() == ability.getStrength())
              && (this.getConstitution() == ability.getConstitution())
              && (this.getCharisma() == ability.getCharisma())
              && (this.getDexterity() == ability.getDexterity());
    }
    return false;
  }


  public boolean checkOverPenalty(Ability weaponAbility) {
    if (weaponAbility.getStrength() > 0) {
      return this.getStrength()>weaponAbility.getStrength();
    }
    if (weaponAbility.getConstitution() > 0) {
      return this.getConstitution()>weaponAbility.getConstitution();
    }
    if (weaponAbility.getDexterity() > 0) {
      return this.getDexterity()>weaponAbility.getDexterity();
    }
    if (weaponAbility.getCharisma() > 0) {
      return this.getCharisma()>weaponAbility.getCharisma();
    }
    return true;
  }

  @Override
  public int getMaxHealth() {
    return this.getStrength()+this.getCharisma()+this.getDexterity()
            +this.getConstitution();
  }

}
