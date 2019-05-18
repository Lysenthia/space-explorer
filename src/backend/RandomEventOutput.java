package backend;

/**
 * Contains a random event that occurred and the crew member involved
 * Should only be used by the RandomEvent class
 * @author hoo42
 * @author rvo16
 */
public class RandomEventOutput{
	
	/**
	 * The type of event that occurred
	 * @see RandomEventTypes
	 */
	public final RandomEventTypes event;
	
	/**
	 * The crew member that was involved in the event.
	 * null if there was no crew member involvement
	 */
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
