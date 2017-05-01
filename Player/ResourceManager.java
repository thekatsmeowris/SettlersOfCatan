// ResourceManager.java

public class ResourceManager {
	private AResource soy;
	private AResource hemp;
	private AResource plastic;
	private AResource glass;
	private AResource steel;  

	public ResourceManager(){
		soy = new AResource();
		hemp = new AResource();
		plastic = new AResource();
		glass = new AResource();
		steel = new AResource();
	}

	public void give(String giveResourceName, int amountGive) {
		if (giveResourceName.equals("SOY"))
			soy.give(amountGive);

		else if (giveResourceName.equals("HEMP"))
	 		hemp.give(amountGive);

		else if (giveResourceName.equals("PLASTIC"))
			plastic.give(amountGive);

		else if (giveResourceName.equals("GLASS"))
			glass.give(amountGive);

		else if (giveResourceName.equals("STEEL"))
			steel.give(amountGive);
		
	}

	public void recieve(String recieveResourceName, int amountRecieve) {

		if (recieveResourceName.equals("SOY"))
			soy.recieve(amountRecieve);

		else if (recieveResourceName.equals("HEMP"))
	 		hemp.recieve(amountRecieve);

		else if (recieveResourceName.equals("PLASTIC"))
			plastic.recieve(amountRecieve);

		else if (recieveResourceName.equals("GLASS"))
			glass.recieve(amountRecieve);

		else if (recieveResourceName.equals("STEEL"))
			steel.recieve(amountRecieve);
		
	}

	public String toString(){
		return String.format("%s: %d\n" + "%s: %d\n" + 
			"%s: %d\n" + "%s: %d\n" + "%s: %d\n","Soy", getSoy(), "Hemp", getHemp(),
			"Plastic", getPlastic(), "Glass", getGlass(), "Steel", getSteel()
			);
	}

	public int getSoy(){
		return soy.getResource();
	}

	public int getHemp(){
		return hemp.getResource();
	}

	public int getPlastic(){
		return plastic.getResource();
	}

	public int getGlass(){
		return glass.getResource();
	}

	public int getSteel(){
		return steel.getResource();
	}
}