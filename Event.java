
///////////////////////////////////////////////////////////////////////////////
//
//Main Class File:  Scheduler.java
//File:             Event.java
//Semester:         367 Fall 2015
//
//Author:           Anupama Bhattacharya abhattachar4@wisc.edu
//CS Login:         anupama
//Lecturer's Name:  Skrentny
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//Pair Partner:     josh mcgrath
//Email:            jmcgrath4@wisc.edu
//CS Login:         mcgrath
//Lecturer's Name:  Skrentny
///////////////////////////////////////////////////////////////////////////////
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event represents events to be held in .
 */
public class Event implements Interval {
	// times are UNIX time
	// start time of the event
	private long start;
	// end time of the event
	private long end;
	// name of the event
	private String name;
	// resource required
	private String resource;
	// org hosting event
	private String organization;
	// description of event
	private String description;

	Event(long start, long end, String name, String resource, String organization, String description) {
		this.start = start;
		this.end = end;
		this.name = name;
		this.resource = resource;
		this.organization = organization;
		this.description = description;
	}

	/**
	 * Returns start long
	 *
	 * @return long time of start of event
	 */
	@Override
	public long getStart() {
		return start;
	}

	/**
	 * Returns end long
	 *
	 * @return end time of start of event
	 */
	@Override
	public long getEnd() {
		return end;
	}

	/**
	 * Returns String name
	 *
	 * @return name of event
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns String resource of event
	 *
	 * @return resource of event
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * Returns String organization of event
	 *
	 * @return organization of event
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * Returns String description of event
	 *
	 * @return description of event
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Compares the time of this interval event to another event
	 *
	 * @param a
	 *            different comparable interval
	 * @return returns positive or negative 1 dependent on the comparison of the
	 *         beginning of the event
	 */
	@Override
	public int compareTo(Interval i) {
		if (this.start < i.getStart()) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Compares whether 2 events start at the same time
	 *
	 * @param the
	 *            other event to compare to
	 * @return boolean of whether 2 events start at the same time
	 */
	public boolean equals(Event e) {
		if (start == e.start) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks to see of events are overlapping
	 *
	 * @param A
	 *            different event interval
	 * @return boolean of whether events overlap
	 */
	@Override
	public boolean overlap(Interval i) {
		if (start - i.getEnd() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Turns the events information to a string
	 *
	 * @return String of the events information
	 */
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy,HH:mm");
		Date dateStart = new Date(start * 1000);
		Date dateEnd = new Date(end * 1000);
		String startString = f.format(dateStart);
		String endString = f.format(dateEnd);
		return name + "\n" + "By: " + organization + "\n" 
				+ "In: " + resource 
				+ "\n" + "Start: " + startString + "\n"
				+ "End: " + endString + "\n" + "Description: " + description;
	}
}
