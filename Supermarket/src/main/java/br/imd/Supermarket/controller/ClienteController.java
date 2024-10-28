package br.imd.Supermarket.controller;

import br.imd.Supermarket.DTO.ClienteDTO;
import br.imd.Supermarket.DTO.ProdutoDTO;
import br.imd.Supermarket.model.ClienteEntity;
import br.imd.Supermarket.model.ProdutoEntity;
import br.imd.Supermarket.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @GetMapping("/mensagem")
    public String mensagem() { return "teste get cliente";}

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteEntity> cadastrarProduto(@RequestBody ClienteDTO clienteDTO){
        ClienteEntity clienteEntity = new ClienteEntity(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(clienteEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCliente(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = repository.findById(id);
        if (cliente.isEmpty() || !cliente.get().isAtivo()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao localizado ou inativo.");
        }
        return ResponseEntity.ok(cliente.get());
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> listarClientes() { return ResponseEntity.ok(repository.findAll()); }

    @GetMapping("/getAllAtivos")
    public ResponseEntity<Object> listarClientesAtivos(){
        return ResponseEntity.ok().body(repository.findAllAtivos());
    }

    @PutMapping("/alterar")
    public ResponseEntity<Object> putCliente(@RequestBody ClienteEntity clienteAtualizado) {
        if(clienteAtualizado.getId() == null){
            return ResponseEntity.badRequest().body("O ID do cliente é obrigatório.");
        }

        Optional<ClienteEntity> clienteExistente = repository.findById(clienteAtualizado.getId());
        if (clienteExistente.isPresent() && clienteExistente.get().isAtivo()) {
            ClienteEntity cliente = clienteExistente.get();
            if(clienteAtualizado.getNome() != null) cliente.setNome(clienteAtualizado.getNome());
            if(clienteAtualizado.getCpf() != null) cliente.setCpf(clienteAtualizado.getCpf());
            if(clienteAtualizado.getGenero() != null) cliente.setGenero(clienteAtualizado.getGenero());
            if(clienteAtualizado.getDataNascimento() != null) cliente.setDataNascimento(clienteAtualizado.getDataNascimento());

            return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao localizado.");
        }
    }

    //Método DELETE para exclusão física
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao localizado.");
        }
    }

    //Método DELETE para exclusão lógica
    @DeleteMapping("/exclusao-logica/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        Optional<ClienteEntity> clienteExistente = repository.findById(id);

        if (clienteExistente.isPresent() && clienteExistente.get().isAtivo()) {
            ClienteEntity cliente = clienteExistente.get();
            cliente.setAtivo(false);
            repository.save(cliente);
            return ResponseEntity.ok("Cliente inativado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente nao localizado.");
        }
    }
}



