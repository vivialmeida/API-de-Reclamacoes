package br.com.reclameaqui.GestorDeReclamacoes.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

@Data
public class Empresa {

  @NotBlank
  private String fantasia;
  @CNPJ
  private String cnpj;

}
