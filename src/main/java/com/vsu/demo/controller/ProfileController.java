package com.vsu.demo.controller;

import com.vsu.demo.entity.Profile;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController("/profile")
public class ProfileController {
    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable UUID id) {
        return new Profile(id, id.toString(), BigDecimal.ONE);
    }

//    @PostMapping
//    public Profile createProfile(@RequestBody Profile profile) {
//        return new Profile(UUID.randomUUID(), request.login(), request.balance());
//    }

}
