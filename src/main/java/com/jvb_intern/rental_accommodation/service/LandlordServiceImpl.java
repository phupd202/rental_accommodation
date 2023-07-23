package com.jvb_intern.rental_accommodation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvb_intern.rental_accommodation.dto.LandlordDto;
import com.jvb_intern.rental_accommodation.entity.Landlord;
import com.jvb_intern.rental_accommodation.repository.LandlordReposiry;

@Service
public class LandlordServiceImpl implements LandlordService {
    @Autowired
    private LandlordReposiry landlordReposiry;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LandlordServiceImpl(LandlordReposiry landlordReposiry, PasswordEncoder passwordEncoder) {
        this.landlordReposiry = landlordReposiry;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveLandlord(LandlordDto landlordDto) {
        Landlord newLandlord = new Landlord();
        newLandlord.setName(landlordDto.getName());
        newLandlord.setName(landlordDto.getEmail());
        newLandlord.setPhone(landlordDto.getPhone());
        newLandlord.setRole(landlordDto.getRole());

        String hashedPassword = passwordEncoder.encode(landlordDto.getPassword());
        newLandlord.setPassword(hashedPassword);
        landlordReposiry.save(newLandlord);
    }

    @Override
    public Landlord findByTenantEmail(String email) {
        return landlordReposiry.findByLandlordEmail(email);
    }

    @Override
    public List<LandlordDto> findAllTenant() {
        List<Landlord> landlords = landlordReposiry.findAll();
        return (landlords.stream())
                .map((landlord) -> mapLandlordDto(landlord)).collect(Collectors.toList());
    }

    // convert entity to dto
    private LandlordDto mapLandlordDto(Landlord tenant) {
        LandlordDto landlordDto = new LandlordDto();
        landlordDto.setName(tenant.getName());
        landlordDto.setEmail(tenant.getLandlordEmail());
        landlordDto.setPhone(tenant.getPhone());
        return landlordDto;
    }
}
