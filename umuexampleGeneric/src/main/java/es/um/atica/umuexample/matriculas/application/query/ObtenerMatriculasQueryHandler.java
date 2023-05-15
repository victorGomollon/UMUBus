package es.um.atica.umuexample.matriculas.application.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaReadRepository;
import es.um.atica.umubus.domain.cqrs.QueryHandler;

@Component
public class ObtenerMatriculasQueryHandler implements QueryHandler<List<Matricula>,ObtenerMatriculasQuery> {

	@Autowired
    private MatriculaReadRepository matriculasRepository;
	
    @Override
    public List<Matricula> handle(ObtenerMatriculasQuery query) throws Exception {
        return matriculasRepository.findAllMatriculas();
    }
}
