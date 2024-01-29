package com.example.demo.controller;

import com.example.demo.model.Voiture;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VoitureMapper {
    public static Voiture convertDTOToEntity(VoiturePostDTO dto) {
        Voiture entity = new Voiture();
        entity.setMarque(dto.getBrand());
        entity.setModele(dto.getModel());
        entity.setCouleur(dto.getColor());
        entity.setAnnee(dto.getYear());

        if(dto instanceof VoitureGetDTO) {
            int yearImmatriculation = LocalDate.now().getYear() - ((VoitureGetDTO) dto).getAge();
            entity.setDateImmatriculation(LocalDate.of(yearImmatriculation,
                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        }
        else {
            entity.setDateImmatriculation(LocalDate.of(dto.getYear(),
                    LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        }

        return entity;
    }

    public static VoitureGetDTO convertEntityToDTO(Voiture entity) {
        VoitureGetDTO dto = new VoitureGetDTO();
        dto.setBrand(entity.getMarque());
        dto.setModel(entity.getModele());
        dto.setYear(entity.getAnnee());
        dto.setColor(entity.getCouleur());

        // Calculate age
        if(entity.getDateImmatriculation() != null) {
            int age = (int)Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), entity.getDateImmatriculation()));
            dto.setAge(age);
        }

        return dto;
    }
}
