package com.jvb_intern.rental_accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jvb_intern.rental_accommodation.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    Admin findByLandlordEmail(String email);
}
