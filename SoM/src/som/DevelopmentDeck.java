package som;

// Deck.java
import devCards.Knight;
import devCards.VictoryPoint;
import java.util.Stack;
import java.util.Collections;
import progressCards.Monopoly;
import progressCards.RoadBuilding;
import progressCards.YearOfPlenty;
public class DevelopmentDeck{


	private Stack<DevelopmentCard> deck;
	private int totalNumber;
        Knight k= new Knight();
        VictoryPoint vp=new VictoryPoint();
        YearOfPlenty yop=new YearOfPlenty();
        Monopoly mp=new Monopoly();
        RoadBuilding rb=new RoadBuilding();
        
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
                Collections.shuffle(deck);
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
