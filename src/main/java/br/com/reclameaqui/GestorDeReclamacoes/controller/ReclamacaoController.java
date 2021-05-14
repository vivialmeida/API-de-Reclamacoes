package br.com.reclameaqui.GestorDeReclamacoes.controller;

import br.com.reclameaqui.GestorDeReclamacoes.model.Reclamacao;
import br.com.reclameaqui.GestorDeReclamacoes.service.ReclamacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reclamacoes")
public class ReclamacaoController {

      private final ReclamacaoService reclamacaoService;

      @GetMapping
      public ResponseEntity<List<Reclamacao>> recuperarReclamacoes() {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacoes());
      }

      @GetMapping("/{id}")
      public ResponseEntity<Reclamacao> recuperarReclamacaoPorId(@PathVariable("id") Long id) {
            return ResponseEntity.ok(reclamacaoService.recuperarReclamacao(id));
      }

      @PostMapping
      public ResponseEntity.BodyBuilder adicionarReclamacao(@RequestBody Reclamacao reclamacao) {
            reclamacaoService.inserirReclamacao(reclamacao);
            return ResponseEntity.created(null);
      }

      @PutMapping("/{id}")
      public ResponseEntity<Reclamacao> atualizarReclamacao(@PathVariable("id") Long id,
                                                            @RequestBody Reclamacao reclamacao) {
            return ResponseEntity.ok(reclamacaoService.atualizarReclamacao(id, reclamacao));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> excluirReclamacao(@PathVariable("id") Long id) {
            reclamacaoService.excluirReclamacao(id);
            return ResponseEntity.noContent().build();
      }
}
