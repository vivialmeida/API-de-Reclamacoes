package br.com.reclameaqui.GestorDeReclamacoes.service.interfaces;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;

import java.util.HashSet;
import java.util.List;

public interface EmpresaService {

  Empresa adicionarOuAtualizarEmpresa(Empresa empresa);

  HashSet<Empresa> recuperarEmpresas();

  HashSet<Empresa>recuperarEmpresaPor(String nomeEmpresa);

}
