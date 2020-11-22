package co.antiqu.cannonservercore.magicsand;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.*;
import com.intellectualcrafters.plot.object.Plot;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MagicSand implements Listener {

    @Getter
    private ItemStack item;

    private CannonServerCore instance;

    @Getter
    private HashSet<Location> locations;

    @Getter
    private HashMap<Plot,Integer> plotLimit;

    private final int LIMIT = CannonServerCore.getInstance().getConfig().getInt("magicsand.plot_limit");

    private String header;
    private List<String> lore;
    private Material material;

    public MagicSand(CannonServerCore instance) {
        this.instance = instance;
        init();
    }

    private void init() {
        locations = new HashSet<>();
        plotLimit = new HashMap<>();
        material = Material.getMaterial(instance.getConfig().getString("magicsand.block"));

        header = Color.t(instance.getConfig().getString("magicsand.header"));
        lore = Color.t(instance.getConfig().getStringList("magicsand.lore"));

        this.item = new ItemBuilder(material)
                .setName(header)
                .setLore(lore)
                .addGlow()
                .toItemStack();
        timer();
    }

    private void timer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(locations.isEmpty())
                    return;
                locations.forEach(n -> {
                    Location tempLoc = new Location(n.getWorld(),n.getBlockX(),n.getBlockY()-1,n.getBlockZ());
                    if(tempLoc.getBlock().getType() != Material.AIR)
                        return;
                    if(tempLoc.getBlock().getType() != Material.SAND) {
                        n.getWorld().spawnFallingBlock(tempLoc,Material.SAND, (byte) 0);
                    }
                });
            }
        }.runTaskTimer(instance,20,3);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent evt) {
        if(evt.getItemInHand().getType() != material)
            return;
        if(evt.getItemInHand() == null
                || !evt.getItemInHand().hasItemMeta()
                || !evt.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(header))
            return;
        if(!evt.getPlayer().hasPermission(Permission.MAGIC_SAND.getPerm())) {
            evt.getPlayer().sendMessage(Lang.NO_PERMISSION);
            return;
        }
        Plot plot = PlotManager.getPlot(evt.getBlock().getLocation());
        if(plotLimit.containsKey(plot)) {
            plotLimit.put(plot,plotLimit.get(plot)+1);
            if(plotLimit.get(plot) >= LIMIT) {
                evt.getPlayer().sendMessage(Lang.LIMIT_REACHED_SAND);
                return;
            }
            locations.add(evt.getBlock().getLocation());
        } else {
            plotLimit.put(plot,1);
            locations.add(evt.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent evt) {
        if(evt.getBlock().getType() != material)
            return;
        if(!locations.contains(evt.getBlock().getLocation()))
            return;
        if(!evt.getPlayer().hasPermission(Permission.MAGIC_SAND.getPerm())) {
            evt.getPlayer().sendMessage(Lang.NO_PERMISSION);
            return;
        }
        Plot plot = PlotManager.getPlot(evt.getBlock().getLocation());
        if(plot == null)
            return;
        if(!evt.isCancelled()) {
            locations.remove(evt.getBlock().getLocation());
            plotLimit.put(plot,plotLimit.get(plot)-1);
        }
    }

    public void removeSand() {
        assert !locations.isEmpty();
        locations.forEach(n -> n.getBlock().setType(Material.AIR));
    }

}
