package fr.formation.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class City {

    @NotNull
    private Long id;
    @NotNull
    @Size(max = 3)
    private String departmentCode;
    @NotNull
    @Size(max = 255)
    private String name;

    public City() {
    }
}
