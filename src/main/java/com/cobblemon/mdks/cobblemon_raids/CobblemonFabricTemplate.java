package com.cobblemon.mdks.cobblemon_raids;

import com.cobblemon.mdks.cobblemon_raids.command.CRaidConfigCommand;
import com.cobblemon.mdks.cobblemon_raids.config.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CobblemonFabricTemplate implements ModInitializer {
    public static final String MOD_ID = "cobblemon_fabric_template";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static MinecraftServer server;

    @Override
    public void onInitialize() {
        Config.getInstance().load();

        // Register commands with Fabric
        net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {
                CRaidConfigCommand.register(dispatcher);
            }
        );

        // Register event listeners
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            CobblemonFabricTemplate.server = server;
        });

        CobblemonEventHandler.init();
    }
}
