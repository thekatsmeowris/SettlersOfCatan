class AResource {

	private static final int MAX = 19;

	private int resourceAmount;
	
	// Edited & Added
	public AResource(){
		resourceAmount = 0;
	}

	public AResource(int num){
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
	public void give(int amount){
		if(resourceAmount >= amount) 
			resourceAmount -= amount;
		else
			System.out.println("No more of desired resource");
	}

	public void recieve(int amount){
		if(resourceAmount+amount <= MAX) // <= Edited
			resourceAmount += amount;
		else 
			System.out.println("Too many of desired resource");

	}

	public int getResource() {
		// returns number of resource
		return resourceAmount;	
	}
}