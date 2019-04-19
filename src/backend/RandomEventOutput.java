package backend;

/**
 * Contains a random event that occurred and the crew member involved
 * Should only be used by the RandomEvent class
 * @author hoo42
 * @author rvo16
 */
public class RandomEventOutput{
	public final RandomEventTypes event;
	public final CrewMember member;
	
	/**
	 * Constructs a RandomEventOutput with a given event and crew member
	 * @param event the event that occurred
	 * @param member the crew member involved in the event
	 */
	public RandomEventOutput(RandomEventTypes event, CrewMember member) {
		this.event = event;
		this.member = member;
	}
}
