package com.Koupag.mappers.models_map;

import com.Koupag.models.Address;
import com.Koupag.models.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressMap {
    private String areaName;
    private String city;
    private LocationMap location;

    public AddressMap(Address address){
        this.areaName = address.getAreaName();
        this.city = address.getCity();
        this.location = new LocationMap(address.getLocation());
    }
}
