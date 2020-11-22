package co.antiqu.cannonservercore.util.configws;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private FileConfiguration config;

    public Config(Plugin plugin, String fileName) {
        boolean fileBoolean = new File(plugin.getDataFolder().getParent(), plugin.getDataFolder().getName()).mkdirs();
        file = new File(plugin.getDataFolder(), fileName);
        try {
            config = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig(Plugin plugin) {
        if (!file.exists()) {
            plugin.saveResource(file.getName(), false);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

}
