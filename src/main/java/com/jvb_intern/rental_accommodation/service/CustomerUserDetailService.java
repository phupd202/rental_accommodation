package com.jvb_intern.rental_accommodation.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jvb_intern.rental_accommodation.entity.Landlord;
import com.jvb_intern.rental_accommodation.entity.Tenant;
import com.jvb_intern.rental_accommodation.repository.LandlordRepository;
import com.jvb_intern.rental_accommodation.repository.TenantRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.equals("admin@gmail.com")) {
            String password = "admin-password";
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(email, password, authorities);
        } else {
            Tenant tenant = tenantRepository.findByTenantEmail(email);
            // find email in 2 tables Tenant and Landlord
            if(tenant != null) {
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_TENANT"));
                return new org.springframework.security.core.userdetails.User(tenant.getTenantEmail(), tenant.getPassword(), authorities);
            } else {
                Landlord landlord = landlordRepository.findByLandlordEmail(email);
                if(landlord != null) {
                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_LANDLORD"));
                    return new org.springframework.security.core.userdetails.User(landlord.getLandlordEmail(), landlord.getLandlordEmail(), authorities);
                } else {
                    throw new UsernameNotFoundException("Không tìm thấy tài khoản trong hệ thống");
                }
            }
        }
    }
}
