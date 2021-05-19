package br.com.reclameaqui.gestorreclamacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reclamacao")
public class Reclamacao {

  @Id @GeneratedValue
  private String id;
  @NotEmpty  @Size(min = 2)
  private String titulo;
  @NotEmpty @Size(min = 5)
  private String descricao;
  private LocalDate data;
  @DBRef   @Valid
  private Localidade localidade;
  @DBRef   @Valid
  private Empresa empresa;

}
