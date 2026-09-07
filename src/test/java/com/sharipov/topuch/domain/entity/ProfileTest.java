package com.sharipov.topuch.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ProfileTest {

    @Test
    void newProfileStartsWithNeutralTrustFactor() {
        Profile profile = new Profile();

        assertThat(profile.getTrustFactor()).isEqualTo(Profile.DEFAULT_TRUST_FACTOR);
    }

    @Test
    void acceptsTrustFactorInsideRange() {
        Profile profile = new Profile();

        profile.setTrustFactor(10);

        assertThat(profile.getTrustFactor()).isEqualTo(10);
    }

    @Test
    void rejectsTrustFactorOutsideRange() {
        Profile profile = new Profile();

        assertThatIllegalArgumentException().isThrownBy(() -> profile.setTrustFactor(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> profile.setTrustFactor(11));
    }
}
