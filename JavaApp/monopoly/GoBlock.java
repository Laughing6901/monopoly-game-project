/**
 * This is a monopoly game for OOP Project
 * Author: Vo Anh Viet
*/
package monopoly;

public class GoBlock implements Block {
  private final int pos;
  private final String name = "Go";

  public GoBlock(int pos) {
    this.pos = pos;
  }

  public int position() {
    return pos;
  }

  public String name() {
    return name;
  }

  public boolean isOwnable() {
    return false;
  }

  public boolean isOwned() {
    return false;
  }

  public int cost() {
    return 0;
  }

  public void purchase(Player player) {
  }

  public int rent() {
    return 0;
  }

  public void setFestival(boolean stt) {
  }

  public boolean getFestival() {
    return false;
  }

  public Player owner() {
    return null;
  }

  public void reset() {
  }

  public String toString() {
    return name;
  }
}
