package br.com.reclameaqui.gestorreclamacoes.controller;

import br.com.reclameaqui.gestorreclamacoes.model.Reclamacao;
import br.com.reclameaqui.gestorreclamacoes.model.dto.ReclamacaoDTO;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.ReclamacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
  public ResponseEntity<Page<ReclamacaoDTO>> recuperarReclamacoes(@RequestParam(value = "size", required = false, defaultValue = "100")Integer size,
                                                                  @RequestParam(value = "page", required = false, defaultValue = "0")Integer page) {

    Page<ReclamacaoDTO> pages = reclamacaoService.recuperarReclamacoes(page, size);
    pages.getContent().forEach(reclamacao -> reclamacao.add(linkTo(methodOn(ReclamacaoController.class).recuperarReclamacaoPorId(reclamacao.getId())).withSelfRel()));
    return ResponseEntity.ok().body(pages);
  }

  @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacao em um intervalo de data")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Parâmetros incorretos"),
      @ApiResponse(code = 500, message = "Erro interno"),
  })
  @GetMapping("/data")
  public ResponseEntity<Iterable<ReclamacaoDTO>> recuperarReclamacaoPorData(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                         @RequestParam("dataInicio") LocalDate dataInicio,
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
  public ResponseEntity<ReclamacaoDTO> recuperarReclamacaoPorId(@PathVariable("id") String id) {
    ReclamacaoDTO reclamacao = reclamacaoService.recuperarReclamacaoPorId(id);
    reclamacao.add(linkTo(methodOn(ReclamacaoController.class).recuperarReclamacoes(100, 0)).withRel("Listar todas as reclamacoes"));

    return ResponseEntity.ok().body(reclamacao);
  }


  @ApiOperation(value = "Operação responsável por adicionar uma nova reclamacao")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Parâmetros incorretos"),
      @ApiResponse(code = 500, message = "Erro interno"),
  })
  @PostMapping
  public ResponseEntity<ReclamacaoDTO> adicionarReclamacao(@Valid @RequestBody Reclamacao reclamacao, UriComponentsBuilder uriBuilder) {
    ReclamacaoDTO novaReclamacao = reclamacaoService.inserirReclamacao(reclamacao);
    return ResponseEntity.created(URI.create(uriBuilder.build().getPath())).body(novaReclamacao);
  }

  @ApiOperation(value = "Operação responsável por atualizar uma reclamacao ja existente")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Parâmetros incorretos"),
      @ApiResponse(code = 500, message = "Erro interno"),
  })
  @PutMapping("/{id}")
  public ResponseEntity<ReclamacaoDTO> atualizarReclamacao(@PathVariable("id") String id,
                                                        @RequestBody @Valid Reclamacao reclamacao) {
    return ResponseEntity.ok(reclamacaoService.atualizarReclamacao(id,reclamacao));
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
  public ResponseEntity<List<ReclamacaoDTO>> recuperarReclamacoesPorEmpresa(@RequestParam(value = "nome", required = false) String nome,
                                                                         @RequestParam(value = "cnpj" , required = false) String cnpj) {
    return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorEmpresa(nome, cnpj));
  }

  @ApiOperation(value = "Operação responsável por recuperar uma lista de reclamacoes por localidade")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Parâmetros incorretos"),
      @ApiResponse(code = 500, message = "Erro interno"),
  })
  @GetMapping("/localidade")
  public ResponseEntity<List<ReclamacaoDTO>> recuperarReclamacoesPorEmpresa(@RequestParam(value = "pais", required = false)  String pais,
                                                                         @RequestParam(value = "estado", required = false)  String estado,
                                                                         @RequestParam(value = "cidade", required = false) String cidade) {
    return ResponseEntity.ok(reclamacaoService.recuperarReclamacaoPorLocalidade(pais, estado, cidade));
  }
}
