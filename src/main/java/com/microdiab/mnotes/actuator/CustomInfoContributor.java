package com.microdiab.mnotes.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    private final Environment environment;

    public CustomInfoContributor(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> appInfo = new HashMap<>();
        appInfo.put("version", environment.getProperty("info.app.version", "mnotes - Version non définie"));
        appInfo.put("description", environment.getProperty("info.app.description", "mnotes - Description non définie"));
        appInfo.put("documentation", environment.getProperty("info.app.documentation", "mnotes - Documentation non définie"));
        appInfo.put("information", environment.getProperty("info.app.information", "mnotes - Informations non définies"));
        // Ajout d'une info dynamique (ex: horodatage)
        appInfo.put("lastUpdated", LocalDateTime.now().toString());

        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("app", appInfo);

        builder.withDetails(infoMap);
    }
}
