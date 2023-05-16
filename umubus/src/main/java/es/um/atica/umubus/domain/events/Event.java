package es.um.atica.umubus.domain.events;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Event {

    private String type;
    private int version;
    private boolean isLocal;
	private Map<String, String> metaData;

    public Event() {
    	this(1);
    }
    
    public Event(int version) {
        this.version = version;
        this.type = String.format(this.getTypeFormat(),this.getClass().getName());
        this.isLocal = false;
    }

    public String getType() { return this.type; }

    public String toString() {
        return String.format("[version:%s][type:%s]",version, type);
    }

    public String getTypeFormat() {
    	return "events." + version + ".%s";
    }

    public Map getMetaData() {
    	return metaData;
    }
    
    public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
    
  public void setMetaData(){
	  metaData = new HashMap<>();

      Class<?> clase = this.getClass();
      Field[] atributos = clase.getDeclaredFields();

      try {
          for(Field field : atributos) {
              field.setAccessible(true);
              String name = field.getName();
              String value = (String)field.get(this);
              metaData.put(name, value);
          }
      }catch(Exception e) {
              e.printStackTrace();
      }
  }
    
}