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
import es.um.atica.umuexample.users.adapters.rest.dto.UsuarioDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PruebaUnRestTest extends SpringConfigurationTest  {

	private static String UMUEXAMPLE_PATH = "/umuexample/userEvent/";
	private static final String ID_USUARIO_INEXISTENTE = "30497182-c376-11ed-afa1-0242ac120555";
	
    @Test
    void crea_evento_usuario_ok() throws Exception {
    	UsuarioDTO usr = UsuarioDTO.builder().id(ID_USUARIO_INEXISTENTE).name("USUARIO10").age(10).build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .post(UMUEXAMPLE_PATH + ID_USUARIO_INEXISTENTE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(usr))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

}
