// Deck.java
import java.util.Stack;
public class Deck{


	public Stack<DevelopCard> deck;
	private int totalNumber;

	public Deck(){
		deck = new Stack<DevelopCard>(); 
		totalNumber = 0;
	}

	public void push(DevelopCard dc){
		deck.push(dc);
		totalNumber++;
	}

	public boolean hasCard(){
		return !deck.empty();
	}

	public DevelopCard drawCard(){
		totalNumber--;
		return deck.pop();
	}

	

	public static void main(String[] args) {
		Knight k = new Knight();
		VictoryPoint vp = new VictoryPoint();
		YearOfPlenty yop = new YearOfPlenty();
		Monopoly mp = new Monopoly();
		RoadBuilding rb = new RoadBuilding();
		Deck d = new Deck();

		
		d.push(k);
		d.push(vp);
		// d.push(yop);
		// d.push(mp);
		// d.push(rb);
		System.out.println(d.drawCard());
		System.out.println(d.hasCard());
	}
	
}