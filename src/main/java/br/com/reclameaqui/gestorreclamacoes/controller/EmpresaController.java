package br.com.reclameaqui.gestorreclamacoes.controller;

import br.com.reclameaqui.gestorreclamacoes.model.Empresa;
import br.com.reclameaqui.gestorreclamacoes.service.interfaces.EmpresaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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
      public ResponseEntity<HashSet<Empresa>> recuperarEmpresa(){
            return  ResponseEntity.ok(empresaService.recuperarEmpresas());
      }

      @ApiOperation(value = "Operação responsável por recuperar empresas atraves do nome")
      @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Ok"),
          @ApiResponse(code = 400, message = "Parâmetros incorretos"),
          @ApiResponse(code = 500, message = "Erro interno"),
      })
      @GetMapping("/{nome}")
      public ResponseEntity<HashSet<Empresa>> recuperarEmpresaPor(@PathVariable("nome") String nome){
            return  ResponseEntity.ok(empresaService.recuperarEmpresaPor(nome));
      }

}
