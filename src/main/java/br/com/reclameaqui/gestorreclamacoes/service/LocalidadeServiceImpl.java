package br.com.reclameaqui.gestorreclamacoes.service;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import br.com.reclameaqui.gestorreclamacoes.respository.LocalidadeRepository;
import br.com.reclameaqui.gestorreclamacoes.respository.templates.LocalidadeRepositoryTemplate;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.LocalidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@Service
@RequiredArgsConstructor
public class LocalidadeServiceImpl implements LocalidadeService {

  private final LocalidadeRepository localidadeRepository;
  private final LocalidadeRepositoryTemplate localidadeRepositoryTemplate;

  public Localidade adicionarOuAtualizarLocalidade(Localidade localidade){

        Localidade localidadeExistente = recuperarLocalidadeCasoJaExista(localidade);
        if (Objects.nonNull(localidadeExistente)){
              return localidadeExistente;
        }

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

      private Localidade recuperarLocalidadeCasoJaExista(Localidade localidade){
            ExampleMatcher modelMatcher = ExampleMatcher.matching()
                    .withIgnorePaths("id")
                    .withMatcher("pais", ignoreCase())
                    .withMatcher("cidade", ignoreCase())
                    .withMatcher("estado", ignoreCase());

            Example<Localidade> example = Example.of(localidade,modelMatcher);
            localidadeRepository.exists(example);
            return localidadeRepository.findLocalidadeByPaisAndCidadeAndEstado(localidade.getPais(), localidade.getCidade(), localidade.getEstado());

      }
}
