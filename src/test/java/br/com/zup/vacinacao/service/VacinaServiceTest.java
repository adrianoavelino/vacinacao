package br.com.zup.vacinacao.service;

import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.exception.EntityNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VacinaServiceTest {

    @Mock
    private VacinaService vacinaService;

    @Test
    @DisplayName("Deve salvar vacina")
    void deveSalvarVacina() {
        Vacina vacina = new Vacina("Coronavac");
        Vacina vacinaRetorno = new Vacina(vacina.getNome());
        vacinaRetorno.setId(1L);
        Mockito.when(vacinaService.save(vacina)).thenReturn(vacinaRetorno);

        vacinaService.save(vacina);

        Mockito.verify(vacinaService, Mockito.times(1)).save(vacina);
    }

    @Test
    @DisplayName("Deve buscar vacina por nome")
    void deveBuscarVacinaPorNome() {
        String nomeDaVacina = "Coronavac";
        Vacina vacinaRetorno = new Vacina(nomeDaVacina);
        vacinaRetorno.setId(1L);
        Mockito.when(vacinaService.findByNome(nomeDaVacina)).thenReturn(vacinaRetorno);

        vacinaService.findByNome(nomeDaVacina);

        Mockito.verify(vacinaService, Mockito.times(1)).findByNome(nomeDaVacina);
    }

    @Test
    @DisplayName("Deve lancar excecao quando pesquisar por vacina nao cadastrada")
    void deveLancarExcecaoQuandoPesquisarPorVacinaNaoCadastrada() {
        String nomeDaVacina = "Coronavac";
        EntityNotFound exception = new EntityNotFound("Não foi possível encontrar uma vacina com esse nome");
        Mockito.doThrow(exception).when(vacinaService).findByNome(nomeDaVacina);

        Assertions.assertThrows(EntityNotFound.class, ()-> vacinaService.findByNome(nomeDaVacina));
        Mockito.verify(vacinaService, Mockito.times(1)).findByNome(nomeDaVacina);
    }
}
