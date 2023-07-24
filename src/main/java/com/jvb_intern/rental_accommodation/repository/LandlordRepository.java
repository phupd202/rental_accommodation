package com.jvb_intern.rental_accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvb_intern.rental_accommodation.entity.Landlord;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    Landlord findByLandlordEmail(String email);
    
}
