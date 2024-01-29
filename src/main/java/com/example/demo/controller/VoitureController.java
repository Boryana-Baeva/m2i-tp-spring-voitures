package com.example.demo.controller;

import com.example.demo.model.Voiture;
import com.example.demo.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;

    @PostMapping("voitures")
    public ResponseEntity<?> save(@RequestBody VoiturePostDTO dto) {
        Voiture voiture = VoitureMapper.convertDTOToEntity(dto);

        List<String> errorMsg = getErrorResponseMessage(voiture);
        if(!errorMsg.isEmpty()){
            return ResponseEntity.badRequest().body(errorMsg.toString());
        }
        else {
            voitureService.save(voiture);
            VoitureGetDTO responseDTO = VoitureMapper.convertEntityToDTO(voiture);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
    }

    @GetMapping("voitures/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        Optional<Voiture> op = voitureService.findById(id);

        if(op.isPresent()) {
            VoitureGetDTO dto = VoitureMapper.convertEntityToDTO(op.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("voitures")
    public List<VoitureGetDTO> getAll() {
        List<Voiture> voitures = voitureService.findAll();
        return  voitures.stream().map(v-> VoitureMapper.convertEntityToDTO(v)).toList();
    }

    @DeleteMapping("voitures/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<Voiture> op = voitureService.findById(id);

        if(op.isPresent()) {
            voitureService.delete(id);
            return ResponseEntity.ok("Deleted !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("voitures/{id}")
    public ResponseEntity<?> update(@RequestBody Voiture voiture, @PathVariable("id") Integer id) {
        if(!id.equals(voiture.getId())) {
            return ResponseEntity.badRequest().body("Id mismatch !");
        } else {
            Optional<Voiture> op = voitureService.findById(voiture.getId());

            if(op.isPresent()) {
                List<String> errorMsg = getErrorResponseMessage(voiture);
                if(!errorMsg.isEmpty()){
                    return ResponseEntity.badRequest().body(errorMsg.toString());
                } else {
                    voitureService.update(voiture);
                    return ResponseEntity.ok(voiture);
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    private List<String> getErrorResponseMessage(Voiture voiture) {
        List<String> errors = new ArrayList<>();

        if(voiture.getMarque().isBlank()) {
            errors.add("La marque ne peut pas être vide !");
        }
        if(voiture.getModele().isBlank()) {
            errors.add("Le modele ne peut pas être vide !");
        }
        if(voiture.getAnnee() == null) {
            errors.add("L'année ne peut pas être vide !");
        }
        if(voiture.getCouleur().isBlank()) {
            errors.add("La couleur ne peut pas être vide !");
        }

        return errors;
    }
}
