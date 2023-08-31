package com.pets.veterinaryprog2.service;

import com.pets.veterinaryprog2.exceptions.VeterinaryException;
import com.pets.veterinaryprog2.model.City;
import com.pets.veterinaryprog2.model.Vaccine;
import com.pets.veterinaryprog2.model.Vet;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class VeterinaryService {
    private List<Vet> vets;
    private List<City> cities;
    private List<Vaccine> vaccines;

    public VeterinaryService() {
        //Conecto a la base de datos y cargo las ciudades y veterinarios
        cities = new ArrayList<>();
        cities.add(new City("16917001", "Manizales"));
        cities.add(new City("16917063", "Pereira"));

        cities.add(new City("16919001", "Popayán"));

        cities.add(new City("16915001", "Pasto"));
    }

    public City findCityById(String id) throws VeterinaryException {
        for(City cityFound : this.getCities()){
            if(cityFound.getCode().equals(id)){
                return cityFound;
            }
        }
        //no lo encontró
        throw  new VeterinaryException("La ciudad con "+id+
                " no existe");
    }

    public City findCityByName(String description) throws VeterinaryException
    {
        for(City cityFound : this.getCities()){
            if(cityFound.getDescription()
                    .equalsIgnoreCase(description)){
                return cityFound;
            }
        }
        //no lo encontró
        throw  new VeterinaryException("La ciudad con "+description+
                " no existe");
    }


    public List<City> findCitiesByInitialLetter(char letter){
        List<City> citiesFound = new ArrayList<>();
        for(City city: this.getCities()){
            if(city.getDescription().charAt(0)==letter){
                citiesFound.add(city);
            }
        }
        return citiesFound;
    }

}
