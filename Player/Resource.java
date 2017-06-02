//edited the class name and extended it from polygon for onscreen representation
class Resource {
// extends Polygon {

	private static final int MAX = 19;

	private int resourceAmount;
	
	//edited constructor name to match newly changed class name
	// Edited & Added
	public Resource(){
		resourceAmount = 0;
	}

	public Resource(int num){
		resourceAmount = num;
	}
		
	public void drawResource(int amount){
	 //method to take desired amount of resources from the Bank, provided the desired number is appropriate 
		if(resourceAmount-amount >= 0) resourceAmount -=amount;
		else System.out.println("No more of desired resource");            
	}
		
	public void returnResource(int amount){ 
		//method to return desired amount of resources from the Bank, provided the desired number is appropriate
		if(resourceAmount+amount <= MAX) // <= Edited
		 resourceAmount +=amount;
		else System.out.println("Too many of desired resource");
	}
	

	// Added
	public void give(int amount) throws NoMoreResourceException, EmptyResourceException {
		if(resourceAmount >= amount) 
			resourceAmount -= amount;

		else if (resourceAmount == 0)
			throw new EmptyResourceException("This Resource is empty.");

		else
			throw new NoMoreResourceException("No more of desired resource");
	}

	public void recieve(int amount) throws TooManyResourceException {
		if(resourceAmount+amount <= MAX) // <= Edited
			resourceAmount += amount;
		else 
			throw new TooManyResourceException("Too many of desired resource");

	}
	//Edited this method name for clarity-mg
	public int getResourceAmount() {
		// returns number of resource
		return resourceAmount;	
	}
}