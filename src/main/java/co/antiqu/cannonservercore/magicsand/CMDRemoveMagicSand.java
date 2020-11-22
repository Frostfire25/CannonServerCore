package co.antiqu.cannonservercore.magicsand;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Cuboid;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.Permission;
import co.antiqu.cannonservercore.util.PlotManager;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CMDRemoveMagicSand implements CommandExecutor {

    private CannonServerCore instance;

    public CMDRemoveMagicSand(CannonServerCore instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player))
            return false;
        if(!sender.hasPermission(Permission.MAGIC_SAND.getPerm())) {
            sender.sendMessage(Lang.NO_PERMISSION);
            return false;
        }
        Player player = (Player) sender;
        Plot plot = PlotManager.getPlot(player.getLocation());
        if(plot == null || !plot.getOwners().contains(player.getUniqueId())) {
            player.sendMessage(Lang.NO_PERM_RMS);
            return false;
        }
        AtomicInteger magic_sand_removed = new AtomicInteger();

        Location one = plot.getAllCorners().get(0);
        Location two = plot.getAllCorners().get(2);
        World world = Bukkit.getWorld(one.getWorld());

        Set<org.bukkit.Location> removes = new HashSet<>();

        Cuboid cuboid = new Cuboid(new org.bukkit.Location(world,one.getX(),one.getY(),one.getZ()),new org.bukkit.Location(world,two.getX(),two.getY()+255,two.getZ()));
        instance.getMagicSand().getLocations().forEach(n -> {
            if(cuboid.isIn(n)) {
                world.getBlockAt(n).setType(Material.AIR);
                removes.add(n);
                if(instance.getMagicSand().getPlotLimit().containsKey(plot)) {
                    instance.getMagicSand().getPlotLimit().put(plot,instance.getMagicSand().getPlotLimit().get(plot)-1);
                }
                magic_sand_removed.getAndIncrement();
            }
        });
        if(!removes.isEmpty()) {
            removes.forEach(n -> instance.getMagicSand().getLocations().remove(n));
        }
        player.sendMessage(Lang.RMS.replaceAll("%amount%", ""+magic_sand_removed.toString()));
        return false;
    }
}
