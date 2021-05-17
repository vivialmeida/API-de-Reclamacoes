package br.com.reclameaqui.GestorDeReclamacoes.service;

import br.com.reclameaqui.GestorDeReclamacoes.model.Localidade;
import br.com.reclameaqui.GestorDeReclamacoes.respository.LocalidadeRepository;
import br.com.reclameaqui.GestorDeReclamacoes.respository.templates.LocalidadeRepositoryTemplate;
import br.com.reclameaqui.GestorDeReclamacoes.service.exception.ReclamacaoValidationException;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LocalidadeServiceImpl implements LocalidadeService {

  private final LocalidadeRepository localidadeRepository;
  private final LocalidadeRepositoryTemplate localidadeRepositoryTemplate;

  public Localidade adicionarOuAtualizarLocalidade(Localidade localidade){
    return localidadeRepository.save(localidade);
  }

      @Override
      public List<String> recuperarIdsLocalidade(Localidade localidade) {
           List<String> localidades = localidadeRepositoryTemplate.recuperarIdsLocalidade(localidade);
           if(localidades.isEmpty()){
                 throw new ReclamacaoValidationException("Não ha registro na localidade para os parametros informados! ");
           }
           return localidades;



      }
}
