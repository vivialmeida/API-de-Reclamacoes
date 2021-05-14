package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.ReclamacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/reclamacoes")
@Api("Endpoint para gerir informações refente a reclamação")
public class ReclamacaoController {

      private final ReclamacaoService reclamacaoService;

      @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacões")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping
      public ResponseEntity<List<Reclamacao>> recuperarReclamacoes() {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacoes());
      }

      @ApiOperation(value = "Operação responsável por recuperar uma reclamacao atraves do id")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/{id}")
      public ResponseEntity<Reclamacao> recuperarReclamacaoPorId(@PathVariable("id") Long id) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacao(id));
      }

      @ApiOperation(value = "Operação responsável por adicionar uma nova reclamacao")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @PostMapping
      public ResponseEntity.BodyBuilder adicionarReclamacao(@RequestBody Reclamacao reclamacao) {
            reclamacaoService.inserirReclamacao(reclamacao);
            return ResponseEntity.created(null);
      }

      @ApiOperation(value = "Operação responsável por atualixar uma reclamacao ja existente")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @PutMapping("/{id}")
      public ResponseEntity<Reclamacao> atualizarReclamacao(@PathVariable("id") Long id,
                                                            @RequestBody Reclamacao reclamacao) {
            return ResponseEntity.ok(reclamacaoService.atualizarReclamacao(id, reclamacao));
      }

      @ApiOperation(value = "Operação responsável por excluir uma reclamacao atraves o id")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @DeleteMapping("/{id}")
      public ResponseEntity<Void> excluirReclamacao(@PathVariable("id") Long id) {
            reclamacaoService.excluirReclamacao(id);
            return ResponseEntity.noContent().build();
      }
}
