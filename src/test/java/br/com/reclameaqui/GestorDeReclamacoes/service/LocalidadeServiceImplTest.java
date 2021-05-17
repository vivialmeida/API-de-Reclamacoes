package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import br.com.reclameaqui.GestorDeReclamacoes.respository.LocalidadeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LocalidadeServiceImplTest {
      @Mock
      LocalidadeRepository localidadeRepository;
      @InjectMocks
      LocalidadeServiceImpl localidadeServiceImpl;

      @Before
      public void setUp() {
            MockitoAnnotations.openMocks(this);
      }

      @Test
      public void testAdicionarOuAtualizarLocalidade() {
            when(localidadeRepository.save(any())).thenReturn(new Localidade("id", "pais", "estado", "cidade"));
            Localidade result = localidadeServiceImpl.adicionarOuAtualizarLocalidade(new Localidade("id", "pais", "estado", "cidade"));
            Assert.assertEquals(new Localidade("id", "pais", "estado", "cidade"), result);
            verify(localidadeRepository).save(any());
      }
}

