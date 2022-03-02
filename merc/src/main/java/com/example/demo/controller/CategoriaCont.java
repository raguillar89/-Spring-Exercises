package com.example.demo.controller;

import com.example.demo.dto.CategoriaDto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.service.CategoriaServ;
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
@RequestMapping("/categoria")
public class CategoriaCont {

        final CategoriaServ categoriaServ;
        public CategoriaCont(CategoriaServ categoriaServ) {
            this.categoriaServ = categoriaServ;
        }

        @PostMapping //Insere dados
        public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
            if(categoriaServ.existsByNomeCateg(categoriaDto.getNomeCateg())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Produto is already in use!");
            }
            var categoriaModel = new CategoriaModel();
            BeanUtils.copyProperties(categoriaDto, categoriaModel);
            categoriaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServ.save(categoriaModel));
        }

        @GetMapping //Mostra todos os dados
        public ResponseEntity<Page<CategoriaModel>> getAllCategorias(@PageableDefault(page = 0, size = 100, sort = "idCateg", direction = Sort.Direction.ASC) Pageable pageable){
            return ResponseEntity.status(HttpStatus.OK).body(categoriaServ.findAll(pageable));
        }

        @GetMapping("/{idCateg}")
        public ResponseEntity<Object> getOneCategoria(@PathVariable(value = "idCateg") Long idCateg){
            Optional<CategoriaModel> CategoriaModelOptional = categoriaServ.findById(idCateg);
            if(!CategoriaModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria not found.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(CategoriaModelOptional.get());
        }

        @DeleteMapping("/{idCateg}")
        public ResponseEntity<Object> deleteCategoria(@PathVariable(value = "idCateg") Long idCateg){
            Optional<CategoriaModel> CategoriaModelOptional = categoriaServ.findById(idCateg);
            if (!CategoriaModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria not found.");
            }
            categoriaServ.delete(CategoriaModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Categoria deleted successfully.");
        }

        @PutMapping("/{idCateg}")
        public ResponseEntity<Object> updateCategoria(@PathVariable(value = "idCateg") Long idCateg, @RequestBody @Valid CategoriaDto CategoriaDto){
            Optional<CategoriaModel> CategoriaModelOptional = categoriaServ.findById(idCateg);
            if(!CategoriaModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto not found.");
            }
            var CategoriaModel = new CategoriaModel();
            BeanUtils.copyProperties(CategoriaDto, CategoriaModel);
            CategoriaModel.setIdCateg(CategoriaModelOptional.get().getIdCateg());
            CategoriaModel.setRegistrationDate(CategoriaModelOptional.get().getRegistrationDate());
            return ResponseEntity.status(HttpStatus.OK).body(categoriaServ.save(CategoriaModel));
        }

}
