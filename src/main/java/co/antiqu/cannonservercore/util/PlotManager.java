package co.antiqu.cannonservercore.util;

import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlotManager {

    /**
     * @param loc
     * @return
     */

    public static Plot getPlot(org.bukkit.Location loc) {
        return new Location(loc.getWorld().getName()
                ,loc.getBlockX()
                ,loc.getBlockY()
                ,loc.getBlockZ()).getPlot();
    }

    /**
     * Checks to see if a player is bypassing a plots limit
     * @param plot
     * @param permission
     * @return
     */

    public static boolean playerBypassing(Plot plot, Permission permission) {
        List<UUID> uuids = new ArrayList<UUID>();
        uuids.addAll(plot.getOwners());
        uuids.addAll(plot.getTrusted());
        for(UUID n : uuids) {
            Player player = Bukkit.getPlayer(n);
            if(player == null)
                continue;
            if (player.isOnline()) {
                if(player.hasPermission(permission.getPerm()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Messages All Plot Players a message
     * @param plot
     * @param message
     */

    public static void messagePlotPlayers(Plot plot, String message) {
        List<UUID> uuids = new ArrayList<UUID>();
        uuids.addAll(plot.getOwners());
        uuids.addAll(plot.getTrusted());
        for(UUID n : uuids) {
            Player player = Bukkit.getPlayer(n);
            if(player == null)
                continue;
            if (player.isOnline()) {
                player.sendMessage(message);
            }
        }
    }

}

