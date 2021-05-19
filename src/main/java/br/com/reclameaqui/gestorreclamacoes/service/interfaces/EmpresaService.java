package br.com.reclameaqui.gestorreclamacoes.service.interfaces;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.model.dto.EmpresaDTO;

import java.util.HashSet;

public interface EmpresaService {

  Empresa adicionarOuAtualizarEmpresa(Empresa empresa);

  HashSet<EmpresaDTO> recuperarEmpresas();

  HashSet<EmpresaDTO>recuperarEmpresaPor(String nomeEmpresa);

}
