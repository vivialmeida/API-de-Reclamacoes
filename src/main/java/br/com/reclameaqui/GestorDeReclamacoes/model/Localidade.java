package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Localidade {

  @Size(min = 3)
  private String pais;
  @Size(min = 2)
  private String estado;
  @Size(min = 3)
  private String cidade;

}
