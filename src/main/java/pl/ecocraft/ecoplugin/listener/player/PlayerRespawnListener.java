package pl.ecocraft.ecoplugin.listener.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;
import pl.ecocraft.ecoplugin.util.SoundUtil;


public class PlayerRespawnListener implements Listener {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;
    private final SoundUtil sound;

    public PlayerRespawnListener(EcoPlugin plugin, ServerPollution pollution, SoundUtil sound) {
        this.plugin = plugin;
        this.pollution = pollution;
        this.sound = sound;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if(pollution.getTotalPoints() >= 100) {
            player.sendTitle(" ", " ", 0, 3, 0);
            player.setGameMode(GameMode.SPECTATOR);
            sound.playSound(player, "warning");
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("cannot_play_anymore")));
        }
    }
}