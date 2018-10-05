package fr.formation.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Department {

    @NotNull
    private Integer id ;
    @NotNull
    @Size(max = 255)
    private String name ;
    @NotNull
    @Size(max = 3)
    private String code ;

}
