package br.com.reclameaqui.gestorreclamacoes.model.dto;

import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReclamacaoDTO extends RepresentationModel<ReclamacaoDTO>  {

      private String id;
      @NotEmpty
      @Size(min = 2)
      private String titulo;
      @NotEmpty @Size(min = 5)
      private String descricao;
      private LocalDate data;
      @Valid
      private LocalidadeDTO localidade;
      @Valid
      private EmpresaDTO empresa;


      public static ReclamacaoDTO convertDTOReclamacao(Reclamacao reclamacao){
            return  ReclamacaoDTO.builder()
                    .id(reclamacao.getId())
                    .titulo(reclamacao.getTitulo())
                    .descricao(reclamacao.getDescricao())
                    .data(reclamacao.getData())
                    .empresa(EmpresaDTO.convertDTOEmpresa(reclamacao.getEmpresa()))
                    .localidade(LocalidadeDTO.convertDTOLocalidade(reclamacao.getLocalidade()))
                    .build();
      }
}


