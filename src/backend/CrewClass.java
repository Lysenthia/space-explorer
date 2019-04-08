package backend;

public enum CrewClass {
	GUARD("Guard"),
	PILOT("Pilot"),
	SCOUT("Scout"),
	ENGINEER("Shields Engineer"),
	MERCHANT("Merchant"),
	MEDIC("Medic");
	
	private final String className;
	
	private CrewClass(String name) {
		this.className = name;
	}
	
	public String getClassName() {
		return this.className;
	}
	
}