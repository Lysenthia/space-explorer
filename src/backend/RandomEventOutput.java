package backend;

/**
 * Contains a random event that occurred and the crew member involved
 * Should only be used by the RandomEvent class
 * @author hoo42
 *
 */
public class RandomEventOutput{
	public final int event;
	public final CrewMember member;
	
	/**
	 * Constructs a RandomEventOutput with a given event and crew member
	 * @param event the event that occurred
	 * @param member the crew member involved in the event
	 */
	public RandomEventOutput(int event, CrewMember member) {
		this.event = event;
		this.member = member;
	}
}
