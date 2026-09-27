package com.example.hotbarzoom;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotbarZoomMod implements ModInitializer {
    public static final String MOD_ID = "hotbarzoom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hotbar Zoom loaded");
    }
}
