package monopoly;

import org.ini4j.*;

import java.io.File;
import java.io.IOException;

public class TravelBlock implements Block {
	private final int cost;
	private final String name;
	private final int pos;
  //private final TravelBlock[] others;
	//private int numOwned;  //number of travelBlock owned by its owner
	private Player owner;
  private boolean festivalStatus;
	private boolean owned;  //is property owned?

	//constructor
	public TravelBlock(String name, int pos) throws IOException {
    Wini ini = new Wini(new File("..\\config.ini"));
    cost = Integer.parseInt(ini.get("travel", "buy").toString());
    //int N = Integer.parseInt(ini.get("travel", "size").toString());
    //others = new TravelBlock[N - 1];
    owned = false;
    festivalStatus = false;
		this.name = name;
		this.pos = pos;
	}

  //public void createGroup(TravelBlock[] travelList) {
    //int i = 0;
    //for (TravelBlock travelBl : travelList) {
      //if (travelBl == this) {
        //continue;
      //} else {
        //others[i] = travelBl;
        //++i;
      //}
    //}
    //others[0] = a;
    //others[1] = b;
    //others[2] = c;
    //others[3] = d;
  //}

	//private void updateOwners() {
    //numOwned = 1;
		//for (TravelBlock r : others){
      //if (r.isOwned() && r.owner().equals(owner)) {
				//numOwned++;
      //}
		//}
	//}

	public int position() {
		return pos;
	}

	public String name() {
		return name;
	}

	//update status of property to owned
	public void purchase(Player player) {
		owned = true;
		owner = player;
    //updateOwners();
	}

	public boolean isOwnable() {
		return true;
	}

  public void setFestival(boolean stt) {
    festivalStatus = stt;
  }

  public boolean getFestival() {
    return festivalStatus;
  }

	//return rent owed
	public int rent() {
    //updateOwners();
    int price = cost * owner.travelSize(); 

		//switch (numOwned) {
			//case 1:
        //price = cost;
        //break;
			//case 2:
        //price = cost * 2;
        //break;
			//case 3:
        //price = cost * 3;
        //break;
			//case 4:
        //price = cost * 4;
        //break;
      //case 5:
        //price = cost * 5;
        //break;
			//default:
				//return 0;
		//}
    if (festivalStatus) 
      return price * 2;
    return price;
	}

	public boolean isOwned() {
		return owned;
	}

	public Player owner() {
		return owner;
	}

	public int cost() {
		return cost;
	}

  public void reset() {
    owned = false;
    owner = null;
    festivalStatus = false;
  }

	public String toString() {
    if (festivalStatus) 
      return name + " - Festival";
		return name;
	}
}
