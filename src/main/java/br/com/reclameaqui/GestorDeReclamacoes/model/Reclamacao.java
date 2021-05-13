package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Reclamacao {

  private String titulo;
  private String descricao;
  private Localidade localidade;
  private Empresa empresa;
  private LocalDateTime datahora;
}
