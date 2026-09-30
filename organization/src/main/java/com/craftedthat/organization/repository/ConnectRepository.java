package com.craftedthat.organization.repository;
    
import org.springframework.data.repository.CrudRepository;

import com.craftedthat.organization.models.ConnectModel;

// Provides Spring Data persistence operations for subscriber records.
public interface ConnectRepository extends CrudRepository<ConnectModel, String> {
    
}
