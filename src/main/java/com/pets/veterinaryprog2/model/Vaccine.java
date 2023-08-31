package com.pets.veterinaryprog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vaccine {
    private City city;
    private Vet vet;
    private short quantity;
}
