package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.ReclamacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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

      @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacao em um intervalo de data")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/{id}")
      public ResponseEntity<Iterable<Reclamacao>> recuperarReclamacaoPorData(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                             @RequestParam("dataInicio") LocalDate dataInicio,
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                             @RequestParam(value = "dataFim", required = false) LocalDate dataFim) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorData(dataInicio, dataFim));
      }

      @ApiOperation(value = "Operação responsável por recuperar uma reclamacao atraves do id")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/{id}")
      public ResponseEntity<Iterable<Reclamacao>> recuperarReclamacaoPorId(@PathVariable("id") String id) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorId(id));
      }


      @ApiOperation(value = "Operação responsável por adicionar uma nova reclamacao")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @PostMapping
      public ResponseEntity<Void> adicionarReclamacao(@RequestBody Reclamacao reclamacao) {
            reclamacaoService.inserirReclamacao(reclamacao);
            return ResponseEntity.ok().build();
      }

      @ApiOperation(value = "Operação responsável por atualixar uma reclamacao ja existente")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @PutMapping
      public ResponseEntity<Reclamacao> atualizarReclamacao(@RequestBody @Valid Reclamacao reclamacao) {
            return ResponseEntity.ok(reclamacaoService.atualizarReclamacao(reclamacao));
      }

      @ApiOperation(value = "Operação responsável por excluir uma reclamacao atraves o id")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @DeleteMapping("/{id}")
      public ResponseEntity<Void> excluirReclamacao(@PathVariable("id") String id) {
            reclamacaoService.excluirReclamacao(id);
            return ResponseEntity.noContent().build();
      }

      @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacoes por empresa")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/empresa")
      public ResponseEntity<List<Reclamacao>> recuperarReclamacoesPorEmpresa(@RequestParam("nomeEmpresa") String nomeEmpresa,
                                                                             @RequestParam("cnpjEmpresa") String cnpjEmpresa) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorEmpresa(nomeEmpresa, cnpjEmpresa));
      }

      @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacoes por localidade")
      @ApiResponses(value = {
              @ApiResponse(code = 200, message = "Ok"),
              @ApiResponse(code = 400, message = "Parâmetros incorretos"),
              @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/localidade")
      public ResponseEntity<List<Reclamacao>> recuperarReclamacoesPorEmpresa(@RequestParam("pais") String pais,
                                                                             @RequestParam("estado") String estado,
                                                                             @RequestParam("cidade") String cidade) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorLocalidade(pais, estado, cidade));
      }
}
