package br.com.reclameaqui.gestorreclamacoes.controller;

import br.com.reclameaqui.gestorreclamacoes.model.handler.ValidationErrorHandler;
import br.com.reclameaqui.gestorreclamacoes.service.exception.NaoEncontradoException;
import br.com.reclameaqui.gestorreclamacoes.service.exception.ReclamacaoValidationException;
import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorHandler> handlerValidacaoArgumentoInvalido(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        err.setMessage("Campos inválidos");

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        fieldErrorList.forEach( f -> err.addFieldMessageErrorHandler(f.getField(), f.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorHandler> handlerValidacaoConstraintViolada(ConstraintViolationException e, HttpServletRequest request) {

        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        err.setMessage("Campos inválidos: ");

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        violations.forEach(f -> err.addFieldMessageErrorHandler(f.getPropertyPath().toString(), f.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ValidationErrorHandler> handlerNullPointer(NullPointerException e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<ValidationErrorHandler> handlerBD(SQLException e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorHandler> handlerAll(Exception e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ValidationErrorHandler> handlerNaoEncontrado(NaoEncontradoException e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.NOT_FOUND, e, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ReclamacaoValidationException.class)
    public ResponseEntity<ValidationErrorHandler> handlerValidacaoNegocio(ReclamacaoValidationException e, HttpServletRequest request) {
        ValidationErrorHandler err = this.geraValidationErrorHandler(HttpStatus.BAD_REQUEST, e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private ValidationErrorHandler geraValidationErrorHandler(HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        return new ValidationErrorHandler(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.name(),
                e.getMessage(),
                request.getServletPath());
    }

}