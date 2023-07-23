package com.jvb_intern.rental_accommodation.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tenant")

public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantId;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, name = "email", unique = true)
    private String tenantEmail;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(nullable = false, name ="password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "varification_code")
    private String varificationCode;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<NotificationDating> notificationDating;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<TenantDating> tenantDating;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<LandlordDating> landlordDating;

    @OneToOne(mappedBy = "tenant")
    private TenantCriteria tenantCriteria;
}
