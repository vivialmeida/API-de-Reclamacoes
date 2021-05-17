package br.com.reclameaqui.GestorDeReclamacoes.service.interfaces;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReclamacaoService {

      Page<Reclamacao> recuperarReclamacoes(Integer size, Integer page);

      Reclamacao recuperarReclamacaoPorId(String  idReclamcao);

      List<Reclamacao> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim);

      List<Reclamacao> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa);

      List<Reclamacao> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade);


      Reclamacao inserirReclamacao(Reclamacao reclamacao);

      Reclamacao atualizarReclamacao(String id, Reclamacao reclamacao);

      void excluirReclamacao(String idReclamacao);







}
