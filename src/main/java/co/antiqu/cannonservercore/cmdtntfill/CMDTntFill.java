package co.antiqu.cannonservercore.cmdtntfill;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Cuboid;
import co.antiqu.cannonservercore.util.ItemBuilder;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.Permission;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import java.util.Iterator;

public class CMDTntFill implements CommandExecutor {

    private CannonServerCore instance;

    public CMDTntFill(CannonServerCore instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        if(!player.hasPermission(Permission.TNT_FILL.getPerm())) {
            player.sendMessage(Lang.NO_PERMISSION);
            return false;
        }
        Cuboid cuboid = new Cuboid(new Location(player.getWorld(),player.getLocation().getBlockX()-Lang.TNTRADIOUS,player.getLocation().getBlockY()-Lang.TNTRADIOUS, player.getLocation().getBlockZ()-Lang.TNTRADIOUS),
                new Location(player.getWorld(),player.getLocation().getBlockX()+Lang.TNTRADIOUS,player.getLocation().getBlockY()+Lang.TNTRADIOUS, player.getLocation().getBlockZ()+Lang.TNTRADIOUS));
        int filled = 0;
        Iterator<Block> blocks = cuboid.blockList();
        while(blocks.hasNext()) {
            Block block = blocks.next();
            if(block.getType() == Material.DISPENSER) {
                Dispenser dispenser = (Dispenser) block.getState();
                for(int i = 0; i < 9; i++) {
                    dispenser.getInventory().setItem(i, new ItemBuilder(Material.TNT,64).toItemStack());
                }
                filled++;
            }
        }
        player.sendMessage(Lang.TNT_FILLED_MESSAGE.replaceAll("%amount%", ""+filled));
        return false;
    }
}

// - - - - -