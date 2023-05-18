package es.um.atica.umuexample.matriculas.application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculaQuery;
import es.um.atica.umuexample.matriculas.application.query.ObtenerMatriculaQueryHandler;
import es.um.atica.umuexample.matriculas.domain.model.Matricula;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
class ObtenerMatriculaQueryHandlerTest {
    
    private static final String ID_MATRICULA = "10497182-c376-11ed-afa1-0242ac120002";
    private static final String ID_MATRICULA_INEXISTENTE = "10497182-c376-12ed-afa1-0242ac120555";
    
    @Autowired
    private ObtenerMatriculaQueryHandler ObtenerMatriculaQueryHandler;

    @Test
    void obtener_matricula_inexistente() throws Exception {
    	ObtenerMatriculaQuery query = ObtenerMatriculaQuery.of(ID_MATRICULA_INEXISTENTE);
    	Optional<Matricula> result = ObtenerMatriculaQueryHandler.handle(query);
    	assertTrue(result.isEmpty());
//        assertThrows(TitulacionNotFoundException.class, () ->{ obtenerAsignaturasQueryHandler.handle(query); });
    }

    @Test
    void obtener_matricula_ok() throws Exception {
    	ObtenerMatriculaQuery query = ObtenerMatriculaQuery.of(ID_MATRICULA);
        Optional<Matricula> result = ObtenerMatriculaQueryHandler.handle(query);
        assertTrue(result.isPresent());
        assertEquals(ID_MATRICULA, result.get().getId());
    }


}
