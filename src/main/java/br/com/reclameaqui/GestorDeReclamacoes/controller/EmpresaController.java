package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Empresa;
import br.com.reclameaqui.GestorDeReclamacoes.service.interfaces.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

      private final EmpresaService empresaService;

      @GetMapping
      public ResponseEntity<HashSet<Empresa>> recuperarEmpresa(){
            return  ResponseEntity.ok(empresaService.recuperarEmpresas());
      }

      @GetMapping("/{nome}")
      public ResponseEntity<HashSet<Empresa>> recuperarEmpresaPor(@PathVariable("nome") String nome){
            return  ResponseEntity.ok(empresaService.recuperarEmpresaPor(nome));
      }

}
