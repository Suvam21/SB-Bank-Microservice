package com.bank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
