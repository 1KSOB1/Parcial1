package parcial1.parcial1;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ConfiguracionInternacionalizacion {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // Indica que usará messages_es y messages_en
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
