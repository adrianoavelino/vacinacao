package br.com.zup.vacinacao.repository;

import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.entity.Vacina;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AplicacaoVacinaRepositoryTest {
    @Mock
    private AplicacaoVacinaRepository aplicacaoVacinaRepository;

    @Test
    @DisplayName("Deve salvar aplicacao da vacina em um usuario")
    void deveSalvarAplicacaoDaVacinaEmUmUsuario() {
        LocalDate dataNascimento = LocalDate.of(1984, 1, 1);
        Vacina vacinaEntity = new Vacina("Coronavac");
        vacinaEntity.setId(1L);
        Usuario usuarioEntity = new Usuario("Adriano",
                "email@email.com",
                "123.456.789-01",
                dataNascimento);
        AplicacaoVacina aplicacaoVacina = new AplicacaoVacina(vacinaEntity, usuarioEntity, LocalDate.now());
        AplicacaoVacina aplicacaoVacinaRetorno = new AplicacaoVacina(vacinaEntity, usuarioEntity, LocalDate.now());
        aplicacaoVacinaRetorno.setId(1L);
        Mockito.when(aplicacaoVacinaRepository.save(aplicacaoVacina)).thenReturn(aplicacaoVacinaRetorno);

        AplicacaoVacina aplicacaoVacinaSalva = aplicacaoVacinaRepository.save(aplicacaoVacina);

        Mockito.verify(aplicacaoVacinaRepository, Mockito.times(1)).save(aplicacaoVacina);
    }
}
