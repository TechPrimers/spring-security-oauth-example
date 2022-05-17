package com.suchen.security.springsecurityauthserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {

    private List<Location> locations = new ArrayList<Location>();

    public LocationController() {
        Location location1 = new Location();
        location1.setName("location1");
        Location location2 = new Location();
        location2.setName("location2");
        locations.add(location1);
        locations.add(location2);
    }

    @GetMapping("/locations")
    public List<Location> locations() {
        return locations;
    }

    @GetMapping("/location/{name}")
    public Location location(@PathVariable("name") String name) {
        return locations.stream().filter((locaton) -> locaton.getName().equals(name)).findFirst().orElse(null);
    }

    static class Location {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;
    }
}
