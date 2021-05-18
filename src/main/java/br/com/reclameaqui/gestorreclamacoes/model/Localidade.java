package br.com.reclameaqui.gestorreclamacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "localidade")
public class Localidade {

  @Id @GeneratedValue
  private String id;
  @Size(min = 3)
  private String pais;
  @Size(min = 2)
  private String estado;
  @Size(min = 3)
  private String cidade;

}
