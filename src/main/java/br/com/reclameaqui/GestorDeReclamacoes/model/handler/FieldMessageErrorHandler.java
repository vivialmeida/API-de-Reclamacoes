package br.com.reclameaqui.GestorDeReclamacoes.model.handler;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class FieldMessageErrorHandler implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FieldMessageErrorHandler(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}