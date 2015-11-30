import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event represents events to be held in .
 */
public class Event implements Interval{
    //times are UNIX time
    //start time of the event
    private long start;
    //end time of the event
    private long end;
    //name of the event
    private String name;
    //resource required
    private String resource;
    //org hosting event
    private String organization;
    //description of event
    private String description;

    Event(long start, long end, String name, String resource, String organization, String description){
        this.start = start;
        this.end = end;
        this.name = name;
        this.resource = resource;
        this.organization = organization;
        this.description = description;
    }

    @Override
    public long getStart(){
        return start;
    }

    @Override
    public long getEnd(){
        return end;
    }

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public String getOrganization(){
        return organization;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Interval i) {
        if(this.start < i.getStart()){
            return -1;
        }else{
            return 1;
        }
    }


    public boolean equals(Event e) {
        if(start == e.start){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean overlap(Interval i) {
        if(start - i.getEnd() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
    	SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy,HH:mm");
    	 Date dateStart = new Date(start * 1000);
    	 Date dateEnd = new Date(end * 1000);
    	 
         String startString = f.format(dateStart);
         String endString = f.format(dateEnd);
        return name + "\n" +
                "By: " + organization + "\n"+
                "In: " + resource + "\n" +
                "Start: " + startString + " \n" +
                "End: " + endString + "\n" +
                "Description: " + description;
    }
}
