package com.example.mini_mundo.impl;

import com.example.mini_mundo.dtos.TarefaDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.model.Tarefa;
import com.example.mini_mundo.repository.TarefaRepository;
import com.example.mini_mundo.service.impl.ProjetoServiceImpl;
import com.example.mini_mundo.service.impl.TarefaserviceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TarefaserviceImplTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private ProjetoServiceImpl projetoServiceImpl;

    @InjectMocks
    private TarefaserviceImpl tarefaserviceImpl;

    @Test
    @DisplayName("Deve cadastrar uma tarefa com sucesso")
    void testCadastrarTarefaComSucesso() {
        TarefaDto tarefaDto = new TarefaDto(
                "Nova Tarefa",
                1L,
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 5),
                null,
                "Concluída"
        );

        Projeto projeto = new Projeto();
        projeto.setId(1L);

        Tarefa savedTarefa = new Tarefa();
        savedTarefa.setId(1L);
        savedTarefa.setDescricao("Nova Tarefa");
        savedTarefa.setProjeto(projeto);
        savedTarefa.setDataInicio(LocalDate.of(2023, 10, 1));
        savedTarefa.setDataFim(LocalDate.of(2023, 10, 5));
        savedTarefa.setStatus("Concluída");

        when(projetoServiceImpl.buscarProjeto(1L)).thenReturn(projeto);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(savedTarefa);

        Tarefa result = tarefaserviceImpl.cadastrarTarefa(tarefaDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Nova Tarefa", result.getDescricao());
        Assertions.assertEquals(1L, result.getProjeto().getId());
        Assertions.assertEquals(LocalDate.of(2023, 10, 1), result.getDataInicio());
        Assertions.assertEquals(LocalDate.of(2023, 10, 5), result.getDataFim());
        Assertions.assertEquals("Concluída", result.getStatus());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar cadastrar uma tarefa com as datas inválidas")
    void testCadastrarTarefaComDatasInvalidas() {
        TarefaDto tarefaDto = new TarefaDto(
                "Tarefa com datas inválidas",
                1L,
                LocalDate.of(2023, 10, 5),
                LocalDate.of(2023, 10, 1),
                null,
                "Não Concluída"
        );

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            tarefaserviceImpl.cadastrarTarefa(tarefaDto);
        });

        Assertions.assertEquals("A data de fim não pode ser anterior à data de início.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve cadastrar uma tarefa com sucesso e com a precessora")
    void testCadastrarTarefaComPrecessora() {
        TarefaDto tarefaDto = new TarefaDto(
                "Tarefa com precessora",
                1L,
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 5),
                1L,
                "Concluída"
        );

        Projeto projeto = new Projeto();
        projeto.setId(1L);

        Tarefa tarefaPredecessora = new Tarefa();
        tarefaPredecessora.setId(1L);
        tarefaPredecessora.setDescricao("Tarefa Predecessora");

        Tarefa savedTarefa = new Tarefa();
        savedTarefa.setDescricao("Tarefa com predecessora");
        savedTarefa.setProjeto(projeto);
        savedTarefa.setDataInicio(LocalDate.of(2023, 10, 1));
        savedTarefa.setDataFim(LocalDate.of(2023, 10, 5));
        savedTarefa.setTarefaPredecessora(tarefaPredecessora);
        savedTarefa.setStatus("Concluída");

        when(projetoServiceImpl.buscarProjeto(1L)).thenReturn(projeto);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefaPredecessora));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(savedTarefa);

        Tarefa result = tarefaserviceImpl.cadastrarTarefa(new TarefaDto(
                "Tarefa com predecessora",
                1L,
                LocalDate.of(2023, 10, 1),
                LocalDate.of(2023, 10, 5),
                1L,
                "Concluída"
        ));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Tarefa com predecessora", result.getDescricao());
        Assertions.assertEquals(1L, result.getProjeto().getId());
        Assertions.assertEquals(LocalDate.of(2023, 10, 1), result.getDataInicio());
        Assertions.assertEquals(LocalDate.of(2023, 10, 5), result.getDataFim());
        Assertions.assertEquals(1L, result.getTarefaPredecessora().getId());
        Assertions.assertEquals("Concluída", result.getStatus());
    }

    @Test
    @DisplayName("Deve excluir uma tarefa com sucesso")
    void testExcluirTarefaComSucesso() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.existsByTarefaPredecessora(tarefa)).thenReturn(false);

        Assertions.assertDoesNotThrow(() -> tarefaserviceImpl.excluirTarefa(1L));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar excluir uma tarefa que é predecessora")
    void testExcluirTarefaComPredecessora() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.existsByTarefaPredecessora(tarefa)).thenReturn(true);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            tarefaserviceImpl.excluirTarefa(1L);
        });

        Assertions.assertEquals("A tarefa não pode ser excluída, pois é predecessora de outra.", exception.getMessage());
    }
}