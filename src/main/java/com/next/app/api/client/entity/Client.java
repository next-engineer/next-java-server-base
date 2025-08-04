package com.next.app.api.client.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "industry", length = 50, nullable = false)
    private String industry;

    @Column(name = "contact_name", length = 100, nullable = false)
    private String contactName;

    @Column(name = "contact_email", length = 100, nullable = false, unique = true)
    private String contactEmail;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }

}
