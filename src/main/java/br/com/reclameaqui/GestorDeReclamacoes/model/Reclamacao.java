package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "reclamacao")
public class Reclamacao {

  @Id
  private Long id;
  private String titulo;
  private String descricao;
  private Localidade localidade;
  private Empresa empresa;
  private LocalDateTime datahora;

}
