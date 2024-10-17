package dev.eduardovaz.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperConfigTest {

    @Test
    void deveCriarModelMapperComMatchingStrategyLoose() {
        ModelMapperConfig config = new ModelMapperConfig();
        ModelMapper mapper = config.modelMapper();

        assertNotNull(mapper);
        Configuration configuration = mapper.getConfiguration();
        assertEquals(MatchingStrategies.LOOSE, configuration.getMatchingStrategy());
    }
}