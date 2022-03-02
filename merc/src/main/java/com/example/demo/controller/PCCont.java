package com.example.demo.controller;

import com.example.demo.dto.PCDto;
import com.example.demo.model.PCModel;
import com.example.demo.service.PCServ;
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
@RequestMapping("/pc")
public class PCCont {

        final PCServ pcServ;
        public PCCont(PCServ pcServ) {
            this.pcServ = pcServ;
        }

        @PostMapping
        public ResponseEntity<Object> savePC(@RequestBody @Valid PCDto pcDto){
            var pcModel = new PCModel();
            BeanUtils.copyProperties(pcDto, pcModel);
            pcModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.CREATED).body(pcServ.save(pcModel));
        }

        @GetMapping
        public ResponseEntity<Page<PCModel>> getAllPC(@PageableDefault(page = 0, size = 100, sort = "idPC", direction = Sort.Direction.ASC) Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(pcServ.findAll(pageable));
        }

        @GetMapping("/{idPC}")
        public ResponseEntity<Object> getOnePC(@PathVariable(value = "idPC") Long idPC){
            Optional<PCModel> PCModelOptional = pcServ.findById(idPC);
            if(!PCModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClienteProduto not found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(PCModelOptional.get());
        }

        @DeleteMapping("/{idPC}")
        public ResponseEntity<Object> deletePC(@PathVariable(value = "idPC") Long idPC){
            Optional<PCModel> PCModelOptional = pcServ.findById(idPC);
            if (!PCModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClienteProduto not found.");
            }
            pcServ.delete(PCModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("ClienteProduto deleted successfully.");
        }

        @PutMapping("/{idPC}")
        public ResponseEntity<Object> updatePC(@PathVariable(value = "idPC") Long idPC, @RequestBody @Valid PCDto PCDto){
            Optional<PCModel> PCModelOptional = pcServ.findById(idPC);
            if(!PCModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClienteProduto not found.");
            }
            var PCModel = new PCModel();
            BeanUtils.copyProperties(PCDto, PCModel);
            PCModel.setIdProd(PCModelOptional.get().getIdPC());
            PCModel.setRegistrationDate(PCModelOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(pcServ.save(PCModel));
        }

}
