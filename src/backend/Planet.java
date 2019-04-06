package backend;

public class Planet {
	private boolean hasPart;
	private boolean partFound;
	private boolean searched;
	private String name;
	private String description;
	
	public Planet(String name, String description) {
		this.name = name;
		this.description = description;
		//TODO: Implement part randomiser
		this.hasPart = false;
		this.partFound = false;
		this.searched = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public boolean getHasPart() {
		return this.hasPart;
	}
	
	public boolean getPartFound() {
		return this.partFound;
	}
	
	public boolean getSearched() {
		return this.searched;
	}
	
	public boolean checkForPart() {
		if (this.hasPart) {
			this.partFound = true;
			this.searched = true;
			return true;
		} else {
			this.searched = true;
			return false;
		}
	}

}
