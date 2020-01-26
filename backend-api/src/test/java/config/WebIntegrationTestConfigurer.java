package config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


public abstract class WebIntegrationTestConfigurer {

    protected static Level loggerLevel = Level.INFO;
    protected final Logger logger;
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    {
        Configurator.setRootLevel(loggerLevel);
        logger = getLogger(getClass());
    }

    @Before
    public void init() {
        initMocks(this);
        this.mockMvc = webAppContextSetup(this.webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }

}