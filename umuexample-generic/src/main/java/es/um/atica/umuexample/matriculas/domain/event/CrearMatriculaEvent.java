package es.um.atica.umuexample.matriculas.domain.event;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import lombok.Getter;
import es.um.atica.umubus.domain.events.Event;

@Getter
public class CrearMatriculaEvent extends Event {

    private String matriculaId;

    public CrearMatriculaEvent() {
        super();
    }
    
    private CrearMatriculaEvent(String id) {
    	super();
        this.matriculaId = id;
    }

    public static CrearMatriculaEvent of (Matricula matricula) {
        return new CrearMatriculaEvent(matricula.getId());
    }

    @Override
    public String toString() { 
        return super.toString() + String.format("[matriculaId:%s]",matriculaId);
    }
}
