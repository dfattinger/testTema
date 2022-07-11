package com.testTema.infra.config.service;


import com.testTema.domain.entity.Baralhos;
import com.testTema.domain.entity.BaralhosCartas;
import com.testTema.domain.entity.Cartas;
import com.testTema.domain.entity.Classes;
import com.testTema.domain.repository.BaralhosCartasRepository;
import com.testTema.domain.repository.BaralhosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaralhosCartasServiceImpl {

    private final BaralhosCartasRepository baralhosCartasRepository;
    private final BaralhosRepository baralhosRepository;
    private final CartasServiceImpl cartasServiceImpl;
    private static final Long QUALQUER_CLASSE = 5L;
    private static final Long LIMITE_CARTAS_BARALHO = 30L;

    public List<BaralhosCartas> listAll() throws Exception{
        List<BaralhosCartas> baralhosCartasList = baralhosCartasRepository.findAll();
        if(baralhosCartasList.size()==0){
            throw new Exception("Nenhum baralho com carta registrado");
        }
        return baralhosCartasList;
    }

    public List<BaralhosCartas> findByIdBaralho(Long idBaralho) throws Exception{
        this.verifyIfExistsBaralho(idBaralho);
        List<BaralhosCartas> listBaralhosCartas = baralhosCartasRepository.findByBaralhosIdBaralho(idBaralho);
        if(listBaralhosCartas.isEmpty()){
            throw new Exception("Baralho de ID: ".concat(idBaralho.toString()).concat("não possui carta."));
        }
        return listBaralhosCartas;
    }

    private void verifyIfExistsBaralho(Long idBaralho) throws Exception {
        Optional<Baralhos> optionalBaralhos = baralhosRepository.findById(idBaralho);
        if(optionalBaralhos.isEmpty()){
            throw new Exception("Baralho de ID: ".concat(idBaralho.toString()).concat(" não cadastrado."));
        }
    }

    public List<BaralhosCartas> findByNomBaralho(String nomBaralho) throws Exception{
        List<BaralhosCartas> baralhosCartasList = baralhosCartasRepository.findByBaralhosNomBaralhoContainsIgnoreCase(nomBaralho);
        if(baralhosCartasList.size()==0){
            throw new Exception("Baralho não encontrada para o nome informado: ".concat(nomBaralho));
        }
        return baralhosCartasList;
    }

    public List<BaralhosCartas> findByNomClasse(String nomClasse) throws Exception{
        List<BaralhosCartas> baralhosCartasList = baralhosCartasRepository.findByCartasClassesNomClasseContainsIgnoreCase(nomClasse);
        if(baralhosCartasList.size()==0){
            throw new Exception("Baralho não encontrada para o nome da classe informado: ".concat(nomClasse));
        }
        return baralhosCartasList;
    }

    public void deleteById(Long idBaralho) throws Exception{
        List<BaralhosCartas> listBaralhosCartas = this.findByIdBaralho(idBaralho);
        try {
            baralhosCartasRepository.deleteByBaralhosIdBaralho(idBaralho);
            baralhosRepository.deleteById(idBaralho);
        }catch (Exception e){
            throw new Exception("Não foi possível excluir o Baralho de ID: ".concat(idBaralho.toString()));
        }
    }

    public BaralhosCartas insert(BaralhosCartas baralhosCartas) throws Exception{
        this.verifyIfExistsBaralho(baralhosCartas.getBaralhos().getIdBaralho());
        this.verifyIfExistsCarta(baralhosCartas);
        BaralhosCartas baralhosCartasSalva = new BaralhosCartas();
        try {
            baralhosCartasSalva = baralhosCartasRepository.save(baralhosCartas);
            return baralhosCartasRepository.findById(baralhosCartasSalva.getIdBaralhoCarta())
                    .orElse(new BaralhosCartas());
        }catch (Exception e){
            throw new Exception("Não foi possível inserir a Carta no Baralho");
        }
    }

    private void verifyIfExistsCarta(BaralhosCartas baralhosCartas) throws Exception {
        Optional<Cartas> optionalCartas = cartasServiceImpl.findById(baralhosCartas.getCartas().getIdCarta());
        this.verifyIfExistsCartaBaralho(baralhosCartas.getBaralhos().getIdBaralho(), optionalCartas.get().getIdCarta());
        this.verifyTotalCartaBaralhoPerClasse(baralhosCartas, optionalCartas);
    }

    private void verifyIfExistsCartaBaralho(
            Long idBaralho,
            Long idCarta) throws Exception {
        List<BaralhosCartas> listBaralhosCartas = baralhosCartasRepository.findByBaralhosIdBaralhoAndCartasIdCarta(
                idBaralho,
                idCarta);
        if(listBaralhosCartas.size()>=2L){
            throw new Exception("Essa carta já foi inserida 2x nesse baralho.");
        }
    }

    private void verifyTotalCartaBaralhoPerClasse(
            BaralhosCartas baralhosCartas,
            Optional<Cartas> optionalCartas) throws Exception{
        List<BaralhosCartas> listBaralhosCartas = this.findByIdBaralho(baralhosCartas.getBaralhos().getIdBaralho());
        List<Long> listaIdClasse = listBaralhosCartas.stream().map(BaralhosCartas::getCartas).collect(Collectors.toList())
                .stream().map(Cartas::getClasses).collect(Collectors.toList())
                .stream().map(Classes::getIdClasse).collect(Collectors.toList());
        List<Long> listaIdClasseDistinct = listaIdClasse.stream().distinct().collect(Collectors.toList());
        this.verifyClasseDif(baralhosCartas, optionalCartas, listaIdClasseDistinct);
        listaIdClasseDistinct.add(QUALQUER_CLASSE);
        List<BaralhosCartas> listBaralhosCartasClasse = baralhosCartasRepository.findByCartasClassesIdClasseInAndBaralhosIdBaralho(
                listaIdClasseDistinct,
                baralhosCartas.getBaralhos().getIdBaralho());
        if(listBaralhosCartasClasse.size()==LIMITE_CARTAS_BARALHO){
            throw new Exception("Já foram inseridas ".concat(LIMITE_CARTAS_BARALHO.toString())
                    .concat(" cartas no baralho, que é o Limite máximo permitido."));
        }
    }

    private void verifyClasseDif(
            BaralhosCartas baralhosCartas,
            Optional<Cartas> optionalCartas,
            List<Long> listaIdClasseDistinct) throws Exception{
        if(((listaIdClasseDistinct.size()>=2L)
                &&(!listaIdClasseDistinct.contains(optionalCartas.get().getClasses().getIdClasse())))
        ||((listaIdClasseDistinct.size()==1L)
                &&(!listaIdClasseDistinct.contains(optionalCartas.get().getClasses().getIdClasse())))
                &&(!listaIdClasseDistinct.contains(QUALQUER_CLASSE))
                &&(!optionalCartas.get().getClasses().getIdClasse().equals(QUALQUER_CLASSE))
        ){
            throw new Exception("Não é possível inserir cartas com mais de uma classe (serão aceitas as cartas da mesma classe ou de Qualquer Classe) .");
        }
    }

    public Baralhos insertBaralho(Baralhos baralhos) throws Exception{
        this.verifyIfExistsBaralhoWithName(baralhos.getNomBaralho());
        Baralhos baralhosSalvo = new Baralhos();
        try {
            baralhosSalvo = baralhosRepository.save(baralhos);
            return baralhosRepository.findById(baralhosSalvo.getIdBaralho())
                    .orElse(new Baralhos());
        }catch (Exception e){
            throw new Exception("Não foi possível inserir o Baralho");
        }
    }

    private void verifyIfExistsBaralhoWithName(String nomBaralho) throws Exception {
        List<Baralhos> listaBaralhos = baralhosRepository.findByNomBaralhoContainsIgnoreCase(nomBaralho);
        if(listaBaralhos.size()>0){
            throw new Exception("Baralho com essa descrição já existe.");
        }
    }

}