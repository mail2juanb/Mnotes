package com.microdiab.mnotes.actuator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.info.Info;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomInfoContributorTest {

    @Mock
    private Environment environment;

    @InjectMocks
    private CustomInfoContributor customInfoContributor;

    @Test
    void contribute_ShouldAddAppInfoToBuilder() {
        // Arrange
        when(environment.getProperty("info.app.version", "mnotes - Version non définie")).thenReturn("1.0.0");
        when(environment.getProperty("info.app.description", "mnotes - Description non définie")).thenReturn("Gestion des patients");
        when(environment.getProperty("info.app.documentation", "mnotes - Documentation non définie")).thenReturn("https://docs.microdiab.com/mnotes");
        when(environment.getProperty("info.app.information", "mnotes - Informations non définies")).thenReturn("Microservice de gestion des patients");

        Info.Builder builder = new Info.Builder();

        // Act
        customInfoContributor.contribute(builder);

        // Assert
        Info info = builder.build();
        Map<String, Object> details = info.getDetails();

        assertNotNull(details);
        assertTrue(details.containsKey("app"));

        @SuppressWarnings("unchecked")
        Map<String, Object> appInfo = (Map<String, Object>) details.get("app");

        assertEquals("1.0.0", appInfo.get("version"));
        assertEquals("Gestion des patients", appInfo.get("description"));
        assertEquals("https://docs.microdiab.com/mnotes", appInfo.get("documentation"));
        assertEquals("Microservice de gestion des patients", appInfo.get("information"));
        assertNotNull(appInfo.get("lastUpdated"));
    }

    @Test
    void contribute_ShouldUseDefaultValuesIfPropertiesAreMissing() {
        // Arrange
        when(environment.getProperty("info.app.version", "mnotes - Version non définie")).thenReturn("mnotes - Version non définie");
        when(environment.getProperty("info.app.description", "mnotes - Description non définie")).thenReturn("mnotes - Description non définie");
        when(environment.getProperty("info.app.documentation", "mnotes - Documentation non définie")).thenReturn("mnotes - Documentation non définie");
        when(environment.getProperty("info.app.information", "mnotes - Informations non définies")).thenReturn("mnotes - Informations non définies");

        Info.Builder builder = new Info.Builder();

        // Act
        customInfoContributor.contribute(builder);

        // Assert
        Info info = builder.build();
        Map<String, Object> details = info.getDetails();

        assertNotNull(details);
        assertTrue(details.containsKey("app"));

        @SuppressWarnings("unchecked")
        Map<String, Object> appInfo = (Map<String, Object>) details.get("app");

        assertEquals("mnotes - Version non définie", appInfo.get("version"));
        assertEquals("mnotes - Description non définie", appInfo.get("description"));
        assertEquals("mnotes - Documentation non définie", appInfo.get("documentation"));
        assertEquals("mnotes - Informations non définies", appInfo.get("information"));
        assertNotNull(appInfo.get("lastUpdated"));
    }
}