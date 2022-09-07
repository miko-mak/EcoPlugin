package pl.ecocraft.ecoplugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.ecocraft.ecoplugin.command.CurrentDisastersCommand;
import pl.ecocraft.ecoplugin.command.PollutionCheckCommand;
import pl.ecocraft.ecoplugin.command.PutMaskCommand;
import pl.ecocraft.ecoplugin.listener.action.*;
import pl.ecocraft.ecoplugin.listener.disaster.MobDisappearance;
import pl.ecocraft.ecoplugin.listener.disaster.BrokenPlantsGrowth;
import pl.ecocraft.ecoplugin.listener.disaster.PollutedAir;
import pl.ecocraft.ecoplugin.listener.player.PlayerJoinListener;
import pl.ecocraft.ecoplugin.listener.player.PlayerRespawnListener;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.task.PollutionTask;
import pl.ecocraft.ecoplugin.util.SoundUtil;


public final class EcoPlugin extends JavaPlugin {

    private ServerPollution pollution;
    private SoundUtil sound;

    @Override
    public void onEnable() {
        pollution = new ServerPollution();
        sound = new SoundUtil(this);
        saveDefaultConfig();
        registerEvents();
        registerCommands();
        pollution.pointsCollection = this.getConfig().getDouble("Pollution-points");
        PollutionTask task = new PollutionTask(this, pollution, sound);
        task.runTaskTimer(this, 0, 600);
    }

    @Override
    public void onDisable() {
        double points = pollution.getTotalPoints();
        this.reloadConfig();
        this.getConfig().set("Pollution-points", points);
        this.saveConfig();
    }

    private void registerCommands() {
        this.getCommand("disasters").setExecutor(new CurrentDisastersCommand(this, pollution, sound));
        this.getCommand("pollution").setExecutor(new PollutionCheckCommand(this, pollution, sound));
        this.getCommand("put-mask").setExecutor(new PutMaskCommand(this, sound));
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BedEnterListener(pollution), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(pollution), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(pollution), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(pollution), this);
        getServer().getPluginManager().registerEvents(new FurnaceBurnListener(pollution), this);
        getServer().getPluginManager().registerEvents(new HungerListener(pollution), this);
        getServer().getPluginManager().registerEvents(new ItemPickUpListener(pollution), this);
        getServer().getPluginManager().registerEvents(new ItemThrowListener(pollution), this);
        getServer().getPluginManager().registerEvents(new MobKillListener(pollution), this);

        getServer().getPluginManager().registerEvents(new BrokenPlantsGrowth(pollution), this);
        getServer().getPluginManager().registerEvents(new MobDisappearance(pollution), this);
        getServer().getPluginManager().registerEvents(new PollutedAir(pollution), this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, pollution, sound), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this, pollution, sound), this);
    }
}