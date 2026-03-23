package com.vsu.demo.service;

import com.vsu.demo.request.CreateProfileRequest;
import com.vsu.demo.entity.Profile;
import com.vsu.demo.exception.ValidationException;
import com.vsu.demo.repository.ProfileRepository;
import com.vsu.demo.response.ErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.vsu.demo.repository.ProfileRepository;

import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(CreateProfileRequest request) {
        Profile profile = new Profile(UUID.randomUUID(), request.login(), request.balance());
        try {
            boolean created = profileRepository.createProfile(profile);
            if (!created) {
                throw new RuntimeException("Не получилось создать профиль с логином " + profile.login());
            }
            return profile;
        } catch (DataIntegrityViolationException ex) {
            throw new ValidationException(ErrorCode.PROFILE_ALREADY_EXISTS, ex);
        }
    }
}
