package co.antiqu.cannonservercore.firelever;

import co.antiqu.cannonservercore.CannonServerCore;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.material.Button;
import org.bukkit.scheduler.BukkitRunnable;

public class RunnableFire extends BukkitRunnable {

    private CannonServerCore instance;

    private Button button;
    private Block block;

    public RunnableFire(CannonServerCore instance, Button button, Block block) {
        this.instance = instance;
        this.block = block;
        this.button = button;
    }

    @Override
    public void run() {
        /*
        if(button.isPowered()) {
            Bukkit.getServer().getScheduler().runTaskLater(instance, new Runnable() {
                public void run() {
                    button.setPowered(false);
                    block.getState().setData(button);
                    block.getState().update();
                }
            }, 2L);
            button.setPowered(false);
        }
        //block.getState().update();*/
    }
}
