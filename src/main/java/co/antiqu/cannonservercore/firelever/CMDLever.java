package co.antiqu.cannonservercore.firelever;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.firelever.v1_8.Lever;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.Permission;
import co.antiqu.cannonservercore.util.PlotManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class CMDLever implements CommandExecutor, Listener {

    private CannonServerCore instance;

    private HashMap<UUID, Block> leverList;

    public CMDLever(CannonServerCore instance) {
        this.instance = instance;
        leverList = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        if(!player.hasPermission(Permission.FIRE_LEVER.getPerm())) {
            player.sendMessage(Lang.NO_PERMISSION);
            return false;
        }
        if(PlotManager.getPlot(player.getLocation()) == null)
            return false;
        if(!leverList.containsKey(player.getUniqueId())) {
            player.sendMessage(Lang.NO_LAST_CLICK);
            return false;
        }
        Block block = leverList.get(player.getUniqueId());
        if(block.getType() != Material.LEVER)
            return false;

        Lever.handle(block, false, Power.LEVER);

        player.sendMessage(Lang.LEVER);
        return false;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent evt) {
        if(!evt.getPlayer().hasPermission(Permission.FIRE_LEVER.getPerm()))
            return;
        if(evt.getClickedBlock() == null)
            return;
        if(evt.getClickedBlock().getType() == Material.LEVER)
            leverList.put(evt.getPlayer().getUniqueId(),evt.getClickedBlock());
    }

}
