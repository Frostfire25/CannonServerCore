package co.antiqu.cannonservercore.tntfix;

import co.antiqu.cannonservercore.CannonServerCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class RedstoneMonsterFix implements Listener {

    private CannonServerCore instance;

    public RedstoneMonsterFix(CannonServerCore instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent evt) {
        if(evt.getBlockPlaced().getType() == Material.COMMAND || evt.getBlockPlaced().getType() == Material.COMMAND_MINECART)
            evt.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent evt) {
        if(evt.getEntity().getType() == EntityType.DROPPED_ITEM)
            evt.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block evt = e.getBlock();
        switch(evt.getWorld().getBlockAt(new Location(evt.getWorld(),evt.getLocation().getBlockX(),evt.getLocation().getBlockY()+1,evt.getLocation().getBlockZ()))
                .getType()) {
            case REDSTONE: {
                if(evt.getType() == Material.REDSTONE)
                    e.setCancelled(true);
            }
            case REDSTONE_WIRE: {
                if(evt.getType() == Material.REDSTONE_WIRE)
                    e.setCancelled(true);
            }
            case DIODE: {
                if(evt.getType() == Material.DIODE)
                    e.setCancelled(true);
            }
            case TORCH: {
                if(evt.getType() == Material.TORCH)
                    e.setCancelled(true);
            }
            case REDSTONE_TORCH_OFF: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                e.setCancelled(true);
            }
            case REDSTONE_TORCH_ON: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                    e.setCancelled(true);
            }
            case STRING: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                    e.setCancelled(true);
            }
        }
        switch(evt.getWorld().getBlockAt(new Location(evt.getWorld(),evt.getLocation().getBlockX(),evt.getLocation().getBlockY()-1,evt.getLocation().getBlockZ()))
                .getType()) {
            case REDSTONE: {
                if(evt.getType() == Material.REDSTONE)
                    e.setCancelled(true);
            }
            case REDSTONE_WIRE: {
                if(evt.getType() == Material.REDSTONE_WIRE)
                    e.setCancelled(true);
            }
            case DIODE: {
                if(evt.getType() == Material.DIODE)
                    e.setCancelled(true);
            }
            case TORCH: {
                if(evt.getType() == Material.TORCH)
                    e.setCancelled(true);
            }
            case REDSTONE_TORCH_OFF: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                    e.setCancelled(true);
            }
            case REDSTONE_TORCH_ON: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                    e.setCancelled(true);
            }
            case STRING: {
                if(evt.getType() == Material.REDSTONE_TORCH_OFF)
                    e.setCancelled(true);
            }
        }
    }

}
