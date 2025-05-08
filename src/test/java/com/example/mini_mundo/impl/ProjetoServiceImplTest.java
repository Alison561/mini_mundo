package com.example.mini_mundo.impl;

import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.repository.ProjetoRepository;
import com.example.mini_mundo.service.impl.ProjetoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProjetoServiceImplTest {


    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoServiceImpl projetoService;

    @Test
    @DisplayName("Deve cadastrar um projeto com sucesso quando os dados estão válidos")
    void deveCadastrarProjetoComSucesso() {
        ProjetoDto projetoDto = new ProjetoDto(
                "Projeto 1",
                "Descrição do projeto 1",
                "Ativo",
                BigDecimal.valueOf(5000)
        );

        Projeto projetoEsperado = new Projeto();
        projetoEsperado.setId(1L);
        projetoEsperado.setNome(projetoDto.nome());
        projetoEsperado.setDescricao(projetoDto.descricao());
        projetoEsperado.setStatus(projetoDto.status());
        projetoEsperado.setOrcamento(projetoDto.orcamento());


        Mockito.when(projetoRepository.existsByNome(eq(projetoDto.nome()))).thenReturn(false);
        Mockito.when(projetoRepository.save(any(Projeto.class))).thenReturn(projetoEsperado);

        Projeto projetoCadastrado = projetoService.cadastrarProjeto(projetoDto);

        assertEquals(projetoEsperado.getId(), projetoCadastrado.getId());
        assertEquals(projetoEsperado.getNome(), projetoCadastrado.getNome());
        assertEquals(projetoEsperado.getDescricao(), projetoCadastrado.getDescricao());
        assertEquals(projetoEsperado.getStatus(), projetoCadastrado.getStatus());
        assertEquals(projetoEsperado.getOrcamento(), projetoCadastrado.getOrcamento());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar um projeto com nome já existente")
    void deveLancarExcecaoQuandoNomeJaExistente() {
        ProjetoDto projetoDto = new ProjetoDto(
                "Projeto 1",
                "Descrição do projeto existente",
                "Inativo",
                BigDecimal.valueOf(1000)
        );

        Mockito.when(projetoRepository.existsByNome(eq(projetoDto.nome()))).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> projetoService.cadastrarProjeto(projetoDto));

        assertEquals("Já existe um projeto com esse nome.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve excluir um projeto com sucesso quando ele não tem tarefas vinculadas")
    void deveExcluirProjetoComSucesso() {
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projeto.setTarefas(List.of());

        Mockito.when(projetoRepository.findById(projetoId)).thenReturn(java.util.Optional.of(projeto));

        projetoService.excluirProjeto(projetoId);

        Mockito.verify(projetoRepository, Mockito.times(1)).delete(projeto);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir um projeto com tarefas vinculadas")
    void deveLancarExcecaoQuandoProjetoPossuiTarefas() {
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projeto.setTarefas(null);

        Mockito.when(projetoRepository.findById(projetoId)).thenReturn(java.util.Optional.of(projeto));

        assertThrows(RuntimeException.class, () -> projetoService.excluirProjeto(projetoId));
    }
}