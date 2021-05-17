package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import br.com.reclameaqui.GestorDeReclamacoes.respository.LocalidadeRepository;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LocalidadeServiceImpl implements LocalidadeService {

  private final LocalidadeRepository localidadeRepository;

  public Localidade adicionarOuAtualizarLocalidade(Localidade localidade){
    return localidadeRepository.save(localidade);
  }

}
