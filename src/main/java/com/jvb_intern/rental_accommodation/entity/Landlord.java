package com.jvb_intern.rental_accommodation.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Landlord")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long landlordId;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, name = "email", unique = true)
    private String landlordEmail;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "varification_code")
    private String varificationCode;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    List<TenantDating> tenantDatings;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    List<LandlordDating> landlordDatings;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL)
    List<Accommodate> accommodates;
}
