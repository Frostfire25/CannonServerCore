package co.antiqu.cannonservercore.firelever.v1_8;

import co.antiqu.cannonservercore.firelever.Power;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;

public class Lever {

    public static void handle(Block block, boolean on, Power powerType) {
        try {
            Object world2 = ((CraftWorld) block.getWorld()).getClass().getMethod("getHandle");
            World world = ((CraftWorld) block.getWorld()).getHandle();
            BlockPosition blockposition = new BlockPosition(block.getLocation().getX(), block.getLocation().getY(), block.getLocation().getZ());

            IBlockData iblockdata = world.getType(blockposition);
            net.minecraft.server.v1_8_R3.Block mBlock = iblockdata.getBlock();

            if (powerType == Power.BUTTON) {
                int old = !on ? 15 : 0;
                int current = on ? 15 : 0;

                BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
                world.getServer().getPluginManager().callEvent(eventRedstone);
                BlockButtonAbstract blockButtonAbstract = (BlockButtonAbstract) mBlock;
                blockButtonAbstract.interact(world, blockposition, iblockdata, null, null, 0f, 0f, 0f);
            } else {
                BlockLever blockLever = (BlockLever) mBlock;
                blockLever.interact(world, blockposition, iblockdata, null, null, 0f, 0f, 0f);
            }
        } catch (Exception ex) {

        }
        }
}
