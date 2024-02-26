package com.example.carom.Repository;


import com.example.carom.Entity.Owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByUsername(String username);


}