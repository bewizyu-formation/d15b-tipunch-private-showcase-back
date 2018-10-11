package fr.formation.model;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private String departmentCode;

    public City(){}

    public City(int id, String name, String deptCode){
        this.id = id;
        this.name = name;
        this.departmentCode = deptCode;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(departmentCode, city.departmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentCode);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}
