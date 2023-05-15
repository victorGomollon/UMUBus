package es.um.atica.umuexample.matriculas.adapters.rest;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.um.atica.umuexample.SpringConfigurationTest;
import es.um.atica.umuexample.matriculas.adapters.rest.dto.MatriculaDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MatriculasCommandRestControllerTest extends SpringConfigurationTest {

    private static String UMUEXAMPLE_PATH = "/umuexample/matricula/";
    
    private static final String ID_MATRICULA1 = "10497182-c376-11ed-afa1-0242ac220002";
    private static final String ID_MATRICULA2 = "10497182-c376-11ed-afa1-0242ac120002";
    private static final String ID_MATRICULA_INEXISTENTE = "10497182-c376-11ed-afa1-0242ac120555";
    private static final String ID_MATRICULA_INEXISTENTE_UPD = "10427182-c376-11ed-afa1-0242ac120115";

    /**
     * POST
     */
    @Test
    void crea_matricula_ok() throws Exception {
    	MatriculaDTO mat = MatriculaDTO.builder().id(ID_MATRICULA_INEXISTENTE).name("MATRICULA10").asignatura("Telematica").usuario("30497182-c376-11ed-afa1-0242ac220002").build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .post(UMUEXAMPLE_PATH + ID_MATRICULA_INEXISTENTE)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(mat))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    
    @Test
    void crea_matricula_existente() throws Exception {
    	MatriculaDTO mat = MatriculaDTO.builder().id(ID_MATRICULA1).name("MATRICULA11").asignatura("SSOO").usuario("30497182-c376-11ed-afa1-0242ac220002").build();
        MvcResult mvcResult = mvc.perform(
            MockMvcRequestBuilders
            .post(UMUEXAMPLE_PATH + ID_MATRICULA1)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(mat))
            .accept(MediaType.APPLICATION_JSON_VALUE))
        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(409, status);
    }
    
    /**
     * PUT
     */
       @Test
       void eliminar_matricula_ok() throws Exception {
           MvcResult mvcResult = mvc.perform(
               MockMvcRequestBuilders
               .delete(UMUEXAMPLE_PATH + ID_MATRICULA2)
               .accept(MediaType.APPLICATION_JSON_VALUE))
           .andReturn();
           int status = mvcResult.getResponse().getStatus();
           assertEquals(200, status);
       }
       
       @Test
       void eliminar_matricula_inexistente() throws Exception {
           MvcResult mvcResult = mvc.perform(
               MockMvcRequestBuilders
               .delete(UMUEXAMPLE_PATH + ID_MATRICULA_INEXISTENTE_UPD)
               .accept(MediaType.APPLICATION_JSON_VALUE))
           .andReturn();
           int status = mvcResult.getResponse().getStatus();
           assertEquals(200, status);
       }
}
