package com.jvb_intern.rental_accommodation.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accommodate")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Accommodate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodateId;

    @Column(name = "room_price")
    private Double roomPrice;

    @Column(name = "water_price")
    private Double waterPrice;

    @Column(name = "electric_price")
    private Double electricPrice;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "area")
    private Double area;

    @Column(name = "size")
    private Integer size;

    @Column(name = "room_status")
    private Boolean roomStatus;

    @Column(name = "price_category")
    private String priceCategory;

    @Column(name = "with_host")
    private String withHost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}