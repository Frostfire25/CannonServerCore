package co.antiqu.cannonservercore.tntfix;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Cuboid;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class TNTFix implements Listener {

    private CannonServerCore instance;

    public TNTFix(CannonServerCore instance) {
        this.instance = instance;
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
            case DIODE: {
                if(evt.getType() == Material.DIODE)
                    e.setCancelled(true);
            }
        }
        switch(evt.getWorld().getBlockAt(new Location(evt.getWorld(),evt.getLocation().getBlockX(),evt.getLocation().getBlockY()-1,evt.getLocation().getBlockZ()))
                .getType()) {
            case REDSTONE: {
                if(evt.getType() == Material.REDSTONE)
                    e.setCancelled(true);
            }
            case DIODE: {
                if(evt.getType() == Material.DIODE)
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent evt) {
        
    }
    /*
    @EventHandler
    public void onIgnite2(ExplosionPrimeEvent e) {
        System.out.println("Entity Spawned " +e.getEntity().getName());
        AtomicBoolean completed = new AtomicBoolean(false);
        if(e.getEntity().getType() == EntityType.PRIMED_TNT) {
            System.out.println("primed tnt");
            Entity evt = e.getEntity();
            new Cuboid(new Location(evt.getLocation().getWorld(),evt.getLocation().getBlockX()+1,evt.getLocation().getBlockY()+1,evt.getLocation().getBlockZ()+1),
                    new Location(evt.getLocation().getWorld(),evt.getLocation().getBlockX()-1,evt.getLocation().getBlockY()-1,evt.getLocation().getBlockZ()-1))
                .blockList().forEachRemaining(n -> {
                    if(n.getType() == Material.DISPENSER) {
                        System.out.println("found dispenser");
                        completed.set(true);
                    }
            });
            if(!completed.get()) {
                e.setCancelled(true);
                System.out.println("removed tnt");
            }
        }
    }*/
}
