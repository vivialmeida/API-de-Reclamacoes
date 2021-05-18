package br.com.reclameaqui.gestorreclamacoes.model.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorHandler {

    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<FieldMessageErrorHandler> fieldErrors;

    public ValidationErrorHandler(LocalDateTime timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = new ArrayList<>();
    }

    public void addFieldMessageErrorHandler(String fieldName, String message) {
        fieldErrors.add(FieldMessageErrorHandler.builder()
                .fieldName(fieldName)
                .message(message)
                .build());
    }
}