package co.antiqu.cannonservercore.bonemeal;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.Permission;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class BoneMeal implements Listener {

    private CannonServerCore instance;

    public BoneMeal(CannonServerCore instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent evt) {
        if(evt.getAction() != Action.RIGHT_CLICK_BLOCK && evt.getAction() != Action.LEFT_CLICK_BLOCK)
            return;
        ItemStack item = evt.getPlayer().getItemInHand();
        if(item == null)
            return;
        if(item.getType() == Material.BONE) {
            if(!evt.getPlayer().hasPermission(Permission.SAND_REMOVED.getPerm())) {
                evt.getPlayer().sendMessage(Lang.NO_PERMISSION);
                return;
            }
            int removed = 0;
            for(int i = 0; i < 255; i++) {
                if(new Location(evt.getClickedBlock().getLocation().getWorld(), evt.getClickedBlock().getLocation().getBlockX(),i,evt.getClickedBlock().getLocation().getBlockZ()).getBlock().getType() == Material.SAND) {
                    removed++;
                    new Location(evt.getClickedBlock().getLocation().getWorld(), evt.getClickedBlock().getLocation().getBlockX(),i,evt.getClickedBlock().getLocation().getBlockZ()).getBlock().setType(Material.WATER);
                }
            }
            evt.getPlayer().sendMessage(Lang.SAND_REMOVED.replaceAll("%amount%", ""+removed));
        } else if(item.getType() == Material.INK_SACK && item.getDurability() == (short) 15) {
            if(!evt.getPlayer().hasPermission(Permission.SAND_REMOVED.getPerm())) {
                evt.getPlayer().sendMessage(Lang.NO_PERMISSION);
                return;
            }
            int removed = 0;
            for(int i = 0; i < 255; i++) {
                if(new Location(evt.getClickedBlock().getLocation().getWorld(), evt.getClickedBlock().getLocation().getBlockX(),i,evt.getClickedBlock().getLocation().getBlockZ()).getBlock().getType() == Material.SAND) {
                    removed++;
                    new Location(evt.getClickedBlock().getLocation().getWorld(), evt.getClickedBlock().getLocation().getBlockX(),i,evt.getClickedBlock().getLocation().getBlockZ()).getBlock().setType(Material.AIR);
                }
            }
            evt.getPlayer().sendMessage(Lang.SAND_REMOVED.replaceAll("%amount%", ""+removed));
        }
    }

}
