package com.cobblemon.mdks.cobblemon_raids.util;

import com.cobblemon.mdks.cobblemon_raids.CobblemonFabricTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Utility methods for common operations
 */
public class Utils {
    public static Gson newGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public static void broadcast(String message) {
        if (CobblemonFabricTemplate.server == null) return;
        
        Component component = Component.literal(message);
        List<ServerPlayer> players = new ArrayList<>(CobblemonFabricTemplate.server.getPlayerList().getPlayers());
        
        for (ServerPlayer player : players) {
            player.sendSystemMessage(component);
        }
    }

    public static File checkForDirectory(String path) {
        File dir = new File(new File("").getAbsolutePath() + path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static String readFileSync(String directory, String filename) {
        try {
            // Ensure directory exists
            File dir = checkForDirectory("/" + directory);
            Path path = Paths.get(dir.getAbsolutePath(), filename);
            File file = path.toFile();

            if (!file.exists()) {
                return "";
            }

            return Files.readString(path);
        } catch (Exception e) {
            CobblemonFabricTemplate.LOGGER.error("Failed to read file: " + filename, e);
            return "";
        }
    }

    public static void writeFileSync(String directory, String filename, String data) {
        try {
            // Ensure directory exists
            File dir = checkForDirectory("/" + directory);
            Path path = Paths.get(dir.getAbsolutePath(), filename);

            // Write file
            try (FileWriter writer = new FileWriter(path.toFile())) {
                writer.write(data);
            }
            CobblemonFabricTemplate.LOGGER.info("Wrote file: " + path.toAbsolutePath());
        } catch (Exception e) {
            CobblemonFabricTemplate.LOGGER.error("Failed to write file: " + filename, e);
        }
    }
}
