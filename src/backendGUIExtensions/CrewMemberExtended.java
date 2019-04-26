package backendGUIExtensions;

import backend.CrewClass;
import backend.CrewMember;

/**
 * Extends the CrewMember class to also store a link to an image
 * @author hoo42
 * @author rvo16
 */
public class CrewMemberExtended extends CrewMember {
	
	/**
	 * The path to the image of the crew member
	 */
	private String image;
	
	/**
	 * Constructs an instance of an extended crew member with a name, class, and path to an image of them
	 * @param name the name of the crew member
	 * @param memberClass the class of the crew member
	 * @param image the path to an image of the crew member
	 */
	public CrewMemberExtended(String name, CrewClass memberClass, String image) {
		super(name, memberClass);
		this.image = image;
	}

	/**
	 * Sets the path to an image of the crew member
	 * @param image the path to an image of the crew member
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Returns the path to an image of the crew member
	 * @return the path to an image of the crew member
	 */
	public String getImage() {
		return this.image;
	}
	
}
