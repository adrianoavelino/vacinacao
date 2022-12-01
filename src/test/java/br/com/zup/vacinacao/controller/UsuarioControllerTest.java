package br.com.zup.vacinacao.controller;

import br.com.zup.vacinacao.controller.dto.UsuarioDto;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.service.UsuarioService;
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


@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve cadastrar usuario com status created")
    void deveCadastrarUsuarioComStatusCreated() throws Exception {
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Adriano");
        usuarioDto.setEmail("email@email.com");
        usuarioDto.setCpf("972.140.570-18");
        usuarioDto.setDataNascimento(dataNascimento);

        Usuario usuario = UsuarioDto.converter(usuarioDto);
        Usuario usuarioRetorno = new Usuario(usuarioDto.getNome(),
                usuarioDto.getEmail(),
                usuarioDto.getCpf(),
                usuarioDto.getDataNascimento());
        usuarioRetorno.setId(1L);

        Mockito.when(service.save(usuario)).thenReturn(usuarioRetorno);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDto));
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }
}
