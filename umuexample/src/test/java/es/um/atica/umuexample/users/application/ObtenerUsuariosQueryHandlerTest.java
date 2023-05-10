package es.um.atica.umuexample.users.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import es.um.atica.umuexample.users.application.query.ObtenerUsuariosQueryHandler;
import es.um.atica.umuexample.users.domain.model.Usuario;
import es.um.atica.umuexample.users.application.query.ObtenerUsuariosQuery;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
class ObtenerUsuariosQueryHandlerTest {
    
    @Autowired
    private ObtenerUsuariosQueryHandler ObtenerUsuariosQueryHandler;

    @Test
    void obtener_todos_usuarios() throws Exception {
    	ObtenerUsuariosQuery query = ObtenerUsuariosQuery.of();
        List<Usuario> result = ObtenerUsuariosQueryHandler.handle(query);
        assertEquals(2, result.size());
    }
}
