package co.antiqu.cannonservercore.util;

import lombok.Getter;

public enum Permission {

    NONE(""),
    TNT_FILL("csc.tntfill"),
    FIRE_LEVER("CSC.firelever"),
    SAND_REMOVED("csc.sandremoved"),
    MAGIC_SAND("csc.magicsand");

    @Getter
    private String perm;

    Permission(String s) {
        this.perm = s;
    }

}
