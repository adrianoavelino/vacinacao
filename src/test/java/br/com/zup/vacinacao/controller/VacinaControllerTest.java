package br.com.zup.vacinacao.controller;

import br.com.zup.vacinacao.controller.dto.VacinaDto;
import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.service.VacinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(VacinaController.class)
class VacinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacinaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve cadastrar vacina com status created")
    void deveCadastarVacinaComStatusCreated() throws Exception {
        VacinaDto vacinaDto = new VacinaDto();
        vacinaDto.setNome("Coronavac");

        Vacina vacina = VacinaDto.converter(vacinaDto);
        Vacina vacinaRetorno = new Vacina(vacinaDto.getNome());
        vacinaRetorno.setId(1L);
        Mockito.when(service.save(vacina)).thenReturn(vacinaRetorno);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/vacinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacinaDto));
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn();
    }
}
