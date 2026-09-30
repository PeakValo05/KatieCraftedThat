package com.craftedthat.organization.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// JPA entity for mailing-list subscriber records.
@Entity 
public class ConnectModel {

    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long connect_id;

    private String email;

    private String role;

    public Long getConnect_id() {
        return connect_id;
    }

    public void setConnect_id(Long connect_id) {
        this.connect_id = connect_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
