package br.imd.Supermarket.controller;

import br.imd.Supermarket.DTO.ProdutoDTO;
import br.imd.Supermarket.model.ProdutoEntity;
import br.imd.Supermarket.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/mensagem")
    public String mensagem(){
        return "teste get produto";
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoEntity> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        ProdutoEntity produtoEntity = new ProdutoEntity(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProduto(@PathVariable Long id){
        Optional<ProdutoEntity> produto = repository.findById(id);
        if(produto.isEmpty() || !produto.get().isAtivo()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não localizado ou inativo");
        }
        return ResponseEntity.ok().body(produto.get());
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> buscarProdutos(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/getAllAtivos")
    public ResponseEntity<Object> buscarProdutosAtivos(){
        return ResponseEntity.ok().body(repository.findAllAtivos());
    }

    @PutMapping("/alterar")
    public ResponseEntity<Object> putProduto(@RequestBody ProdutoEntity produtoAtualizado) {
        if (produtoAtualizado.getId() == null) {
            return ResponseEntity.badRequest().body("O ID do produto é obrigatório.");
        }

        Optional<ProdutoEntity> produtoExistente = repository.findById(produtoAtualizado.getId());

        if (produtoExistente.isPresent() && produtoExistente.get().isAtivo()) {
            ProdutoEntity produto = produtoExistente.get();
            if (produtoAtualizado.getNomeProduto() != null) produto.setNomeProduto(produtoAtualizado.getNomeProduto());
            if (produtoAtualizado.getMarca() != null) produto.setMarca(produtoAtualizado.getMarca());
            if (produtoAtualizado.getDataFabricacao() != null) produto.setDataFabricacao(produtoAtualizado.getDataFabricacao());
            if (produtoAtualizado.getDataValidade() != null) produto.setDataValidade(produtoAtualizado.getDataValidade());
            if (produtoAtualizado.getGenero() != null) produto.setGenero(produtoAtualizado.getGenero());
            if (produtoAtualizado.getLote() != null) produto.setLote(produtoAtualizado.getLote());

            return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE para exclusão física
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Produto excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE para exclusão lógica
    @DeleteMapping("/exclusao-logica/{id}")
    public ResponseEntity<String> deleteLogic(@PathVariable Long id) {
        Optional<ProdutoEntity> produtoExistente = repository.findById(id);

        if (produtoExistente.isPresent() && produtoExistente.get().isAtivo()) {
            ProdutoEntity produto = produtoExistente.get();
            produto.setAtivo(false); // Define o produto como inativo
            repository.save(produto);
            return ResponseEntity.ok("Produto inativado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
