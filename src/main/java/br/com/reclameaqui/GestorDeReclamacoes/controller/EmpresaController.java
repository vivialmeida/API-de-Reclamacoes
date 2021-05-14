package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

      private final EmpresaService empresaService;

      @GetMapping
      public ResponseEntity<List<Empresa>>recuperarEmpresa(@RequestParam("nomeEmpresa") String nomeEmpresa,
                                                           @RequestParam("cnpjEmpresa") String cnpjEmpresa){
            return  ResponseEntity.ok(empresaService.recuperarEmpresaPor(nomeEmpresa, cnpjEmpresa));
      }






}
