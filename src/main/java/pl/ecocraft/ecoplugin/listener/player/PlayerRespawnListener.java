package pl.ecocraft.ecoplugin.listener.player;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;


public class PlayerRespawnListener implements Listener {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;

    public PlayerRespawnListener(EcoPlugin plugin, ServerPollution pollution) {
        this.plugin = plugin;
        this.pollution = pollution;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if(pollution.getTotalPoints() >= 100) {
            player.sendTitle(" ", " ", 0, 3, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("cannot_play_anymore")));
        }
    }
}