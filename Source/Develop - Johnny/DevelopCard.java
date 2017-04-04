// DevelopCard.java
public class DevelopCard{
	private String name;
	private String description;
	private String imgName;

	public DevelopCard(String n, String d, String imgName){
		setName(n);
		setDescription(d);
		setImage(imgName);
	}

	public void setName(String n){
		name = n;
	}

	public void setDescription(String d){
		description = d;
	}

	public void setImage(String img){
		imgName = img;
	}

	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}

	public String getImage(){
		return imgName;
	}

	public String toString(){
		return "(" + getName() + ", " + getDescription() + ", " + getImage() +")";	
	}
	

}
