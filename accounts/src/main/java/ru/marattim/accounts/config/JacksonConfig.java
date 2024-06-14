package ru.marattim.accounts.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper getCustomObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        mapper.registerModule(simpleModule);
        return mapper;
    }

    private static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

        @Override
        public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeNumber(bigDecimal.setScale(2, RoundingMode.HALF_EVEN));
        }
    }
}
