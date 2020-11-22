package co.antiqu.cannonservercore.util.configws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Class uses Java WatchService to poll Plugin directory on file updates.
 * If path is updated,
 * WSClass updateConfig() method is called.
 *
 * user should reinstate the config inside this method.
 */

public class WSM {

    @Getter
    private static WSM instance;

    @Getter
    public WSClass mainClass;

    @Getter
    public Plugin plugin;

    private Path path;

    private WatchService watchService;

    private WatchKey watchKey;

    private boolean enabled;

    private boolean reloadAllOnEdit;

    //private HashSet<FileConfiguration> configs;

    /**
     * Create a object using a WSClass as the mainClass
     * Preferable JavaPlugin main class
     * @param mainClass Class implementing WSClass
     * @param plugin Java Plugin for directories for file reloading
     * @param reloadAllOnEdit determines if all Files should be reloaded on edit, or solly edited file.
     */

    public WSM(WSClass mainClass, Plugin plugin/*, HashSet<FileConfiguration> configs*/, boolean reloadAllOnEdit) {
        instance = this;
        this.mainClass = mainClass;
        this.plugin = plugin;
        this.reloadAllOnEdit = reloadAllOnEdit;
        this.enabled = true;
        //this.configs = configs;
        WSMThread wsmThread = new WSMThread(mainClass,plugin,watchService,watchKey,path,enabled);
    }

    private void init() {

    }

    public void disableLoop() {
        this.enabled = false;
    }

}
@AllArgsConstructor
class WSMThread implements Runnable {

    @Getter
    private static WSM instance;

    @Getter
    public WSClass mainClass;

    @Getter
    public Plugin plugin;

    private WatchService watchService;

    private WatchKey watchKey;

    private Path path;

    private boolean enabled;

    @Override
    public void run() {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            boolean exists = new File(plugin.getDataFolder().getParent(),plugin.getDataFolder().getName()).mkdirs();
            path = plugin.getDataFolder().toPath();
            watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            boolean ran = false;
            long lastRan = System.currentTimeMillis();
            System.out.println(path.toUri());
            for( ; ; ) {
                if(!enabled)
                    return;
                if(!((lastRan+1000) < System.currentTimeMillis()))
                    continue;
                lastRan = System.currentTimeMillis();
                for(WatchEvent<?> n : watchKey.pollEvents()) {
                    Path innerFilePath = ((Path) n.context());
                    if(!ran) {
                        System.out.println(innerFilePath.toUri());
                        File file = innerFilePath.toFile();
                        mainClass.updateConfig();
                        ran = true;
                    } else {
                        ran = false;
                    }
                    /*
                    if(innerPath.getParent().toString().equalsIgnoreCase(path.toString())) {

                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
