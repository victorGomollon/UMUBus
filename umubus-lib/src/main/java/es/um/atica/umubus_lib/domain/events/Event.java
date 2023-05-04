package es.um.atica.umubus_lib.domain.events;

public class Event {

    private String type;
    private int version;

    public Event() {
    	this(1);
    }
    
    public Event(int version) {
        this.version = version;
        this.type = String.format(this.getTypeFormat(),this.getClass().getName());
    }

    public String getType() { return this.type; }

    public String toString() {
        return String.format("[version:%s][type:%s]",version, type);
    }

    public String getTypeFormat() {
    	return "events." + version + ".%s";
    }
    
}