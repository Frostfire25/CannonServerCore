package co.antiqu.cannonservercore.util;

import co.antiqu.cannonservercore.CannonServerCore;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Lang {

    private CannonServerCore instance;

    private static FileConfiguration config = CannonServerCore.getInstance().getConfig();

    public static String NO_PERMISSION;
    public static String PLACED_SAND;
    public static String BROKE_SAND;
    public static String GIVEN_SAND;
    public static String LIMIT_REACHED_SAND;
    public static String SAND_REMOVED;
    public static String TNT_FILLED_MESSAGE;
    public static String NO_LAST_CLICK;
    public static String FIRE;
    public static String LEVER;
    public static String PLOT_NEEDED;
    public static String TNT_UNFILLED;
    public static String POWERED;
    public static String RMS;
    public static String NO_PERM_RMS;

    public static List<String> UPLOAD;

    public static int TNTRADIOUS;

    // Un-comment BC integration

    public Lang(CannonServerCore instance) {
        this.instance = instance;
        NO_PERMISSION = Color.t(config.getString("no_permission"));
        PLACED_SAND = Color.t(config.getString("given_magic_sand"));
        BROKE_SAND = Color.t(config.getString("broke_magic_sand"));
        GIVEN_SAND = Color.t(config.getString("given_magic_sand"));
        LIMIT_REACHED_SAND = Color.t(config.getString("limit_reached"));
        SAND_REMOVED = Color.t(config.getString("sand_removed"));
        TNT_FILLED_MESSAGE = Color.t(config.getString("tntfilled"));
        NO_LAST_CLICK = Color.t(config.getString("no_last_click"));
        FIRE = Color.t(config.getString("fire"));
        LEVER = Color.t(config.getString("lever"));
        PLOT_NEEDED = Color.t(config.getString("plot_needed"));
        POWERED = Color.t(config.getString("powered"));
        TNT_UNFILLED = Color.t(config.getString("tntunfilled"));
        UPLOAD = Color.t(config.getStringList("upload"));
        RMS = Color.t(config.getString("removed_all_magic_sand"));
        NO_PERM_RMS = Color.t(config.getString("only_remove_on_your_plot"));

        TNTRADIOUS = config.getInt("tntfill_radius");
    }

}
