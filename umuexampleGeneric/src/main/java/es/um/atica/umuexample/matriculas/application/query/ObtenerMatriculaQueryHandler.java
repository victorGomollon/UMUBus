package es.um.atica.umuexample.matriculas.application.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.domain.repository.MatriculaReadRepository;
import es.um.atica.umubus.domain.cqrs.QueryHandler;

@Component
public class ObtenerMatriculaQueryHandler implements QueryHandler<Optional<Matricula>,ObtenerMatriculaQuery> {

    @Autowired
    private MatriculaReadRepository matriculasRepository;

    @Override
    public Optional<Matricula> handle(ObtenerMatriculaQuery query) throws Exception {
        return matriculasRepository.findMatricula(query.getMatriculaId());
    }
}
