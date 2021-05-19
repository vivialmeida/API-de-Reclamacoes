package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import br.com.reclameaqui.gestorreclamacoes.model.dto.EmpresaDTO;
import br.com.reclameaqui.gestorreclamacoes.model.dto.LocalidadeDTO;
import br.com.reclameaqui.gestorreclamacoes.model.dto.ReclamacaoDTO;
import br.com.reclameaqui.gestorreclamacoes.respository.ReclamacaoRepository;
import br.com.reclameaqui.gestorreclamacoes.respository.templates.EmpresaRepositoryTemplate;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.exception.ReclamacaoValidationException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.LocalidadeService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ReclamacaoServiceImplTest {
  @Mock
  ReclamacaoRepository reclamacaoRepository;
  @Mock
  EmpresaRepositoryTemplate reclamacaoRepositoryTemplate;
  @Mock
  LocalidadeService localidadeService;
  @Mock
  EmpresaService empresaService;
  @InjectMocks
  ReclamacaoServiceImpl reclamacaoServiceImpl;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailIllegalArgumentsRecuperarReclamacoes() {
    Page<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacoes(Integer.valueOf(0), Integer.valueOf(0));
  }


  @Test(expected = NaoEncontradoException.class)
  public void testFailRecuperarReclamacaoPorId() throws Exception {
    ReclamacaoDTO result = reclamacaoServiceImpl.recuperarReclamacaoPorId("idReclamacao");
    Assert.assertEquals(new ReclamacaoDTO("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new LocalidadeDTO("id", "pais", "estado", "cidade"), new EmpresaDTO("id", "fantasia", "cnpj")), result);
    verify(reclamacaoRepository, times(1)).findById(anyString());
  }

  @Test
  public void testRecuperarReclamacaoPorId() throws Exception {
    when(reclamacaoRepository.findById(anyString())).thenReturn(java.util.Optional.of(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))));
    ReclamacaoDTO result = reclamacaoServiceImpl.recuperarReclamacaoPorId("idReclamacao");
    verify(reclamacaoRepository).findById(anyString());
  }


  @Test(expected = NaoEncontradoException.class)
  public void testFailRecuperarReclamacaoPorData() throws Exception {
    when(reclamacaoRepository.findReclamacaoByDataBetween(any(), any())).thenReturn(Lists.emptyList());
    List<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacaoPorData(LocalDate.of(2021, Month.MAY, 18), LocalDate.of(2021, Month.MAY, 18));
    Assert.assertEquals(Arrays.<Reclamacao>asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))), result);
  }


  @Test(expected = ReclamacaoValidationException.class)
  public void testFailDataFimMenorRecuperarReclamacaoPorData() throws Exception {
    when(reclamacaoRepository.findReclamacaoByDataBetween(any(), any())).thenReturn(Arrays.asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))));

    List<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacaoPorData(LocalDate.of(2021, Month.MAY, 18), LocalDate.of(2021, Month.MAY, 17));
    Assert.assertEquals(Arrays.<Reclamacao>asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))), result);
  }

  @Test
  public void testRecuperarReclamacaoPorData() throws Exception {
    when(reclamacaoRepository.findReclamacaoByDataBetween(any(), any())).thenReturn(Arrays.asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))));

    List<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacaoPorData(LocalDate.of(2021, Month.MAY, 18), LocalDate.of(2021, Month.MAY, 18));
    Assert.assertEquals(Arrays.asList(new ReclamacaoDTO("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new LocalidadeDTO("id", "pais", "estado", "cidade"), new EmpresaDTO("id", "fantasia", "cnpj"))), result);
  }

  @Test
  public void testRecuperarReclamacaoPorEmpresa() throws Exception {
    when(reclamacaoRepository.findReclamacaoByEmpresaIsIn(any())).thenReturn(Arrays.asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))));
    when(reclamacaoRepositoryTemplate.recuperarReclamacoesPorEmpresa(any())).thenReturn(Arrays.<String>asList("String"));

    List<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacaoPorEmpresa("nomeEmpresa", "cnpjEmpresa");
    Assert.assertEquals(Arrays.asList(new ReclamacaoDTO("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new LocalidadeDTO("id", "pais", "estado", "cidade"), new EmpresaDTO("id", "fantasia", "cnpj"))), result);
  }

  @Test
  public void testRecuperarReclamacaoPorLocalidade() throws Exception {
    when(reclamacaoRepository.findReclamacaosByLocalidadeIsIn(any())).thenReturn(Arrays.asList(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj"))));
    when(localidadeService.recuperarIdsLocalidade(any())).thenReturn(Arrays.<String>asList("String"));

    List<ReclamacaoDTO> result = reclamacaoServiceImpl.recuperarReclamacaoPorLocalidade("pais", "estado", "cidade");
    Assert.assertEquals(Arrays.asList(new ReclamacaoDTO("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new LocalidadeDTO("id", "pais", "estado", "cidade"), new EmpresaDTO("id", "fantasia", "cnpj"))), result);
  }

  @Test
  public void testInserirReclamacao() throws Exception {
    when(localidadeService.adicionarOuAtualizarLocalidade(any())).thenReturn(new Localidade("id", "pais", "estado", "cidade"));
    when(empresaService.adicionarOuAtualizarEmpresa(any())).thenReturn(new Empresa("id", "fantasia", "cnpj"));
    when(reclamacaoRepository.insert(any(Reclamacao.class))).thenReturn(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj")));
   reclamacaoServiceImpl.inserirReclamacao(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj")));
   verify(reclamacaoRepository).insert(any(Reclamacao.class));
   verify(localidadeService).adicionarOuAtualizarLocalidade(any());
   verify(empresaService).adicionarOuAtualizarEmpresa(any());

  }

  @Test
  public void testAtualizarReclamacao() throws Exception {
    when(localidadeService.adicionarOuAtualizarLocalidade(any())).thenReturn(new Localidade("id", "pais", "estado", "cidade"));
    when(empresaService.adicionarOuAtualizarEmpresa(any())).thenReturn(new Empresa("id", "fantasia", "cnpj"));
    when(reclamacaoRepository.save(any())).thenReturn(new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj")));


    reclamacaoServiceImpl.atualizarReclamacao("id", new Reclamacao("id", "titulo", "descricao", LocalDate.of(2021, Month.MAY, 18), new Localidade("id", "pais", "estado", "cidade"), new Empresa("id", "fantasia", "cnpj")));
    verify(reclamacaoRepository).save(any());
  }

  @Test
  public void testExcluirReclamacao() throws Exception {
    reclamacaoServiceImpl.excluirReclamacao("idReclamacao");
  }
}

