package br.com.reclameaqui.gestorreclamacoes.model.dto;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO  extends RepresentationModel<EmpresaDTO> {

      private String id;
      @NotEmpty
      private String fantasia;
      @CNPJ(message = "CNPJ inválido")
      private String cnpj;


      public static  EmpresaDTO convertDTOEmpresa(Empresa empresa){
            return  EmpresaDTO.builder()
                    .id(empresa.getId())
                    .cnpj(empresa.getCnpj())
                    .fantasia(empresa.getFantasia())
                    .build();
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            EmpresaDTO that = (EmpresaDTO) o;
            return fantasia.equals(that.fantasia) && cnpj.equals(that.cnpj);
      }

      @Override
      public int hashCode() {
            return Objects.hash(super.hashCode(), fantasia, cnpj);
      }
}
