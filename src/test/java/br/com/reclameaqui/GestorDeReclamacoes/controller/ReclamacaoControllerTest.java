package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.ReclamacaoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
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
      public void testRecuperarReclamacoes(){
            when(reclamacaoService.recuperarReclamacoes()).thenReturn(Arrays.<Reclamacao>asList(new Reclamacao()));
            ResponseEntity<List<Reclamacao>> result = reclamacaoController.recuperarReclamacoes();
            verify(reclamacaoService).recuperarReclamacoes();
            Assert.assertTrue(result.hasBody());
      }

      @Test
      public void testRecuperarReclamacaoPorId(){
            Iterable<Reclamacao> reclamacaos = new ArrayList<>();
            when(reclamacaoService.recuperarReclamacaoPorId(anyString())).thenReturn(reclamacaos);

            ResponseEntity<Iterable<Reclamacao>> result = reclamacaoController.recuperarReclamacaoPorId("1");
            verify(reclamacaoService, times(1)).recuperarReclamacaoPorId(anyString());
            Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
      }

      @Test
      public void testAdicionarReclamacao() {
            ResponseEntity result = reclamacaoController.adicionarReclamacao(new Reclamacao());
            verify(reclamacaoService, times(1)).inserirReclamacao(any());
      }

      @Test
      public void testAtualizarReclamacao(){
            when(reclamacaoService.atualizarReclamacao(any())).thenReturn(new Reclamacao());

            ResponseEntity<Reclamacao> result = reclamacaoController.atualizarReclamacao(new Reclamacao());
            verify(reclamacaoService, times(1)).atualizarReclamacao( any());
            Assert.assertEquals(ResponseEntity.ok(new Reclamacao()), result);
      }

      @Test
      public void testExcluirReclamacao(){
            ResponseEntity<Void> result = reclamacaoController.excluirReclamacao("1");
            verify(reclamacaoService, times(1)).excluirReclamacao(anyString());
            Assert.assertEquals(ResponseEntity.noContent().build(), result);
      }
}

