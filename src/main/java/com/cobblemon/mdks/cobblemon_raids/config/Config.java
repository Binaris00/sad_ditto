package com.cobblemon.mdks.cobblemon_raids.config;

import com.cobblemon.mdks.cobblemon_raids.CobblemonFabricTemplate;
import com.cobblemon.mdks.cobblemon_raids.util.Constants;
import com.cobblemon.mdks.cobblemon_raids.util.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Config {
    private static Config instance;

    private double xp;

    private Config() {
        setDefaults();
        load();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void setDefaults() {
        generateDefaultConfig();
    }

    private void generateDefaultConfig() {
        this.xp = 1.0;
    }

    public void load() {
        // Ensure config directory exists
        Utils.checkForDirectory("/" + Constants.CONFIG_PATH);

        String content = Utils.readFileSync(Constants.CONFIG_PATH, Constants.CONFIG_FILE);
        if (content == null || content.isEmpty()) {
            CobblemonFabricTemplate.LOGGER.warn("Config file is empty or not found, generating defaults.");
            generateDefaultConfig();
            save();
            return;
        }

        try {
            JsonObject json = JsonParser.parseString(content).getAsJsonObject();
            loadFromJson(json);
            CobblemonFabricTemplate.LOGGER.info("Config reloaded successfully.");
        } catch (Exception e) {
            CobblemonFabricTemplate.LOGGER.error("Failed to load config, resetting to defaults.", e);
            setDefaults();
            save();
        }
    }

    private void loadFromJson(JsonObject json) {
        // Load existing properties
        xp = getOrDefault(json, "xp", 1.0);
    }

    private <T> T getOrDefault(JsonObject json, String key, T defaultValue) {
        if (!json.has(key)) return defaultValue;

        JsonElement element = json.get(key);
        if (defaultValue instanceof String) {
            return (T) element.getAsString();
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(element.getAsInt());
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(element.getAsLong());
        } else if (defaultValue instanceof Boolean) {
            return (T) Boolean.valueOf(element.getAsBoolean());
        } else if (defaultValue instanceof Double) {
            return (T) Double.valueOf(element.getAsDouble());
        } else if (defaultValue instanceof Float) {
            return (T) Float.valueOf(element.getAsFloat());
        }
        return defaultValue;
    }

    public void save() {
        Utils.checkForDirectory("/" + Constants.CONFIG_PATH);

        JsonObject json = new JsonObject();
        json.addProperty("xp", xp);

        Utils.writeFileSync(Constants.CONFIG_PATH, Constants.CONFIG_FILE,
                Utils.newGson().toJson(json));
        CobblemonFabricTemplate.LOGGER.info("Config saved successfully.");
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
        save();
    }

    public void hotReload() {
        load();
        CobblemonFabricTemplate.LOGGER.info("Configuration hot-reloaded.");
    }
}