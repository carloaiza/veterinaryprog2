package com.pets.veterinaryprog2.controller;

import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.City;
import com.pets.veterinaryprog2.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/veterinary")
public class VeterinaryController {
    @Autowired
    private VeterinaryService veterinaryService;

    @GetMapping(path = "/cities")
    public ResponseEntity<ResponseDTO> getAllCities(){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        veterinaryService.getCities(),
                        null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/cities/{id}")
    public ResponseEntity<ResponseDTO> getCityById(@PathVariable String id){
        try {
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.OK.value(),
                            veterinaryService.findCityById(id),
                            null),
                    HttpStatus.OK);
        }
        catch (VeterinaryException e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "/cities/bydescription/{description}")
    public ResponseEntity<ResponseDTO> getCityByName(@PathVariable String description){
        if(description != null && !description.equals("")){
            try {
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.OK.value(),
                                veterinaryService.findCityByName(description),
                                null)
                        ,HttpStatus.OK);
            } catch (VeterinaryException e){
                List<String> errors = new ArrayList<>();
                errors.add(e.getMessage());
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                                null,
                                errors),
                        HttpStatus.OK);
            }
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add("La description no puede ser vacía");
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }

    }

    @GetMapping(path = "/cities/by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByInitialLetter(
            @PathVariable char letter
    ){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                       veterinaryService.findCitiesByInitialLetter(letter),
                        null)
                ,HttpStatus.OK);
    }

}
