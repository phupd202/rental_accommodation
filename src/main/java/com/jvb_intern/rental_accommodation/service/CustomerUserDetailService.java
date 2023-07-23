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

import com.jvb_intern.rental_accommodation.entity.Tenant;
import com.jvb_intern.rental_accommodation.repository.TenantRepository;


@Service
public class CustomerUserDetailService implements UserDetailsService{
    @Autowired
    private TenantRepository tenantRepository;

    // constructor
    public CustomerUserDetailService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tenant tenantUser = tenantRepository.findByTenantEmail(email);

        if(tenantUser != null) {
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_TENANT")); // tạo danh sach bất biến chứa duy nhất 1 phần tử
            return new org.springframework.security.core.userdetails.User(tenantUser.getTenantEmail(), tenantUser.getPassword(), authorities);// trả về đối tượng User chứa thông tin người dùng
        } else {
            throw new UsernameNotFoundException("Không tìm thấy thông tin tài khoản trong hệ thống");
        }
    }
}
