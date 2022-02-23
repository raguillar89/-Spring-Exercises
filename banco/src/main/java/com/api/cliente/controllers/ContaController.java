package com.api.cliente.controllers;

import com.api.cliente.dtos.ContaDto;
import com.api.cliente.models.ContaModel;
import com.api.cliente.services.ContaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/conta")
public class ContaController {

    final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping //Insere dados
    public ResponseEntity<Object> saveConta(@RequestBody @Valid ContaDto contaDto){
        if(contaService.existsByAgencia(contaDto.getAgencia())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Agencia is already in use!");
        }
        if(contaService.existsByConta(contaDto.getConta())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Conta is already in use!");
        }
        var contaModel = new ContaModel();
        BeanUtils.copyProperties(contaDto, contaModel);
        contaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.save(contaModel));
    }

    @GetMapping //Mostra todos os dados
    public ResponseEntity<Page<ContaModel>> getAllContas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contaService.findAll(pageable));
    }

    @GetMapping("/{id}") //Mostra um dado em específico
    public ResponseEntity<Object> getOneConta(@PathVariable(value = "id") UUID id){
        Optional<ContaModel> contaModelOptional = contaService.findById(id);
        if(!contaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contaModelOptional.get());
    }

    @DeleteMapping("/{id}") //Deleta um dado em específico
    public ResponseEntity<Object> deleteConta(@PathVariable(value = "id") UUID id){
        Optional<ContaModel> contaModelOptional = contaService.findById(id);
        if (!contaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta not found.");
        }
        contaService.delete(contaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Conta deleted successfully.");
    }

    @PutMapping("/{id}") //Atualiza um dado em específico
    public ResponseEntity<Object> updateConta(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContaDto contaDto){
        Optional<ContaModel> contaModelOptional = contaService.findById(id);
        if(!contaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta not found.");
        }
        var contaModel = new ContaModel(); //Dessa forma se mantem as informações que quero que não sejam alteradas e as outras posso alterar livremente
        BeanUtils.copyProperties(contaDto, contaModel);
        contaModel.setId(contaModelOptional.get().getId());
        contaModel.setRegistrationDate(contaModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(contaService.save(contaModel));
    }
}
