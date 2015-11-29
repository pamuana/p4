import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;



public class SchedulerDB {
    List<Resource> resources;

    SchedulerDB(){
        resources = new ArrayList<Resource>();
    }

    public boolean addResource(String resource){
        return resources.add(new Resource(resource));
    }

    public boolean removeResource(String r){
        Resource re = findResource(r);
        return resources.remove(re);
    }

    public boolean addEvent(String r, long start, long end, String name,
                            String organization, String description){
        Resource re = findResource(r);
        return re.addEvent(new Event(start, end, name, r, organization, description));
    }

    public boolean deleteEvent(long start, String resource){
    	Resource re = findResource(resource);
    	return re.deleteEvent(start);
    }

    public Resource findResource(String r){
        Iterator<Resource> itr = resources.iterator();
        Resource res = null;
        System.out.println("Find Res: Compare to " + r);
        while (itr.hasNext()){
        	Resource temp = itr.next();
        	System.out.println("Itr " + temp.getName());
        	if (temp.getName().equals(r)){
        		res = temp;
        	}
        }
        return res;
    }

    public List<Resource> getResources(){
        return resources;
    }

    public List<Event> getEventsInResource(String resource){
        Iterator<Event> itr = findResource(resource).iterator();
        List<Event> events = new ArrayList<Event>();
        while (itr.hasNext()){
        	events.add(itr.next());
        }
        return events;
    }

    public List<Event> getEventsInRange(long start, long end){
        List<Event> events = new ArrayList<Event>();
        Iterator<Resource> itrRe = resources.iterator();
        while (itrRe.hasNext()){
        	Resource tempRe = itrRe.next();
        	Iterator<Event> itrEv = tempRe.iterator();
        	while(itrEv.hasNext()){
        		Event tempEv = itrEv.next();
        		Event test = new Event(start, end, "test","test","test","test");
        		if (tempEv.overlap(test)){
        			events.add(tempEv);
        		}
        		
        	}
        }
        return events;
    }

    public List<Event> getEventsInRangeInResource(long start, long end, String resource){
    	 List<Event> events = new ArrayList<Event>();
         Resource tempRe = findResource(resource);
         Iterator<Event> itrEv = tempRe.iterator();
         while(itrEv.hasNext()){
        	Event tempEv = itrEv.next();
        	Event test = new Event(start, end, "test","test","test","test");
    		if (tempEv.overlap(test)){
    			events.add(tempEv);
    		}
         	}
         return events;
    }
    
    

    public List<Event> getAllEvents(){
    	List<Event> events = new ArrayList<Event>();
        Iterator<Resource> itrRe = resources.iterator();
        while (itrRe.hasNext()){
        	Resource tempRe = itrRe.next();
        	Iterator<Event> itrEv = tempRe.iterator();
        	while(itrEv.hasNext()){
        		Event tempEv = itrEv.next();
        		events.add(tempEv);
        	}
        }
        return events;
    }

    public List<Event> getEventsForOrg(String org){
    	List<Event> events = new ArrayList<Event>();
        Iterator<Resource> itrRe = resources.iterator();
        while (itrRe.hasNext()){
        	Resource tempRe = itrRe.next();
        	Iterator<Event> itrEv = tempRe.iterator();
        	while(itrEv.hasNext()){
        		Event tempEv = itrEv.next();
        		if (tempEv.getOrganization().equals(org)){
        			events.add(tempEv);
        		}
        	}
        }
        return events;
    }
}
