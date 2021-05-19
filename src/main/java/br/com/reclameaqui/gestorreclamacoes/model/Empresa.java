package br.com.reclameaqui.gestorreclamacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "empresa")
public class Empresa extends RepresentationModel<Empresa> {

  @Id @GeneratedValue
  private String id;
  @NotEmpty
  private String fantasia;
  @CNPJ(message = "CNPJ inválido")
  private String cnpj;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Empresa empresa = (Empresa) o;

    if (!fantasia.equals(empresa.fantasia)) {
      return false;
    }
    return cnpj.equals(empresa.cnpj);
  }

  @Override
  public int hashCode() {
    int result = fantasia.hashCode();
    result = 31 * result + cnpj.hashCode();
    return result;
  }

  @Transient
  @Override
  public Links getLinks() {
    return super.getLinks();
  }
}
