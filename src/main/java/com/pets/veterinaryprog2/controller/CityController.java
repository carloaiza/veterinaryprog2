package com.pets.veterinaryprog2.controller;

import com.pets.veterinaryprog2.controller.dto.ResponseDTO;
import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.City;
import com.pets.veterinaryprog2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllCities(){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                        cityService.getCities(),
                        null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO> getCityById(@PathVariable String id){
        try {
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.OK.value(),
                            cityService.findCityById(id),
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

    @GetMapping(path = "/bydescription/{description}")
    public ResponseEntity<ResponseDTO> getCityByName(@PathVariable String description){
        if(description != null && !description.equals("")){
            try {
                return new ResponseEntity<>(
                        new ResponseDTO(HttpStatus.OK.value(),
                                cityService.findCityByName(description),
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

    @GetMapping(path = "/by_initial_letter/{letter}")
    public ResponseEntity<ResponseDTO> getCitiesByInitialLetter(
            @PathVariable char letter
    ){
        return new ResponseEntity<>(
                new ResponseDTO(HttpStatus.OK.value(),
                       cityService.findCitiesByInitialLetter(letter),
                        null)
                ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createCity(
            @RequestBody City city){
        try {

            return new ResponseEntity<>(
                    new ResponseDTO(
                            HttpStatus.OK.value(),
                            cityService.addCity(city),
                            null
                    ), HttpStatus.OK
            );
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.CONFLICT.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDTO> updateCity(
            @PathVariable String id, @RequestBody City city){
        try {

            return new ResponseEntity<>(
                    new ResponseDTO(
                            HttpStatus.OK.value(),
                            cityService.updateCity(id,city),
                            null
                    ), HttpStatus.OK
            );
        } catch (VeterinaryException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(
                    new ResponseDTO(HttpStatus.NOT_FOUND.value(),
                            null,
                            errors),
                    HttpStatus.OK);
        }
    }

}
