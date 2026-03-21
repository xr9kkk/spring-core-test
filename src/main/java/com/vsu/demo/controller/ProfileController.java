package com.vsu.demo.controller;

import com.vsu.demo.request.CreateProfileRequest;
import com.vsu.demo.entity.Profile;
import com.vsu.demo.service.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public Profile getProfileById(@PathVariable UUID id) {
        return new Profile(id, id.toString(), BigDecimal.ONE);
    }

    @PostMapping
    public Profile createProfile(@RequestBody CreateProfileRequest request) {
        return profileService.createProfile(request);
    }
}