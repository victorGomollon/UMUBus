package es.um.atica.umuexample.matriculas.application;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculasQueryHandler;
import es.um.atica.umuexample.matriculas.domain.model.Matricula;
import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculasQuery;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
class ObtenerMatriculasQueryHandlerTest {
    
    @Autowired
    private ObtenerMatriculasQueryHandler ObtenerMatriculasQueryHandler;

    @Test
    void obtener_todos_matriculas() throws Exception {
    	ObtenerMatriculasQuery query = ObtenerMatriculasQuery.of();
        List<Matricula> result = ObtenerMatriculasQueryHandler.handle(query);
        assertFalse(result.isEmpty());
    }
}
