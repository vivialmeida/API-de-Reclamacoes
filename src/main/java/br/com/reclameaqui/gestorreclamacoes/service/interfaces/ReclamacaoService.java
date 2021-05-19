package br.com.reclameaqui.gestorreclamacoes.service.interfaces;

import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import br.com.reclameaqui.gestorreclamacoes.model.dto.ReclamacaoDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ReclamacaoService {

      Page<ReclamacaoDTO> recuperarReclamacoes(Integer size, Integer page);

      ReclamacaoDTO recuperarReclamacaoPorId(String  idReclamcao);

      List<ReclamacaoDTO> recuperarReclamacaoPorData(LocalDate dataInicio, LocalDate dataFim);

      List<ReclamacaoDTO> recuperarReclamacaoPorEmpresa(String nomeEmpresa, String cnpjEmpresa);

      List<ReclamacaoDTO> recuperarReclamacaoPorLocalidade(String pais, String estado, String cidade);


      ReclamacaoDTO inserirReclamacao(Reclamacao reclamacao);

      ReclamacaoDTO atualizarReclamacao(String id, Reclamacao reclamacao);

      void excluirReclamacao(String idReclamacao);

      List<ReclamacaoDTO> recuperarReclamacaoDeEmpresaPorCidade(String cidade, String Empresa);
}
