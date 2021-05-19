package br.com.reclameaqui.gestorreclamacoes.model.dto;

import br.com.reclameaqui.gestorreclamacoes.model.Localidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalidadeDTO  extends RepresentationModel<LocalidadeDTO> {

      private String id;
      @Size(min = 3)
      private String pais;
      @Size(min = 2)
      private String estado;
      @Size(min = 3)
      private String cidade;

      public static LocalidadeDTO convertDTOLocalidade(Localidade localidade){
            return  LocalidadeDTO.builder()
                    .id(localidade.getId())
                    .pais(localidade.getPais())
                    .estado(localidade.getEstado())
                    .cidade(localidade.getCidade())
                    .build();
      }
}
