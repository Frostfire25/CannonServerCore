package co.antiqu.cannonservercore.magicsand;

import co.antiqu.cannonservercore.CannonServerCore;
import co.antiqu.cannonservercore.util.Color;
import co.antiqu.cannonservercore.util.ItemBuilder;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.Permission;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CMDMagicSand implements CommandExecutor {

    private CannonServerCore instance;

    public CMDMagicSand(CannonServerCore instance) {
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
        player.getInventory().addItem(instance.getMagicSand().getItem());
        player.sendMessage(Lang.GIVEN_SAND);

        return false;
    }
}
