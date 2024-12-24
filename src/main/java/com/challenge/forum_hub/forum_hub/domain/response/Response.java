package com.challenge.forum_hub.forum_hub.domain.response;

import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import com.challenge.forum_hub.forum_hub.domain.user.UserCreateData;
import jakarta.persistence.*;
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

    @ManyToOne
    private Topics topics;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    private User author;

    private Boolean solution;




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
    public Response(ResponseCreateData dataResponse) {
        this.message = message;
        this.creationDate = creationDate;
        this.solution = solution;
        this.author = author;
        this.topics = topics;
    }

    public Response() {}

    public Response(Long id, String message, LocalDateTime creationDdate, Boolean solution, User author, Topics topics) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
        this.solution = solution;
        this.author = author;
        this.topics = topics;
    }
}
