
///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  Scheduler.java
// File:             SchedulerDB.java
// Semester:         367 Fall 2015
//
// Author:           Anupama Bhattacharya abhattachar4@wisc.edu
// CS Login:         anupama
// Lecturer's Name:  Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     josh mcgrath
// Email:            jmcgrath4@wisc.edu
// CS Login:         mcgrath
// Lecturer's Name:  Skrentny
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 * Maintains all the resources and events within them
 *
 * @author Anupama Bhattacharya
 */

public class SchedulerDB {
	List<Resource> resources;//Keeps all the resources

	SchedulerDB() {
		resources = new ArrayList<Resource>();
	}

	/**
	 * Adds a resource to list
	 *
	 * @param String name of new resource
	 */
	public boolean addResource(String resource) {
		return resources.add(new Resource(resource));
	}

	/**
	 * Removes a resource
	 *
	 * @param String name of Resource to be removed
	 * @return boolean of the removal's success
	 */
	public boolean removeResource(String r) {
		Resource re = findResource(r);
		if (re == null) {
			return false;
		}
		return resources.remove(re);
	}

	/**
	 * Creates, and adds a new event to resource
	 *
	 * @param String r, long start, long end, String name, String organization, String description
	 * @return boolean of the success
	 */
	public boolean addEvent(String r, long start, long end, String name, String organization, String description) {
		Resource re = findResource(r);
		if (re == null) {
			return false;
		}
		return re.addEvent(new Event(start, end, name, r, organization, description));
	}

	/**
	 * Deletes event from specific resource
	 *
	 * @param long start, String resource
	 * @return boolean of the success
	 */
	public boolean deleteEvent(long start, String resource) {
		Resource re = findResource(resource);
		if (re == null) {
			return false;
		}
		return re.deleteEvent(start);

	}

	/**
	 * Finds Resource based off of the name given
	 *
	 * @param String name of resource
	 * @return (description of the return value)
	 */
	public Resource findResource(String r) {
		Iterator<Resource> itr = resources.iterator();
		Resource res = null;
		while (itr.hasNext()) {
			Resource temp = itr.next();
			if (temp.getName().equals(r)) {
				res = temp;
			}
		}
		return res;
	}

	/**
	 * Returns list of Resources
	 *
	 * @return list
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * Returns all the events for any given resource
	 *
	 * @param String resource
	 * @return List of events
	 */
	public List<Event> getEventsInResource(String resource) {
		Iterator<Event> itr = findResource(resource).iterator();
		List<Event> events = new ArrayList<Event>();
		while (itr.hasNext()) {
			events.add(0, itr.next());

		}
		return events;
	}

	/**
	 * Returns all events in specific range
	 * @param long start, long end
	 * @return List of events
	 */
	public List<Event> getEventsInRange(long start, long end) {
		List<Event> events = new ArrayList<Event>();
		Iterator<Resource> itrRe = resources.iterator();
		while (itrRe.hasNext()) {

			Resource tempRe = itrRe.next();
			Iterator<Event> itrEv = tempRe.iterator();
			while (itrEv.hasNext()) {
				Event tempEv = itrEv.next();
				//Create test interval
				Event test = new Event(start, end, "test", "test", "test", "test");
				if (!tempEv.overlap(test)) {
					events.add(tempEv);
				}

			}
		}
		return events;
	}

	/**
	 * Returns all events in specific range of specific resource
	 * @param long start, long end, String resource
	 * @return List of events
	 */
	public List<Event> getEventsInRangeInResource(long start, long end, String resource) {
		List<Event> events = new ArrayList<Event>();
		Resource tempRe = findResource(resource);
		Iterator<Event> itrEv = tempRe.iterator();
		while (itrEv.hasNext()) {
			Event tempEv = itrEv.next();
			Event test = new Event(start, end, "test", "test", "test", "test");
			if (!tempEv.overlap(test)) {
				events.add(tempEv);
			}
		}
		return events;
	}

	/**
	 * Returns all events 
	 * @return List of events
	 */
	public List<Event> getAllEvents() {

		List<Event> events = new ArrayList<Event>();
		Iterator<Resource> itrRe = resources.iterator();
		while (itrRe.hasNext()) {
			Resource tempRe = itrRe.next();
			Iterator<Event> itrEv = tempRe.iterator();
			List<Event> miniList = new ArrayList<>();
			while (itrEv.hasNext()) {
				Event tempEv = itrEv.next();
				miniList.add(0, tempEv);
			}
			events.addAll(miniList);
		}
		return events;
	}

	/**
	 * Returns all events in specific organization
	 * @param String organization
	 * @return List of events
	 */
	public List<Event> getEventsForOrg(String org) {

		List<Event> events = new ArrayList<Event>();
		Iterator<Resource> itrRe = resources.iterator();
		while (itrRe.hasNext()) {
			Resource tempRe = itrRe.next();
			Iterator<Event> itrEv = tempRe.iterator();
			while (itrEv.hasNext()) {
				Event tempEv = itrEv.next();
				if (tempEv.getOrganization().equals(org)) {
					events.add(tempEv);
				}
			}
		}
		return events;
	}
}
