package som;

// Deck.java
import java.util.Stack;


import devCards.Knight;
import devCards.VictoryPoint;
import progressCards.Monopoly;
import progressCards.RoadBuilding;
import progressCards.YearOfPlenty;
import java.util.Collections;

public class DevelopmentDeck{


	private Stack<DevelopmentCard> deck;
	private int totalNumber;
        
        
        public DevelopmentDeck(){
            deck=new Stack<>();
            for (int i=0; i<14; i++){
                deck.push(new Knight());
            }
            for (int i=0; i<2; i++){
                deck.push(new YearOfPlenty());
                deck.push(new Monopoly());
                deck.push(new RoadBuilding());
            }
            for (int i=0; i<5; i++){
                deck.push(new VictoryPoint());
            }
            
        }
        
        
        
   
        /*public DevelopmentDeck(int numberOfKnights, int numberOfProgress, int numberOfVP){
            for (int i=0; i<numberOfKnights; i++){
                deck.push(new DevelopmentCard());
            }
            for (int i=0; i<numberOfProgress; i++){
                deck.push(new DevelopmentCard());
            }
            for (int i=0; i<numberOfVP; i++){
                deck.push(new DevelopmentCard());
            }
        
        }*/
	public DevelopmentDeck(int totalNumber){
		deck = new Stack<DevelopmentCard>(); 
		this.totalNumber = totalNumber;
	}

	// Add card to deck
	protected void addCard(DevelopmentCard dc){
		deck.push(dc);
		totalNumber++;
                Collections.shuffle(deck);//shuffles the deck of knight cards 
	}


	// Check the deck whether or not is empty
	protected boolean isEmpty(){
		return deck.empty();
	}

	//Get a card from deck
	protected DevelopmentCard drawCard(){
		totalNumber--;
		return deck.pop();
	}

	
	
}