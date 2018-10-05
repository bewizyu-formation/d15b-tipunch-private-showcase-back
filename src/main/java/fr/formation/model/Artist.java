package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Artist extends User {

    @Column(unique=true)
    private String artistName ;

    @NotNull
    @Size(max = 200)
    private String shortDescription;

    private String longDescription;

    private String website;

    private String artistEmail;

    private String address;

    private String phoneNumber;

    @ManyToMany
    private ArrayList<Department> allowedDepartment;

    private String picture;

    private ArrayList<Integer> grade;

    public Artist() {
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getArtistEmail() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail = artistEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Department> getAllowedDepartment() {
        return allowedDepartment;
    }

    public void setAllowedDepartment(ArrayList<Department> allowedDepartment) {
        this.allowedDepartment = allowedDepartment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<Integer> getGrade() {
        return grade;
    }

    public void setGrade(ArrayList<Integer> grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(artistName, artist.artistName) &&
                Objects.equals(shortDescription, artist.shortDescription) &&
                Objects.equals(longDescription, artist.longDescription) &&
                Objects.equals(website, artist.website) &&
                Objects.equals(artistEmail, artist.artistEmail) &&
                Objects.equals(address, artist.address) &&
                Objects.equals(phoneNumber, artist.phoneNumber) &&
                Objects.equals(allowedDepartment, artist.allowedDepartment) &&
                Objects.equals(picture, artist.picture) &&
                Objects.equals(grade, artist.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), artistName, shortDescription, longDescription, website, artistEmail, address, phoneNumber, allowedDepartment, picture, grade);
    }
}
