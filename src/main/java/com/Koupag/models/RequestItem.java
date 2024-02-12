package com.Koupag.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID Id;

    int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surplus_Material")
    SurplusMaterial surplusMaterial;
    
    @OneToOne(mappedBy = "item", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    DonationRequest request;

   public RequestItem(int count, SurplusMaterial surplusMaterial) {
       this.count = count;
       this.surplusMaterial = surplusMaterial;
   }
}
