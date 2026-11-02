package com.platzi.ver_pelis_plus;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.platzi.ver_pelis_plus.config.GreetingConfiguration;

class GreetingConfigurationTest {
    private final GreetingConfiguration configuration = new GreetingConfiguration();

    @Test
    void localGreetingWorksWithoutAnyApiKey() {
        String greeting = configuration.verPelisAiService(false, "", "gpt-4o-mini")
                .generateGreeting("Ver Pelis plus");
        assertTrue(greeting.contains("Ver Pelis plus"));
    }

    @Test
    void aiRequiresAnExplicitKey() {
        assertThrows(IllegalStateException.class,
                () -> configuration.verPelisAiService(true, "", "gpt-4o-mini"));
    }

    @Test
    void aiClientCanBeCreatedWithoutMakingNetworkCalls() {
        assertNotNull(configuration.verPelisAiService(true, "test-key", "gpt-4o-mini"));
    }
}
