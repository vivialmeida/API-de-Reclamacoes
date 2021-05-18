package br.com.reclameaqui.gestorreclamacoes.service.interfaces;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;

import java.util.HashSet;

public interface EmpresaService {

  Empresa adicionarOuAtualizarEmpresa(Empresa empresa);

  HashSet<Empresa> recuperarEmpresas();

  HashSet<Empresa>recuperarEmpresaPor(String nomeEmpresa);

}
