package com.platzi.ver_pelis_plus.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.platzi.ver_pelis_plus.services.interfaces.VerPelisAiService;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

@Configuration(proxyBeanMethods = false)
public class GreetingConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingConfiguration.class);

    @Bean
    public VerPelisAiService verPelisAiService(
            @Value("${verpelis.ai.enabled:false}") boolean enabled,
            @Value("${verpelis.ai.api-key:}") String apiKey,
            @Value("${verpelis.ai.model:gpt-4o-mini}") String model) {
        if (!enabled) {
            return GreetingConfiguration::localGreeting;
        }
        if (apiKey.isBlank()) {
            throw new IllegalStateException("Define OPENAI_API_KEY para activar AI_ENABLED=true.");
        }
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .timeout(Duration.ofSeconds(10))
                .maxRetries(0)
                .build();
        VerPelisAiService aiService = AiServices.create(VerPelisAiService.class, chatModel);
        return platform -> {
            try {
                String greeting = aiService.generateGreeting(platform);
                return greeting == null || greeting.isBlank() ? localGreeting(platform) : greeting;
            } catch (RuntimeException e) {
                LOGGER.warn("El proveedor de IA no respondio; se usara el saludo local.");
                return localGreeting(platform);
            }
        };
    }

    private static String localGreeting(String platform) {
        return "¡Bienvenido a " + platform + "! Prepara las palomitas y descubre tu proxima pelicula.";
    }
}
