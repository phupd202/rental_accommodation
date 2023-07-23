package com.jvb_intern.rental_accommodation.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Landlord_Dating")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class LandlordDating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datingId;

    @Column(name = "dating_time")
    private LocalDate datingTime;

    @Column(name = "confirm_status")
    private Boolean confirmStatus;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

}
