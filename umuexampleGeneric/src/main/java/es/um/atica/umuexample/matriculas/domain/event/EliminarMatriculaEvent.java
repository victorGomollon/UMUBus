package es.um.atica.umuexample.matriculas.domain.event;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umubus.domain.events.Event;

public class EliminarMatriculaEvent extends Event {

    private String matriculaId;

    public EliminarMatriculaEvent() {
    	super();
    	this.setLocal(true);
    }
    
    private EliminarMatriculaEvent(String id) {
    	super();
        this.matriculaId = id; this.setLocal(true);
    }

    public static EliminarMatriculaEvent of (Matricula matricula) {
        return new EliminarMatriculaEvent(matricula.getId());
    }

    public String getMatriculaId() { return matriculaId; }
    
    @Override
    public String toString() { 
        return super.toString() + String.format("[matriculaId:%s]",matriculaId);
    }

}
