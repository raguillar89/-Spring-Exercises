package com.api.cliente.controllers;

import com.api.cliente.dtos.ClienteDto;
import com.api.cliente.models.ClienteModel;
import com.api.cliente.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cliente")
public class ClienteController {

    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping //Insere dados
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto){
        if(clienteService.existsByClient(clienteDto.getClient())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Client is already in use!");
        }
        if(clienteService.existsByCpf(clienteDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
        }
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
    }

    @GetMapping //Mostra todos os dados
    public ResponseEntity<Page<ClienteModel>> getAllClientes(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(pageable));
    }

    @GetMapping("/{id}") //Mostra um dado em específico
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "id") UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
    }

    @DeleteMapping("/{id}") //Deleta um dado em específico
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if (!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
        }
        clienteService.delete(clienteModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deleted successfully.");
    }

    @PutMapping("/{id}") //Atualiza um dado em específico
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if(!clienteModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
        }
        var clienteModel = new ClienteModel(); //Dessa forma se mantem as informações que quero que não sejam alteradas e as outras posso alterar livremente
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setId(clienteModelOptional.get().getId());
        clienteModel.setRegistrationDate(clienteModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
    }
}
