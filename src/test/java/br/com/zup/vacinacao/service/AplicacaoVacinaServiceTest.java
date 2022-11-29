package br.com.zup.vacinacao.service;

import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.entity.Vacina;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class AplicacaoVacinaServiceTest {
    @Mock
    private AplicacaoVacinaService aplicacaoVacinaService;

    @Test
    @DisplayName("Deve salvar aplicacao da vacina")
    void deveSalvarAplicacaoDaVacina() {
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
        Mockito.when(aplicacaoVacinaService.save(aplicacaoVacina)).thenReturn(aplicacaoVacinaRetorno);

        aplicacaoVacinaService.save(aplicacaoVacina);

        Mockito.verify(aplicacaoVacinaService, Mockito.times(1)).save(aplicacaoVacina);
    }
}
