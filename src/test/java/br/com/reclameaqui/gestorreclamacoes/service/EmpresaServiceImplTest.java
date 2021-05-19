package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.dto.EmpresaDTO;
import br.com.reclameaqui.gestorreclamacoes.respository.EmpresaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;

public class EmpresaServiceImplTest {
      @Mock
      EmpresaRepository empresaRepository;
      @InjectMocks
      EmpresaServiceImpl empresaServiceImpl;

      @Before
      public void setUp() {
            MockitoAnnotations.openMocks(this);
      }

      @Test
      public void testAdicionarOuAtualizarEmpresa(){
            when(empresaRepository.save(any())).thenReturn(new Empresa("id", "fantasia", "cnpj"));
            Empresa result = empresaServiceImpl.adicionarOuAtualizarEmpresa(new Empresa("id", "fantasia", "cnpj"));
            Assert.assertEquals(new Empresa("id", "fantasia", "cnpj"), result);
            verify(empresaRepository).save(any());
      }

      @Test
      public void testRecuperarEmpresas(){
            when(empresaRepository.findAll()).thenReturn(Arrays.asList(new Empresa("id", "fantasia", "cnpj")));
            HashSet<EmpresaDTO> result = empresaServiceImpl.recuperarEmpresas();
            Assert.assertEquals(new HashSet<>(Arrays.asList(new EmpresaDTO("id", "fantasia", "cnpj"))), result);
            verify(empresaRepository).findAll();

      }

      @Test
      public void testRecuperarEmpresaPor() {
            when(empresaRepository.findEmpresaByFantasia(anyString())).thenReturn(Arrays.<Empresa>asList(new Empresa("id", "fantasia", "cnpj")));
            HashSet<EmpresaDTO> result = empresaServiceImpl.recuperarEmpresaPor("nomeEmpresa");
            Assert.assertEquals(new HashSet<>(Arrays.asList(new EmpresaDTO("id", "fantasia", "cnpj"))), result);
            verify(empresaRepository).findEmpresaByFantasia(anyString());
            Assert.assertFalse(result.isEmpty());

      }
}

