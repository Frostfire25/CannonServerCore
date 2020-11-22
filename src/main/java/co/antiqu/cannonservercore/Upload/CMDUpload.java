package co.antiqu.cannonservercore.Upload;

import co.antiqu.cannonservercore.util.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMDUpload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Lang.UPLOAD.forEach(commandSender::sendMessage);
        return false;
    }
}
