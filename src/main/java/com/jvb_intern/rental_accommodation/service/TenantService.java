package com.jvb_intern.rental_accommodation.service;

import java.util.List;

import com.jvb_intern.rental_accommodation.dto.TenantDto;
import com.jvb_intern.rental_accommodation.entity.Tenant;

public interface TenantService {
    void saveTenant(TenantDto tenantDto);
    Tenant findByTenantEmail(String email);
    List<TenantDto> findAllTenant();
}
