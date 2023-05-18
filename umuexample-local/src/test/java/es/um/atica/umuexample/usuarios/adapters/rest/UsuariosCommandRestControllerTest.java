package es.um.atica.umuexample.usuarios.adapters.rest;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.um.atica.umuexample.SpringConfigurationTest;
import es.um.atica.umuexample.usuarios.adapters.rest.dto.UsuarioDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuariosCommandRestControllerTest extends SpringConfigurationTest {

    private static String UMUEXAMPLE_PATH = "/umuexample/usuario/";
    
    private static final String ID_USUARIO1 = "30497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_USUARIO2 = "30497182-c376-11ed-afa1-0242ac120002";
    private static final String ID_USUARIO_INEXISTENTE = "30497182-c376-11ed-afa1-0242ac120555";
    private static final String ID_USUARIO_INEXISTENTE_UPD = "30427182-c376-11ed-afa1-0242ac120115";

    /**
     * POST
     */
    @Test
    void crea_usuario_ok() throws Exception {
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
    
    @Test
    void crea_usuario_existente() throws Exception {
    	UsuarioDTO usr = UsuarioDTO.builder().id(ID_USUARIO1).name("USUARIO11").age(11).build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .post(UMUEXAMPLE_PATH + ID_USUARIO1)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(usr))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);
    }
    
  /**
  * PUT
  */
    @Test
    void actualizar_nombre_usuario_ok() throws Exception {
    	UsuarioDTO usr = UsuarioDTO.builder().id(ID_USUARIO1).name("UsuarioCambiaNombre").build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .put(UMUEXAMPLE_PATH + ID_USUARIO1)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(usr))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    void actualizar_edad_usuario_ok() throws Exception {
    	UsuarioDTO usr = UsuarioDTO.builder().id(ID_USUARIO1).age(11).build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .put(UMUEXAMPLE_PATH + ID_USUARIO1)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(usr))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    void actualizar_usuario_existente() throws Exception {
    	UsuarioDTO usr = UsuarioDTO.builder().id(ID_USUARIO_INEXISTENTE_UPD).name("USUARIO11").age(11).build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .put(UMUEXAMPLE_PATH + ID_USUARIO_INEXISTENTE_UPD)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(usr))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    /**
     * PUT
     */
       @Test
       void eliminar_usuario_ok() throws Exception {
           MvcResult mvcResult = mvc.perform(
               MockMvcRequestBuilders
               .delete(UMUEXAMPLE_PATH + ID_USUARIO2)
               .accept(MediaType.APPLICATION_JSON_VALUE))
           .andReturn();
           int status = mvcResult.getResponse().getStatus();
           assertEquals(200, status);
       }
       
       @Test
       void eliminar_usuario_inexistente() throws Exception {
           MvcResult mvcResult = mvc.perform(
               MockMvcRequestBuilders
               .delete(UMUEXAMPLE_PATH + ID_USUARIO_INEXISTENTE_UPD)
               .accept(MediaType.APPLICATION_JSON_VALUE))
           .andReturn();
           int status = mvcResult.getResponse().getStatus();
           assertEquals(200, status);
       }
}
