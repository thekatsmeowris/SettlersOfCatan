// TestDeck.java
class TestDeck {
	public static void main(String[] args) {
		Knight k = new Knight();
		VictoryPoint vp = new VictoryPoint();
		YearOfPlenty yop = new YearOfPlenty();
		Monopoly mp = new Monopoly();
		RoadBuilding rb = new RoadBuilding();
		Deck d = new Deck();

		
		d.addCard(k);
		d.addCard(vp);
		// d.addCard(yop);
		// d.addCard(mp);
		d.addCard(rb);
		while (d.hasCard()){
			DevelopmentCard card = d.drawCard();
			System.out.println(card);	
			if (card instanceof RoadBuilding)
				card.playCard();
		}
	}
}