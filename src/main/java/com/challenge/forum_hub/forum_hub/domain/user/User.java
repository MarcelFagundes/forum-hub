package com.challenge.forum_hub.forum_hub.domain.user;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Topics> topics;

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

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

    public User() {};

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UserCreateData data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    }

    @Override
    public String toString() {
        return  name;
    }
}
