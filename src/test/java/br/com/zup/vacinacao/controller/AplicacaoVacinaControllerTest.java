package br.com.zup.vacinacao.controller;

import br.com.zup.vacinacao.controller.dto.AplicacaoVacinaDto;
import br.com.zup.vacinacao.controller.dto.UsuarioDto;
import br.com.zup.vacinacao.controller.dto.VacinaDto;
import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.service.AplicacaoVacinaService;
import br.com.zup.vacinacao.service.UsuarioService;
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

import java.time.LocalDate;

@WebMvcTest(AplicacaoVacinaController.class)
class AplicacaoVacinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacinaService vacinaService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private AplicacaoVacinaService aplicacaoVacinaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve cadastrar aplicacao de vacina com status created")
    void deveCadastrarAplicacaoDeVacinaComStatusCreated() throws Exception {
        Vacina vacina = new Vacina("Coronavac");
        Vacina vacinaRetorno = new Vacina();
        vacinaRetorno.setNome(vacina.getNome());
        vacinaRetorno.setId(1L);
        Mockito.when(vacinaService.findByNome(vacina.getNome())).thenReturn(vacinaRetorno);

        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        Usuario usuario = new Usuario("Adriano",
                "email@email.com",
                "123.456.789-01",
                dataNascimento);
        Usuario usuarioRetorno = new Usuario();
        usuarioRetorno.setId(1L);
        usuarioRetorno.setEmail(usuario.getEmail());
        usuarioRetorno.setNome(usuario.getNome());
        usuarioRetorno.setCpf(usuario.getCpf());
        usuarioRetorno.setDataNascimento(dataNascimento);
        Mockito.when(usuarioService.findByEmail(usuario.getEmail())).thenReturn(usuarioRetorno);

        AplicacaoVacinaDto aplicacaoVacinaDto = new AplicacaoVacinaDto();
        LocalDate dataAplicacaoVacina = LocalDate.now();
        aplicacaoVacinaDto.setData(dataAplicacaoVacina);
        aplicacaoVacinaDto.setNomeVacina(vacinaRetorno.getNome());
        aplicacaoVacinaDto.setEmail(usuarioRetorno.getEmail());
        AplicacaoVacina aplicacaoVacina = AplicacaoVacinaDto.converter(aplicacaoVacinaDto, vacinaService, usuarioService);
        aplicacaoVacina.setId(1L);

        AplicacaoVacina aplicacaoVacinaRetorno = new AplicacaoVacina();
        aplicacaoVacinaRetorno.setId(1L);
        aplicacaoVacinaRetorno.setData(dataAplicacaoVacina);
        aplicacaoVacinaRetorno.setUsuario(usuarioRetorno);
        aplicacaoVacinaRetorno.setVacina(vacinaRetorno);
        Mockito.when(aplicacaoVacinaService.save(aplicacaoVacina)).thenReturn(aplicacaoVacinaRetorno);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/aplicacaovacinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aplicacaoVacinaDto));
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }
}
