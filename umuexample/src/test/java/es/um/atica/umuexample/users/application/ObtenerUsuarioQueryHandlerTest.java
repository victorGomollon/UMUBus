package es.um.atica.umuexample.users.application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umuexample.users.application.query.ObtenerUsuarioQuery;
import es.um.atica.umuexample.users.application.query.ObtenerUsuarioQueryHandler;
import es.um.atica.umuexample.users.domain.model.Usuario;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
class ObtenerUsuarioQueryHandlerTest {
    
    private static final String ID_USUARIO = "30497182-c376-11ed-afa1-0242ac120002";
    private static final String ID_USUARIO_INEXISTENTE = "30497182-c376-12ed-afa1-0242ac120555";
    
    @Autowired
    private ObtenerUsuarioQueryHandler ObtenerUsuarioQueryHandler;

    @Test
    void obtener_usuario_inexistente() throws Exception {
    	ObtenerUsuarioQuery query = ObtenerUsuarioQuery.of(ID_USUARIO_INEXISTENTE);
    	Optional<Usuario> result = ObtenerUsuarioQueryHandler.handle(query);
    	assertTrue(result.isEmpty());
//        assertThrows(TitulacionNotFoundException.class, () ->{ obtenerAsignaturasQueryHandler.handle(query); });
    }

    @Test
    void obtener_usuario_ok() throws Exception {
    	ObtenerUsuarioQuery query = ObtenerUsuarioQuery.of(ID_USUARIO);
        Optional<Usuario> result = ObtenerUsuarioQueryHandler.handle(query);
        assertTrue(result.isPresent());
        assertEquals(ID_USUARIO, result.get().getId());
    }


}
