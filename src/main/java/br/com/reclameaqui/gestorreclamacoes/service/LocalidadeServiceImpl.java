package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import br.com.reclameaqui.gestorreclamacoes.respository.LocalidadeRepository;
import br.com.reclameaqui.gestorreclamacoes.respository.templates.LocalidadeRepositoryTemplate;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.LocalidadeService;
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
                 throw new NaoEncontradoException("Não ha registro na localidade para os parametros informados! ");
           }
           return localidades;



      }
}
