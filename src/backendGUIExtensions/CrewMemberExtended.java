package backendGUIExtensions;

import backend.CrewClass;
import backend.CrewMember;

public class CrewMemberExtended extends CrewMember {

	private String image;
	
	public CrewMemberExtended(String name, CrewClass memberClass, String image) {
		super(name, memberClass);
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}
	
}
