package devCards;

// ProgressCards.java
import som.DevelopmentCard;

public class ProgressCard extends DevelopmentCard {

    public ProgressCard() {
        super("ProgressCard", "This is a ProgressCards card", "None pic yet");
    }

    public ProgressCard(String name, String description, String pic) {
        super(name, description, pic);
    }
}
