package com.example.demo.service;

import com.example.demo.dao.VoitureRepository;
import com.example.demo.model.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {
    @Autowired
    private VoitureRepository voitureRepository;

    public void save(Voiture voiture){
        voitureRepository.save(voiture);
    }

    public Optional<Voiture> findById(Integer id) {
        return voitureRepository.findById(id);
    }

    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    public void delete(Integer id) {
        voitureRepository.deleteById(id);
    }

    public void update(Voiture voiture) {
        voitureRepository.save(voiture);
    }
}
