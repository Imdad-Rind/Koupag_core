package com.Koupag.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashModel extends RequestItemModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    Double amount;
}
