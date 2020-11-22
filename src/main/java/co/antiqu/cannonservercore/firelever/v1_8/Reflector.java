package co.antiqu.cannonservercore.firelever.v1_8;

import org.bukkit.Bukkit;

public abstract class Reflector {

    public static Class<?> getClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (Exception e) {
            return null;
        }
    }

}
