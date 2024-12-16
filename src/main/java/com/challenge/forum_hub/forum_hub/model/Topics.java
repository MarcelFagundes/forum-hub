package com.challenge.forum_hub.forum_hub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;



@Table(name = "topics")
@Entity(name = "Topics")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;

    private LocalDateTime creation_date;

    @Enumerated(EnumType.STRING)
    private TopicStatus topic_status;

    private String author;
    private String course;

    public Topics(Long id, String title, String message, LocalDateTime creation_date, TopicStatus topic_status, String author, String course) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.creation_date = LocalDateTime.now();
        this.topic_status = topic_status;
        this.author = author;
        this.course = course;
    }

    public Topics() {};

    public Topics(TopicsCreateData data){
        this.title = data.title();
        this.message = data.message();
//        LocalDateTime creation_date;
        this.creation_date = LocalDateTime.now();
        this.topic_status = data.topicStatus();
        this.author = data.author();
        this.course = data.course();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public TopicStatus getTopic_status() {
        return topic_status;
    }

    public void setTopic_status(TopicStatus topic_status) {
        this.topic_status = topic_status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}