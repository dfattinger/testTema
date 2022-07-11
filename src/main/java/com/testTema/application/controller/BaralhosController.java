package com.testTema.application.controller;

import com.testTema.domain.entity.Baralhos;
import com.testTema.domain.entity.BaralhosCartas;
import com.testTema.infra.config.service.BaralhosCartasServiceImpl;
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
@RequestMapping("/baralho")
public class BaralhosController {
    private final BaralhosCartasServiceImpl baralhosCartasServiceImpl;

    @ApiOperation(
            value = "Buscar todos os Baralhos.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.listAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar baralho por ID.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{idBaralho}")
    public ResponseEntity<?> findByIdBaralho(
            @PathVariable(name = "idBaralho", required = true) Long idBaralho) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.findByIdBaralho(idBaralho));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar baralho por NOME.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/nome")
    public ResponseEntity<?> findByNomBaralho(
            @RequestParam(name = "nomBaralho", required = true) String nomBaralho) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.findByNomBaralho(nomBaralho));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Buscar baralho por CLASSE.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/classe")
    public ResponseEntity<?> findByNomClasse(
            @RequestParam(name = "nomClasse", required = true) String nomClasse) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.findByNomClasse(nomClasse));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Excluir um baralho pelo ID.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/{idBaralho}")
    public ResponseEntity<?> deleteById(
            @PathVariable(name = "idBaralho", required = true) Long idBaralho) throws Exception {
        try{
            baralhosCartasServiceImpl.deleteById(idBaralho);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Inserir uma Carta no Baralho.",
            response = BaralhosCartas.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<?> insert(
            @RequestBody BaralhosCartas baralhosCartas) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.insert(baralhosCartas));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiOperation(
            value = "Inserir um Baralho.",
            response = Baralhos.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/baralho")
    public ResponseEntity<?> insertBaralho(
            @RequestBody Baralhos baralhos) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(baralhosCartasServiceImpl.insertBaralho(baralhos));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}