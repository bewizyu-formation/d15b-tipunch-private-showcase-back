package fr.formation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(unique = true)
    private String login;

    @NotNull
    @Size(max = 200)
    private String password ;

    @NotNull
    private String email;

    @NotNull
    private Integer city;

    @ManyToMany
    private List<Event> eventsAttended;

    @OneToMany(mappedBy = "organizer")
    private List<Event> eventsOrganized;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", city=" + city +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public List<Event> getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(List<Event> eventsAttended) {
        this.eventsAttended = eventsAttended;
    }

    public List<Event> getEventsOrganized() {
        return eventsOrganized;
    }

    public void setEventsOrganized(List<Event> eventsOrganized) {
        this.eventsOrganized = eventsOrganized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(city, user.city) &&
                Objects.equals(eventsAttended, user.eventsAttended) &&
                Objects.equals(eventsOrganized, user.eventsOrganized);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, city, eventsAttended, eventsOrganized);
    }
}
