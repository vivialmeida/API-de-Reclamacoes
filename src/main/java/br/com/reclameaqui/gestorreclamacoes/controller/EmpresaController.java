package br.com.reclameaqui.gestorreclamacoes.controller;

import br.com.reclameaqui.gestorreclamacoes.model.dto.EmpresaDTO;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

      private final EmpresaService empresaService;

      @ApiOperation(value = "Operação responsável por recuperar uma lista de empresas")
      @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Ok"),
          @ApiResponse(code = 400, message = "Parâmetros incorretos"),
          @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping
      public ResponseEntity<HashSet<EmpresaDTO>> recuperarEmpresas(){

            HashSet<EmpresaDTO>  empresas = empresaService.recuperarEmpresas();
            empresas.forEach(empresa -> empresa.add(linkTo(methodOn(EmpresaController.class).recuperarEmpresaPor(empresa.getFantasia())).withSelfRel()));

            return  ResponseEntity.ok().body(empresas);

      }

      @ApiOperation(value = "Operação responsável por recuperar empresas atraves do nome")
      @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Ok"),
          @ApiResponse(code = 400, message = "Parâmetros incorretos"),
          @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/{nome}")
      public ResponseEntity<HashSet<EmpresaDTO>> recuperarEmpresaPor(@PathVariable("nome") String nome){
            HashSet<EmpresaDTO> empresas = empresaService.recuperarEmpresaPor(nome);
            empresas.forEach(empresa -> empresa.add(linkTo(methodOn(EmpresaController.class).recuperarEmpresas()).withRel("Listar todas as empresas")));

            return  ResponseEntity.ok().body(empresas);
      }

}
