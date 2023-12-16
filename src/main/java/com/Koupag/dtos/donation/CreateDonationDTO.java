package com.Koupag.dtos.donation;

import com.Koupag.models.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonSerialize
public class CreateDonationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long donorId;
    private int count;
    private long surplusMaterialId;
    private List<String> materialImagePaths;
    String description;
    String expectedPickupTime;
    Location location;

    public CreateDonationDTO(long donorId, int count, long surplusMaterialId, List<String> materialImagePaths, String description, String expectedPickupTime, Location location) {
        this.donorId = donorId;
        this.count = count;
        this.surplusMaterialId = surplusMaterialId;
        this.description = description;
        this.expectedPickupTime = expectedPickupTime;// LocalDateTime.parse(, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")) ;
        this.location = location;
    }

    public CreateDonationDTO(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreateDonationDTO createDonationDTO = objectMapper.readValue(jsonString, CreateDonationDTO.class);
            this.donorId = createDonationDTO.getDonorId();
            this.count = createDonationDTO.getCount();
            this.surplusMaterialId = createDonationDTO.getSurplusMaterialId();
            this.materialImagePaths = createDonationDTO.getMaterialImagePaths();
            this.description = createDonationDTO.getDescription();
            this.expectedPickupTime = createDonationDTO.getExpectedPickupTime();
            this.location = createDonationDTO.getLocation();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
