package br.com.reclameaqui.GestorDeReclamacoes.service.interfaces;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import java.time.LocalDate;
import java.util.List;

public interface ReclamacaoService {

      List<Reclamacao> recuperarReclamacoes();

      Iterable<Reclamacao> recuperarReclamacaoPorId(String  idReclamcao);

      Iterable<Reclamacao> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim);

      List<Reclamacao> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa);

      List<Reclamacao> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade);


      void inserirReclamacao(Reclamacao reclamacao);

      Reclamacao atualizarReclamacao(Reclamacao reclamacao);

      void excluirReclamacao(String idReclamacao);







}
