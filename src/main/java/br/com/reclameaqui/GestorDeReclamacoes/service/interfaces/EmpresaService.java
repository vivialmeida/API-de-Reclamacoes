package br.com.reclameaqui.GestorDeReclamacoes.service.interfaces;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import java.util.List;

public interface EmpresaService {

      List<Empresa> recuperarEmpresaPor(String nomeEmpresa, String cnpjEmpresa);

}
