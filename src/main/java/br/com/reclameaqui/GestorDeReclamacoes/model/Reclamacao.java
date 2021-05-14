package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Document(collection = "reclamacao")
public class Reclamacao {

  @Id
  private Long id;
  @NotBlank @Size(min = 2)
  private String titulo;
  @NotBlank @Size(min = 5)
  private String descricao;
  private Localidade localidade;
  private Empresa empresa;
  private LocalDateTime datahora;

}
