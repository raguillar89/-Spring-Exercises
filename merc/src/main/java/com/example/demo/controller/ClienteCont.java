package com.example.demo.controller;

import com.example.demo.dto.ClienteDto;
import com.example.demo.model.ClienteModel;
import com.example.demo.service.ClienteServ;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cliente")
public class ClienteCont {

        final ClienteServ clienteServ;
        public ClienteCont(ClienteServ ClienteServ) {
            this.clienteServ = ClienteServ;
        }

        @PostMapping //Insere dados
        public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto){
            if(clienteServ.existsByNomeCli(clienteDto.getNomeCli())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Cliente is already in use!");
            }
            var clienteModel = new ClienteModel();
            BeanUtils.copyProperties(clienteDto, clienteModel);
            clienteModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteServ.save(clienteModel));
        }

        @GetMapping //Mostra todos os dados
        public ResponseEntity<Page<ClienteModel>> getAllClientes(@PageableDefault(page = 0, size = 100, sort = "idCli", direction = Sort.Direction.ASC) Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(clienteServ.findAll(pageable));
        }

        @GetMapping("/{idCli}")
        public ResponseEntity<Object> getOneCliente(@PathVariable(value = "idCli") Long idCli){
            Optional<ClienteModel> ClienteModelOptional = clienteServ.findById(idCli);
            if(!ClienteModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ClienteModelOptional.get());
        }

        @DeleteMapping("/{idCli}")
        public ResponseEntity<Object> deleteCliente(@PathVariable(value = "idCli") Long idCli){
            Optional<ClienteModel> ClienteModelOptional = clienteServ.findById(idCli);
            if (!ClienteModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
            }
            clienteServ.delete(ClienteModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deleted successfully.");
        }

        @PutMapping("/{idCli}")
        public ResponseEntity<Object> updateProduto(@PathVariable(value = "idCli") Long idCli, @RequestBody @Valid ClienteDto ClienteDto){
            Optional<ClienteModel> ClienteModelOptional = clienteServ.findById(idCli);
            if(!ClienteModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente not found.");
            }
            var ClienteModel = new ClienteModel();
            BeanUtils.copyProperties(ClienteDto, ClienteModel);
            ClienteModel.setIdCli(ClienteModelOptional.get().getIdCli());
            ClienteModel.setRegistrationDate(ClienteModelOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(clienteServ.save(ClienteModel));
        }

}
