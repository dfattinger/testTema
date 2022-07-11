package com.testTema.application.controller;

import com.testTema.domain.entity.Cartas;
import com.testTema.infra.config.service.CartasServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/carta")
public class CartasController {
    private final CartasServiceImpl cartasServiceImpl;

    @ApiOperation(
            value = "Buscar todas as cartas.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.listAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar a carta por ID.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{idCarta}")
    public ResponseEntity<?> findByIdCarta(
            @PathVariable(name = "idCarta", required = true) Long idCarta) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.findById(idCarta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar a carta por NOME.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/nome")
    public ResponseEntity<?> findByNomCarta(
            @RequestParam(name = "nomCarta", required = true) String nomCarta) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.findByNomCarta(nomCarta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar a carta por CLASSE.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/classe")
    public ResponseEntity<?> findByNomClasse(
            @RequestParam(name = "nomClasse", required = true) String nomClasse) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.findByNomClasse(nomClasse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar a carta por TIPO.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/tipo")
    public ResponseEntity<?> findByNomTipo(
            @RequestParam(name = "nomTipo", required = true) String nomTipo) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.findByNomTipo(nomTipo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Excluir uma carta pelo ID.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{idCarta}")
    public ResponseEntity<?> deleteById(
            @PathVariable(name = "idCarta", required = true) Long idCarta) throws Exception {
        try{
            cartasServiceImpl.deleteById(idCarta);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Inserir uma Carta.",
            response = Cartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<?> insert(
            @RequestBody Cartas cartas) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cartasServiceImpl.insert(cartas));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}