

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
        return name + "\n" +
                "By: " + organization + "\n"+
                "In: " + resource + "\n" +
                "Start: " + start + " \n" +
                "End: " + end + "\n" +
                "Description: " + description;
    }
}