package com.platzi.ver_pelis_plus.services.interfaces;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface VerPelisAiService {

    @UserMessage("""
        Genera un saludo de bienvenida a la plataforma de películas de {{platform}}.
        Usa menos de 120 caracteres y hazlo con el estilo de un chico de 10 años.
    """)
    String generateGreeting(@V("platform") String platform);
}
