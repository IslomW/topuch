package com.sharipov.topuch.domain.service.impl;

import com.sharipov.topuch.domain.entity.Profile;
import com.sharipov.topuch.domain.repository.ProfileRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProfileServiceImplTest {

    private final ProfileRepository repository = mock(ProfileRepository.class);
    private final ProfileServiceImpl service = new ProfileServiceImpl(repository);

    @Test
    void createProfileDoesNotAcceptClientControlledTrustFactor() {
        Profile profile = new Profile();
        profile.setTrustFactor(10);
        when(repository.save(profile)).thenReturn(profile);

        Profile saved = service.createProfile(profile);

        assertThat(saved.getTrustFactor()).isEqualTo(Profile.DEFAULT_TRUST_FACTOR);
        assertThat(saved.getCreatedAt()).isNotNull();
        verify(repository).save(profile);
    }

    @Test
    void profileUpdatePreservesServerManagedTrustFactor() {
        UUID profileId = UUID.fromString("01991ee8-9a70-7000-8000-000000000002");
        Profile existing = new Profile();
        existing.setTrustFactor(8);
        Profile changes = new Profile();
        changes.setFirstName("Updated");
        when(repository.findById(profileId)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Profile saved = service.updateProfile(profileId, changes);

        assertThat(saved.getFirstName()).isEqualTo("Updated");
        assertThat(saved.getTrustFactor()).isEqualTo(8);
        verify(repository).save(existing);
    }
}
