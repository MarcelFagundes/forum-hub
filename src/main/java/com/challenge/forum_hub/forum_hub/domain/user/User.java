package com.challenge.forum_hub.forum_hub.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;





//    @ManyToMany
//    @JoinTable(name = "Usuario_Perfil",
//            joinColumns = @JoinColumn(name = "usuario_id"),
//            inverseJoinColumns = @JoinColumn(name = "perfil_id"))

    //private Set<Perfil> perfis;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public User() {};

//    public User(Long id, String name, String email, String password) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    public User(UserCreateData dataUser){
        this.name = dataUser.name();
        this.email = dataUser.email();
        this.password = dataUser.password();
    }
}
