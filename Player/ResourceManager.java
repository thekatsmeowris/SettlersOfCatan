// ResourceManager.java

public class ResourceManager {
	private Resource soy;
	private Resource hemp;
	private Resource plastic;
	private Resource glass;
	private Resource steel;  

	public ResourceManager(){
		soy = new Resource();
		hemp = new Resource();
		plastic = new Resource();
		glass = new Resource();
		steel = new Resource();
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
		return soy.getResourceAmount();
	}

	public int getHemp(){
		return hemp.getResourceAmount();
	}

	public int getPlastic(){
		return plastic.getResourceAmount();
	}

	public int getGlass(){
		return glass.getResourceAmount();
	}

	public int getSteel(){
		return steel.getResourceAmount();
	}
}