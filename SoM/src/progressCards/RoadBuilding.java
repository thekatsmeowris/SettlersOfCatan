package progressCards;

// RoadBuilding.java
import som.DevelopmentCard;

public class RoadBuilding extends DevelopmentCard {

    public RoadBuilding() {
        super("RoadBuilding", "This is a RoadBuilding card", "None pic yet");
    }

    public void playCard() {
        super.playCard();
        System.out.println("Overrided!");
    }
}
