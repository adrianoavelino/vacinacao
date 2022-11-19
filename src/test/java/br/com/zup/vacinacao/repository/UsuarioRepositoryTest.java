package br.com.zup.vacinacao.repository;

import br.com.zup.vacinacao.entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class UsuarioRepositoryTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve cadastrar usuario")
    void deveCadastrarUsuario() {
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        Usuario usuarioEntity = new Usuario("Adriano",
                "email@email.com",
                "123.456.789-01",
                dataNascimento);
        Usuario usuarioRetorno = new Usuario(usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getCpf(),
                usuarioEntity.getDataNascimento());
        usuarioRetorno.setId(1L);
        Mockito.when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioRetorno);

        usuarioRepository.save(usuarioEntity);

        Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuarioEntity);
    }

    @Test
    @DisplayName("Deve buscar usuario por email")
    void deveBuscarUsuarioPorEmail() {
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        String email = "email@email.com";
        Usuario usuarioEntity = new Usuario("Adriano",
                email,
                "123.456.789-01",
                dataNascimento);
        Usuario usuarioRetorno = new Usuario(usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getCpf(),
                usuarioEntity.getDataNascimento());
        usuarioRetorno.setId(1L);

        Mockito.when(usuarioRepository.findByEmail(email))
                .thenReturn(Optional.of(usuarioRetorno));

        usuarioRepository.findByEmail(email);

        Mockito.verify(usuarioRepository, Mockito.times(1))
                .findByEmail(email);
    }
}
