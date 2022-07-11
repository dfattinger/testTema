package com.testTema.infra.config.service;


import com.testTema.domain.entity.Cartas;
import com.testTema.domain.repository.CartasRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartasServiceImpl {

    private final CartasRepository cartasRepository;

    public List<Cartas> listAll() throws Exception{
        List<Cartas> cartasList = cartasRepository.findAll();
        if(cartasList.size()==0){
            throw new Exception("Nenhuma carta registrada");
        }
        return cartasList;
    }

    public Optional<Cartas> findById(Long idCarta) throws Exception{
        Optional<Cartas> optionalCartas = cartasRepository.findById(idCarta);
        if(optionalCartas.isEmpty()){
            throw new Exception("Carta não encontrada para o ID: ".concat(idCarta.toString()));
        }
        return optionalCartas;
    }

    public List<Cartas> findByNomCarta(String nomCarta) throws Exception{
        List<Cartas> cartasList = cartasRepository.findByNomCartaContainsIgnoreCase(nomCarta);
        if(cartasList.size()==0){
            throw new Exception("Carta não encontrada para o nome informado: ".concat(nomCarta));
        }
        return cartasList;
    }

    public List<Cartas> findByNomClasse(String nomClasse) throws Exception{
        List<Cartas> cartasList = cartasRepository.findByClassesNomClasseContainsIgnoreCase(nomClasse);
        if(cartasList.size()==0){
            throw new Exception("Carta não encontrada para o nome da classe informado: ".concat(nomClasse));
        }
        return cartasList;
    }

    public List<Cartas> findByNomTipo(String nomTipo) throws Exception{
        List<Cartas> cartasList = cartasRepository.findByTiposNomTipoContainsIgnoreCase(nomTipo);
        if(cartasList.size()==0){
            throw new Exception("Carta não encontrada para o nome do tipo informado: ".concat(nomTipo));
        }
        return cartasList;
    }

    public void deleteById(Long idCarta) throws Exception{
        Optional<Cartas> optionalCartas = this.findById(idCarta);
        try {
            cartasRepository.deleteById(idCarta);
        }catch (Exception e){
            throw new Exception("Não foi possível excluir a Carta, pois há um baralho cadastrado para essa carta de ID: ".concat(idCarta.toString()));
        }
    }

    public Cartas insert(Cartas cartas) throws Exception{
        this.verifyCartas(cartas);
        this.verifyExistsCarta(cartas.getNomCarta());
        Cartas cartasSalva = new Cartas();
        try {
            cartasSalva = cartasRepository.save(cartas);
            return cartasSalva;
        }catch (Exception e){
            throw new Exception("Não foi possível inserir a Carta");
        }
    }

    public void verifyCartas(Cartas cartas) throws Exception{
        if(cartas.getValAtaque().longValue()<0){
            throw new Exception("O valor do Ataque não pode ser inferir a 0 (zero)");
        }
        if(cartas.getValAtaque().longValue()>10){
            throw new Exception("O valor do Ataque não pode ser superior a 10 (dez)");
        }
        if(cartas.getValDefesa().longValue()<0){
            throw new Exception("O valor da Defesa não pode ser inferir a 0 (zero)");
        }
        if(cartas.getValDefesa().longValue()>10){
            throw new Exception("O valor da Defesa não pode ser superior a 10 (dez)");
        }
    }

    public void verifyExistsCarta(String nomCarta) throws Exception{
        List<Cartas> listCartas = cartasRepository.findByNomCartaContainsIgnoreCase(nomCarta);
        if(listCartas.size()>0){
            throw new Exception("Carta já cadastrada.");
        }
    }
}