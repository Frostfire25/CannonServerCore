package co.antiqu.cannonservercore.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {

    /**
     * Translates color codes in minecraft.
     * @param s
     * @return ret
     *
     */

    public static String t (String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    public static List<String> t(List<String> s) {
        ArrayList<String> ret = new ArrayList<>();
        s.stream().forEach(n -> {
            ret.add(t(n));
        });
        return ret;
    }

}
