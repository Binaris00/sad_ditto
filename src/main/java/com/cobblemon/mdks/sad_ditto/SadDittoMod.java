package com.cobblemon.mdks.sad_ditto;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SadDittoMod implements DedicatedServerModInitializer {
    public static final String MOD_ID = "sad_ditto";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MinecraftServer server;

    /**
     * Runs the mod initializer on the server environment.
     */
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            SadDittoMod.server = server;
        });
    }
}
