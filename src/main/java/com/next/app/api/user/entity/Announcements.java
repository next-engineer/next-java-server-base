package com.next.app.api.user.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "announcements")
@Data
public class Announcements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcement_id;

    @Column(nullable = false,unique = true,length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String created_by;

    @Column(name = "published_at")
    private java.time.LocalDateTime createdAt;

    public Announcements() {}
    public Announcements(String title,String content,String created_by){
        this.title=title;
        this.content=content;
        this.created_by=created_by;
    }

    public Long getAnnouncement_id (){
        return announcement_id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getCreated_by(){
        return created_by;
    }

    public void setCreated_by(String created_by){
        this.created_by=created_by;
    }
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }
}
