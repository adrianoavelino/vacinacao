package br.com.zup.vacinacao.service;

import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.exception.EntityNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {
    @Mock
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve salvar usuario")
    void deveSalvarUsuario() {
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        String email = "email@email.com";
        Usuario usuario = new Usuario("Adriano",
                email,
                "123.456.789-01",
                dataNascimento);
        Usuario usuarioRetorno = new Usuario(usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNascimento());
        usuarioRetorno.setId(1L);
        Mockito.when(usuarioService.save(usuario)).thenReturn(usuarioRetorno);

        usuarioService.save(usuario);

        Mockito.verify(usuarioService, Mockito.times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve buscar usuario por email")
    void deveBuscarUsuarioPorEmail() {
        String email = "email@email.com";
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        Usuario usuarioRetorno = new Usuario("Adriano",
                email,
                "123.456.789-01",
                dataNascimento);
        usuarioRetorno.setId(1L);
        Mockito.when(usuarioService.findByEmail(email)).thenReturn(usuarioRetorno);

        usuarioService.findByEmail(email);

        Mockito.verify(usuarioService, Mockito.times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Deve lancar excecao quando buscar por email usuario nao cadastrado")
    void deveLancarExcecaoQuandoBuscarPorEmailUsuarioNaoCadastrado() {
        String email = "email@email.com";
        EntityNotFound exception = new EntityNotFound("Não foi possível encontrar um usuário utilizando esse email");
        Mockito.doThrow(exception).when(usuarioService).findByEmail(email);

        Assertions.assertThrows(EntityNotFound.class, ()-> usuarioService.findByEmail(email));
        Mockito.verify(usuarioService, Mockito.times(1)).findByEmail(email);
    }
}
