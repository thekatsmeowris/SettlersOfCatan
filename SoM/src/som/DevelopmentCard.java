package som;

// DevelopmentCard.java
public class DevelopmentCard {

    private String name;
    private String description;
    private String imagePathName;

    public DevelopmentCard() {
        name = "dvCard";
        description = "";
        imagePathName = "";
    }

    public DevelopmentCard(String n, String d, String imgName) {
        setName(n);
        setDescription(d);
        setImage(imgName);
    }

    private void setName(String n) {
        name = n;
    }

    private void setDescription(String d) {
        description = d;
    }

    private void setImage(String img) {
        imagePathName = img;
    }

    private String getName() {
        return name;
    }

    private String getDescription() {
        return description;
    }

    private String getImage() {
        return imagePathName;
    }

    // playCard() & discardCard methods will be override
    public void playCard() {
        return;
    }

    public void discardCard() {
        return;
    }

    public String toString() {
        return "(" + getName() + ", " + getDescription() + ", " + getImage() + ")";
    }

}
