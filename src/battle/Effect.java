package battle;

public enum Effect {
  ENHANCE(1),
  DIMINISH(-1);
  private final int EFFECT;


  Effect(int effect) {
    this.EFFECT = effect;
  }

  public int getEffect() {
    return this.EFFECT;
  }

}
