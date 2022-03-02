package com.example.demo.controller;

import com.example.demo.dto.ProdutoDto;
import com.example.demo.model.ProdutoModel;
import com.example.demo.service.ProdutoServ;
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
@RequestMapping("/produto")
public class ProdutoCont {

        final ProdutoServ produtoServ;
        public ProdutoCont(ProdutoServ produtoServ) {
            this.produtoServ = produtoServ;
        }

        @PostMapping //Insere dados
        public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto produtoDto){
            if(produtoServ.existsByNomeProd(produtoDto.getNomeProd())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Produto is already in use!");
            }
            var produtoModel = new ProdutoModel();
            BeanUtils.copyProperties(produtoDto, produtoModel);
            produtoModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoServ.save(produtoModel));
        }

        @GetMapping //Mostra todos os dados
        public ResponseEntity<Page<ProdutoModel>> getAllProdutos(@PageableDefault(page = 0, size = 100, sort = "idProd", direction = Sort.Direction.ASC) Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(produtoServ.findAll(pageable));
        }

        @GetMapping("/{idProd}")
        public ResponseEntity<Object> getOneProduto(@PathVariable(value = "idProd") Long idProd){
            Optional<ProdutoModel> ProdutoModelOptional = produtoServ.findById(idProd);
            if(!ProdutoModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(ProdutoModelOptional.get());
        }

        @DeleteMapping("/{idProd}")
        public ResponseEntity<Object> deleteProduto(@PathVariable(value = "idProd") Long idProd){
            Optional<ProdutoModel> ProdutoModelOptional = produtoServ.findById(idProd);
            if (!ProdutoModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
            }
            produtoServ.delete(ProdutoModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Produto deleted successfully.");
        }

        @PutMapping("/{idProd}")
        public ResponseEntity<Object> updateProduto(@PathVariable(value = "idPro") Long idProd, @RequestBody @Valid ProdutoDto ProdutoDto){
            Optional<ProdutoModel> ProdutoModelOptional = produtoServ.findById(idProd);
            if(!ProdutoModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
            }
            var ProdutoModel = new ProdutoModel();
            BeanUtils.copyProperties(ProdutoDto, ProdutoModel);
            ProdutoModel.setIdProd(ProdutoModelOptional.get().getIdProd());
            ProdutoModel.setRegistrationDate(ProdutoModelOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(produtoServ.save(ProdutoModel));
        }

}
