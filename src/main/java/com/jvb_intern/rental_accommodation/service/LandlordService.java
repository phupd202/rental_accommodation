package com.jvb_intern.rental_accommodation.service;

import java.util.List;

import com.jvb_intern.rental_accommodation.dto.LandlordDto;
import com.jvb_intern.rental_accommodation.entity.Landlord;

public interface LandlordService {
    void saveLandlord(LandlordDto landlordDto);
    Landlord findByTenantEmail(String email);
    List<LandlordDto> findAllTenant();
}
