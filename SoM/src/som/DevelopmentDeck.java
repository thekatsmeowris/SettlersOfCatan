package som;

// Deck.java
import java.util.Stack;
public class DevelopmentDeck{


	private Stack<DevelopmentCard> deck;
	private int totalNumber;

	public DevelopmentDeck(){
		deck = new Stack<DevelopmentCard>(); 
		totalNumber = 0;
	}

	// Add card to deck
	protected void addCard(DevelopmentCard dc){
		deck.push(dc);
		totalNumber++;
	}


	// Check the deck whether or not is empty
	protected boolean hasCard(){
		return !deck.empty();
	}

	//Get a card from deck
	protected DevelopmentCard drawCard(){
		totalNumber--;
		return deck.pop();
	}

	
	
}