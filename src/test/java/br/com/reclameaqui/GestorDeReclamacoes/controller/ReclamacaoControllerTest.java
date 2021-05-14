package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.ReclamacaoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReclamacaoControllerTest {
      @Mock
      ReclamacaoService reclamacaoService;
      @InjectMocks
      ReclamacaoController reclamacaoController;

      @Before
      public void setUp() {
            MockitoAnnotations.openMocks(this);
      }

      @Test
      public void testRecuperarReclamacoes() throws Exception {
            when(reclamacaoService.recuperarReclamacoes()).thenReturn(Arrays.<Reclamacao>asList(new Reclamacao()));
            ResponseEntity<List<Reclamacao>> result = reclamacaoController.recuperarReclamacoes();
            verify(reclamacaoService).recuperarReclamacoes();
            Assert.assertTrue(result.hasBody());
      }

      @Test
      public void testRecuperarReclamacaoPorId() throws Exception {
            when(reclamacaoService.recuperarReclamacao(anyLong())).thenReturn(new Reclamacao());

            ResponseEntity<Reclamacao> result = reclamacaoController.recuperarReclamacaoPorId(Long.valueOf(1));
            verify(reclamacaoService, times(1)).recuperarReclamacao(anyLong());
            Assert.assertEquals(ResponseEntity.ok(new Reclamacao()), result);
      }

      @Test
      public void testAdicionarReclamacao() throws Exception {
            ResponseEntity.BodyBuilder result = reclamacaoController.adicionarReclamacao(new Reclamacao());
            verify(reclamacaoService, times(1)).inserirReclamacao(any());
      }

      @Test
      public void testAtualizarReclamacao() throws Exception {
            when(reclamacaoService.atualizarReclamacao(anyLong(), any())).thenReturn(new Reclamacao());

            ResponseEntity<Reclamacao> result = reclamacaoController.atualizarReclamacao(Long.valueOf(1), new Reclamacao());
            verify(reclamacaoService, times(1)).atualizarReclamacao(anyLong(), any());
            Assert.assertEquals(ResponseEntity.ok(new Reclamacao()), result);
      }

      @Test
      public void testExcluirReclamacao() throws Exception {
            ResponseEntity<Void> result = reclamacaoController.excluirReclamacao(Long.valueOf(1));
            verify(reclamacaoService, times(1)).excluirReclamacao(anyLong());
            Assert.assertEquals(ResponseEntity.noContent().build(), result);
      }
}

