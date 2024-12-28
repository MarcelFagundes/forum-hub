package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "response")
@Entity(name = "Response")
@EqualsAndHashCode(of = "id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_topics", nullable = false)
    private Topics topics;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_author", nullable = true)
    private User author;

    private Boolean solution;

    // Construtor completo
    public Response(Long id, String message, LocalDateTime creationDate, Boolean solution, User author, Topics topics) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate != null ? creationDate : LocalDateTime.now();
        this.solution = solution != null ? solution : Boolean.FALSE;
        this.author = author;
        this.topics = topics;
    }

    // Construtor padrão
    public Response() {}

    // Construtor usando dados da classe ResponseCreateData
    public Response(@Valid ResponseCreateData data) {
        this.message = data.message();
        this.creationDate = LocalDateTime.now();
        this.solution = data.solution() != null ? data.solution() : Boolean.FALSE;
        this.author = data.author();
        this.topics = data.topics();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Topics getTopics() {
        return topics;
    }

    public void setTopics(Topics topics) {
        this.topics = topics;
    }
}


//package com.challenge.forum_hub.forum_hub.domain.response;
//
//import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
//
//import com.challenge.forum_hub.forum_hub.domain.user.User;
//import com.challenge.forum_hub.forum_hub.domain.user.UserCreateData;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import jakarta.validation.Valid;
//import lombok.EqualsAndHashCode;
//
//import java.time.LocalDateTime;
//
//@Table(name = "response")
//@Entity(name = "Response")
//@EqualsAndHashCode(of = "id")
//public class Response {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String message;
//
////    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "fk_topics")
//    private Topics topics;
//
//    @Column(name = "creation_date")
//    private LocalDateTime creationDate;
//
//    @ManyToOne
//    @JoinColumn(name = "fk_author")
//    private User author;
//
//    private Boolean solution;
//
//    public Response(Long id, String message, LocalDateTime creationDate, Boolean solution, User author, Topics topics) {
//        this.id = id;
//        this.message = message;
//        this.topics = topics;
//        this.creationDate = LocalDateTime.now();
//        this.author = author;
//        this.solution = solution;
//    }
//
//    public Response() {}
//
//    public Response(@Valid ResponseCreateData data) {
//        this.message = data.message();
////        this.topics = new Topics(data.topics());
//        this.creationDate = LocalDateTime.now();
////        this.author = new User(author);
//        this.solution = data.solution();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public Boolean getSolution() {
//        return solution;
//    }
//
//    public void setSolution(Boolean solution) {
//        this.solution = solution;
//    }
//
//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
//
//    public Topics getTopics() {
//        return topics;
//    }
//
//    public void setTopics(Topics topics) {
//        this.topics = topics;
//    }
//}