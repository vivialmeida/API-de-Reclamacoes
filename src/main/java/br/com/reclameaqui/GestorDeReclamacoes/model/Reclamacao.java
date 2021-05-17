package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
  @NotBlank @Size(min = 2)
  private String titulo;
  @NotBlank @Size(min = 5)
  private String descricao;
  private LocalDate data;
  @DBRef
  private Localidade localidade;
  @DBRef
  private Empresa empresa;

}
