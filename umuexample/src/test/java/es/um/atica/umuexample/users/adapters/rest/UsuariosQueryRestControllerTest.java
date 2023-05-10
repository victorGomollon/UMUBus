package es.um.atica.umuexample.users.adapters.rest;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.um.atica.umuexample.SpringConfigurationTest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuariosQueryRestControllerTest extends SpringConfigurationTest {

	private static String UMUEXAMPLE_PATH = "/umuexample/user/";
	
    private static final String ID_USUARIO1 = "30497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_USUARIO_INEXISTENTE = "30497182-c376-11ed-afa1-0242ac120555";
	
    @Test
    void listado_usuario_ok() throws Exception {
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .get(UMUEXAMPLE_PATH)
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    void obtener_usuario_existente() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .get(UMUEXAMPLE_PATH + ID_USUARIO1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
    }

      @Test
      void obtener_usuario_inexistente() throws Exception {
    	  MvcResult mvcResult = mvc.perform(
                  MockMvcRequestBuilders
                  .get(UMUEXAMPLE_PATH + ID_USUARIO_INEXISTENTE)
                  .accept(MediaType.APPLICATION_JSON_VALUE))
              .andReturn();
              int status = mvcResult.getResponse().getStatus();
              assertEquals(404, status);
      }
}
