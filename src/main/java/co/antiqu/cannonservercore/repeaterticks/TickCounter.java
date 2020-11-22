package co.antiqu.cannonservercore.repeaterticks;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Color;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Diode;

import java.util.HashMap;

public class TickCounter implements Listener {

    private HashMap<ItemStack,Integer> lightningwands;

    private CannonServerCore instance;

    public TickCounter(CannonServerCore instance) {
        this.instance = instance;
        this.lightningwands = new HashMap<>();
    }

    @EventHandler
    public void onBlockRightClick(PlayerInteractEvent evt) {
        Player player = evt.getPlayer();
        if(player.getItemInHand() == null) {
            return;
        }
        ItemStack item = player.getItemInHand();
        if(item.getType() != Material.BLAZE_ROD) {
            return;
        }
        if(evt.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Block block = evt.getClickedBlock();
        if(block.getType() != Material.DIODE_BLOCK_OFF) {
            return;
        }
        int delay = 0;
        Diode diode = (Diode) block.getState().getData();
        if(!lightningwands.containsKey(item)) {
            delay = diode.getDelay();
            lightningwands.put(item, delay);
        } else {
            delay = lightningwands.get(item) + diode.getDelay();
            lightningwands.put(item, delay);
        }
        player.sendMessage(Color.t("&7[&bTotal Ticks&7] &bRT: &a"+delay+" &bGT: &a"+(delay*2)));
        evt.setCancelled(true);
    }

    @EventHandler
    public void onBlockLeftClick(PlayerInteractEvent evt) {
        Player player = evt.getPlayer();
        if(player.getItemInHand() == null) {
            return;
        }
        ItemStack item = player.getItemInHand();
        if(item.getType() != Material.BLAZE_ROD) {
            return;
        }
        if(evt.getAction() != Action.LEFT_CLICK_AIR) {
            return;
        }
        lightningwands.remove(item);
        player.sendMessage(Color.t("&cTicks removed"));
    }

}
