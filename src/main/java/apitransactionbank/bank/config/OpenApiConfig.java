package apitransactionbank.bank.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Transações Bancárias")
                        .version("1.0")
                        .description("API REST para gerenciar Contas e Transações bancárias.")
                        .contact(new Contact()
                                .name("Samuel Alves")
                                .email("samalves.dev@gmail.com")));
    }
}