package com.jvb_intern.rental_accommodation.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
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
@Table(name = "Notification_Dating")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class NotificationDating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notiDatingId;

    @Column(name = "notification_name")
    private String notificationName;

    @Column(name = "dating_id")
    private Long datingId;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant ;

}
