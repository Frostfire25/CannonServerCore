package co.antiqu.cannonservercore;

import co.antiqu.cannonservercore.Upload.CMDUpload;
import co.antiqu.cannonservercore.bonemeal.BoneMeal;
import co.antiqu.cannonservercore.cmdtntfill.CMDTntFill;
import co.antiqu.cannonservercore.cmdtntfill.CMDTntUnfill;
import co.antiqu.cannonservercore.firelever.CMDFire;
import co.antiqu.cannonservercore.firelever.CMDLever;
import co.antiqu.cannonservercore.magicsand.CMDMagicSand;
import co.antiqu.cannonservercore.magicsand.CMDRemoveMagicSand;
import co.antiqu.cannonservercore.magicsand.MagicSand;
import co.antiqu.cannonservercore.repeaterticks.TickCounter;
import co.antiqu.cannonservercore.tntfix.RedstoneMonsterFix;
import co.antiqu.cannonservercore.tntfix.TNTFix;
import co.antiqu.cannonservercore.util.Lang;
import co.antiqu.cannonservercore.util.configws.WSClass;
import co.antiqu.cannonservercore.util.configws.WSM;
import lombok.Getter;
import me.codedred.playtimes.PlayTimes;
import me.codedred.playtimes.api.TimelessPlayer;
import me.codedred.playtimes.api.TimelessServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CannonServerCore extends JavaPlugin implements WSClass {

    @Getter
    private static CannonServerCore instance;

    @Getter
    private Lang lang;

    @Getter
    private MagicSand magicSand;

    @Getter
    private BoneMeal boneMeal;

    @Getter
    private CMDFire cmdFire;

    @Getter
    private TickCounter tickCounter;

    @Getter
    private CMDLever cmdLever;

    @Getter
    private TNTFix tntFix;

    @Getter
    private RedstoneMonsterFix redstoneMonsterFix;

    @Getter
    private WSM wsmManager;

    @Override
    public void onEnable() {
        instance = this;
        setup();
    }

    private void setup() {
        this.wsmManager = new WSM(this,this,true);
        config();
        objects();
        commands();
        listeners();
    }

    @Override
    public void onDisable() {
        magicSand.removeSand();
        wsmManager.disableLoop();
    }

    private void listeners() {
        this.magicSand = new MagicSand(instance);
        Bukkit.getPluginManager().registerEvents(magicSand,instance);
        this.boneMeal = new BoneMeal(instance);
        Bukkit.getPluginManager().registerEvents(boneMeal,instance);
        Bukkit.getPluginManager().registerEvents(cmdFire,instance);
        Bukkit.getPluginManager().registerEvents(cmdLever,instance);
        this.tickCounter = new TickCounter(instance);
        Bukkit.getPluginManager().registerEvents(tickCounter,instance);
        this.tntFix = new TNTFix(instance);
        Bukkit.getPluginManager().registerEvents(tntFix,instance);
        this.redstoneMonsterFix = new RedstoneMonsterFix(instance);
        Bukkit.getPluginManager().registerEvents(redstoneMonsterFix,instance);
    }

    private void commands() {
        getCommand("magicsand").setExecutor(new CMDMagicSand(instance));
        getCommand("tntfill").setExecutor(new CMDTntFill(instance));
        getCommand("tntunfill").setExecutor(new CMDTntUnfill(instance));
        getCommand("fire").setExecutor(cmdFire);
        getCommand("lever").setExecutor(cmdLever);
        getCommand("upload").setExecutor(new CMDUpload());
        getCommand("removemagicsand").setExecutor(new CMDRemoveMagicSand(instance));
    }

    private void objects() {
        this.lang = new Lang(instance);
        cmdFire = new CMDFire(instance);
        cmdLever = new CMDLever(instance);
    }

    public void config() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void updateConfig() {
        setup();
    }
}
