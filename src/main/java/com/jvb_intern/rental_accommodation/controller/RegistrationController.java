package com.jvb_intern.rental_accommodation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jvb_intern.rental_accommodation.dto.LandlordDto;
import com.jvb_intern.rental_accommodation.dto.RegistrationDto;
import com.jvb_intern.rental_accommodation.dto.TenantDto;
import com.jvb_intern.rental_accommodation.entity.Landlord;
import com.jvb_intern.rental_accommodation.entity.Tenant;
import com.jvb_intern.rental_accommodation.service.LandlordService;
import com.jvb_intern.rental_accommodation.service.TenantService;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private TenantService tenantService;

    @Autowired
    private LandlordService landlordService;

    public RegistrationController(TenantService tenantService, LandlordService landlordService) {
        this.tenantService = tenantService;
        this.landlordService = landlordService;
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registration", registrationDto);
        return "register-test";
    }

    @PostMapping("/register/save")
    public String saveRegistration(@Valid @ModelAttribute("registration") RegistrationDto registrationDto, Model model, BindingResult result) {
        String role = registrationDto.getRole();
        String email = registrationDto.getEmail();
        String password = registrationDto.getPassword();
        String confirmPassword = registrationDto.getConfirmPassword();

        if(role.equals("Người tìm trọ")) {
            Tenant existingTenant = tenantService.findByTenantEmail(email);

            if(existingTenant != null && existingTenant.getTenantEmail() != null && !existingTenant.getTenantEmail().isEmpty()) {
                result.rejectValue("email", null, 
                            "Tài khoản đã tồn tại!!");
            } else if(!password.equals(confirmPassword)) {
                result.rejectValue("email", null, 
                            "Mật khẩu không khớp nhau");
            } else if(result.hasErrors()) {
                model.addAttribute("registration", registrationDto);
                return "register-test";
            } else {
                TenantDto tenantDto = new TenantDto();
                tenantDto.setName(registrationDto.getName());
                tenantDto.setEmail(registrationDto.getEmail());
                tenantDto.setPhone(registrationDto.getPhone());
                tenantDto.setRole("ROLE_TENANT");
                tenantDto.setPassword(registrationDto.getPassword());

                tenantService.saveTenant(tenantDto);
            }
        } else {
            Landlord existingLandlord = landlordService.findByTenantEmail(email);

            if(existingLandlord != null && existingLandlord.getLandlordEmail() != null && !existingLandlord.getLandlordEmail().isEmpty()) {
                result.rejectValue("email", null, 
                            "Tài khoản đã tồn tại!!");
            } else if(!password.equals(confirmPassword)) {
                result.rejectValue("password", null, 
                            "Mật khẩu không khớp nhau");
            } else if(result.hasErrors()) {
                model.addAttribute("registration", registrationDto);
                return "register-test";
            } else {
                LandlordDto landlordDto = new LandlordDto();
                landlordDto.setName(registrationDto.getName());
                landlordDto.setEmail(registrationDto.getEmail());
                landlordDto.setPhone(registrationDto.getPhone());
                landlordDto.setRole("ROLE_LANDLORD");
                landlordDto.setPassword(registrationDto.getPassword());
                
                landlordService.saveLandlord(landlordDto);
            }
        }
        return "redirect:/register?success";
    }
}
