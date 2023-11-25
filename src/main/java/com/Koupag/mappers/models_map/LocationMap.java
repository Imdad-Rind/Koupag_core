package com.Koupag.mappers.models_map;

import com.Koupag.models.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationMap {
    String latitude;
    String longitude;

    public LocationMap(Location location){
        if(location == null) return;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
