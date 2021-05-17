package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.ReclamacaoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
            Page<Reclamacao>reclamacoes = null;
            
            when(reclamacaoService.recuperarReclamacoes(Integer.valueOf(10),Integer.valueOf(0)))
                .thenReturn(reclamacoes);
            ResponseEntity<Page<Reclamacao>> result = reclamacaoController.recuperarReclamacoes(Integer.valueOf(10),Integer.valueOf(0));
            verify(reclamacaoService).recuperarReclamacoes(anyInt(),anyInt());
      }

      @Test
      public void testRecuperarReclamacaoPorId(){
            when(reclamacaoService.recuperarReclamacaoPorId(anyString())).thenReturn(new Reclamacao());

            ResponseEntity<Reclamacao> result = reclamacaoController.recuperarReclamacaoPorId("1");
            verify(reclamacaoService, times(1)).recuperarReclamacaoPorId(anyString());
            Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
      }

      @Test
      public void testAdicionarReclamacao() throws URISyntaxException {
            when(reclamacaoService.inserirReclamacao(any())).thenReturn(Reclamacao.builder().id("00a1").build());
            ResponseEntity result = reclamacaoController.adicionarReclamacao(new Reclamacao(),UriComponentsBuilder.fromUri(new URI("")));
            verify(reclamacaoService, times(1)).inserirReclamacao(any());
      }

      @Test
      public void testAtualizarReclamacao(){
            when(reclamacaoService.atualizarReclamacao(anyString(),any())).thenReturn(new Reclamacao());

            ResponseEntity<Reclamacao> result = reclamacaoController.atualizarReclamacao("1", new Reclamacao());
            verify(reclamacaoService, times(1)).atualizarReclamacao(anyString(),any());
            Assert.assertEquals(ResponseEntity.ok(new Reclamacao()), result);
      }

      @Test
      public void testExcluirReclamacao(){
            ResponseEntity<Void> result = reclamacaoController.excluirReclamacao("1");
            verify(reclamacaoService, times(1)).excluirReclamacao(anyString());
            Assert.assertEquals(ResponseEntity.noContent().build(), result);
      }
}

