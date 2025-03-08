package com.dinef.simplechatjava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The text content of the message
    private String text;

    // Timestamp when the message was created
    private LocalDateTime createdTime;

    // Many-to-One relationship to the User entity.
    // Each message is associated with one user.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Foreign key column in the "messages" table
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public Message() {
        this.createdTime = LocalDateTime.now();
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
        this.createdTime = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
