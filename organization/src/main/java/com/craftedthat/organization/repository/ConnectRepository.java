package com.craftedthat.organization.repository;
    
import org.springframework.data.repository.CrudRepository;

import com.craftedthat.organization.models.ConnectModel;

public interface ConnectRepository extends CrudRepository<ConnectModel, String> {
    
}
