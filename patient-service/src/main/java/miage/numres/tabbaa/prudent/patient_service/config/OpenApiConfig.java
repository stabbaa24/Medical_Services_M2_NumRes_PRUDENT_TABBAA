package miage.numres.tabbaa.prudent.patient_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI patientServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Service Patient API")
                        .description("API du service de gestion des patients")
                        .version("1.0"));
    }
}