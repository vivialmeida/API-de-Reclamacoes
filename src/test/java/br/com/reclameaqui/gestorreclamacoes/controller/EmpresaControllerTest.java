package br.com.reclameaqui.gestorreclamacoes.controller;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;

public class EmpresaControllerTest {
  @Mock
  EmpresaService empresaService;
  @Mock
  Page<Empresa> page;
  @InjectMocks
  EmpresaController empresaController;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testRecuperarEmpresas() throws Exception {
    when(empresaService.recuperarEmpresas()).thenReturn(new HashSet<>(Arrays.asList(new Empresa("id", "fantasia", "cnpj"))));
    when(empresaService.recuperarEmpresaPor(anyString())).thenReturn(new HashSet<Empresa>(Arrays.asList(new Empresa("id", "fantasia", "cnpj"))));

    ResponseEntity<HashSet<Empresa>> result = empresaController.recuperarEmpresas();
    verify(empresaService, times(1)).recuperarEmpresas();
  }

  @Test
  public void testRecuperarEmpresaPor() throws Exception {
    when(empresaService.recuperarEmpresaPor(anyString())).thenReturn(new HashSet<>(Arrays.asList(new Empresa("id", "fantasia", "cnpj"))));

    ResponseEntity<HashSet<Empresa>> result = empresaController.recuperarEmpresaPor("nome");
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    verify(empresaService,times(1)).recuperarEmpresaPor(anyString());
  }
}

