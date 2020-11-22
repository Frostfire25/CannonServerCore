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
import org.bukkit.material.Button;

import java.util.HashMap;
import java.util.UUID;

public class CMDFire implements CommandExecutor, Listener {

    private CannonServerCore instance;

    private HashMap<UUID, Block> fireList;

    public CMDFire(CannonServerCore instance) {
        this.instance = instance;
        fireList = new HashMap<>();
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
        if(!fireList.containsKey(player.getUniqueId())) {
            player.sendMessage(Lang.NO_LAST_CLICK);
            return false;
        }
        Block block = fireList.get(player.getUniqueId());
        if(block.getType() != Material.WOOD_BUTTON && block.getType() != Material.STONE_BUTTON)
            return false;

        Button button = (Button) block.getState().getData();

        if(button.isPowered()) {
            player.sendMessage(Lang.POWERED);
            return false;
        } else {
            Lever.handle(block, false, Power.BUTTON);
        }

        new RunnableFire(instance,button,block).runTaskLater(instance,100);
        player.sendMessage(Lang.FIRE);
        return false;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent evt) {
        if(!evt.getPlayer().hasPermission(Permission.FIRE_LEVER.getPerm()))
            return;
        if(evt.getClickedBlock() == null)
            return;
        if(evt.getClickedBlock().getType() == Material.STONE_BUTTON || evt.getClickedBlock().getType() == Material.WOOD_BUTTON)
            fireList.put(evt.getPlayer().getUniqueId(),evt.getClickedBlock());
    }
    /*
    private void powerBlocks(Location l) {
        Iterator<Block> cuboid = new Cuboid(new Location(l.getWorld(),l.getBlockX()-1,l.getBlockY()-1,l.getBlockZ()-1),
                new Location(l.getWorld(),l.getBlockX()+1,l.getBlockY()+1,l.getBlockZ()+1)).blockList();
        while(cuboid.hasNext()) {
            Block block = cuboid.next();
            switch (block.getType()) {
                case REDSTONE: {
                    if (block.getState().getData() instanceof RedstoneWire) {
                        RedstoneWire redstoneWire = (RedstoneWire) block.getState().getData();
                        redstoneWire.setPower(15);
                        block.setBlockData(blockData);
                    }
                }  break;
            }
        }
    }*/
}
