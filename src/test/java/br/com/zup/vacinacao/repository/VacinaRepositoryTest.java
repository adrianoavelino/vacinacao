package br.com.zup.vacinacao.repository;

import br.com.zup.vacinacao.entity.Vacina;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class VacinaRepositoryTest {
    @Mock
    private VacinaRepository vacinaRepository;

    @Test
    @DisplayName("Deve cadastrar vacina")
    void deveCadastrarVacina() {
        Vacina vacinaEntity = new Vacina("Coronavac");
        Vacina vacinaRetorno = new Vacina(vacinaEntity.getNome());
        vacinaRetorno.setId(1L);
        Mockito.when(vacinaRepository.save(vacinaEntity)).thenReturn(vacinaRetorno);

        vacinaRepository.save(vacinaEntity);

        Mockito.verify(vacinaRepository, Mockito.times(1)).save(vacinaEntity);
    }

    @Test
    @DisplayName("Deve buscar vacina por nome")
    void deveBuscarVacinaPorNome() {
        String nomeDaVacina = "Coronavac";
        Vacina vacinaRetorno = new Vacina(nomeDaVacina);
        vacinaRetorno.setId(1L);
        Mockito.when(vacinaRepository.findByNome(nomeDaVacina))
                .thenReturn(Optional.of(vacinaRetorno));

        vacinaRepository.findByNome(nomeDaVacina);

        Mockito.verify(vacinaRepository, Mockito.times(1))
                .findByNome(nomeDaVacina);
    }
}
