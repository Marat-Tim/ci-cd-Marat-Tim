package ru.marattim.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.model.Currency;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "amount", nullable = false, precision = 20, scale = 10)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
}