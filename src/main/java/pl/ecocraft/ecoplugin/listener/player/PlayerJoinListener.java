package pl.ecocraft.ecoplugin.listener.player;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;


public class PlayerJoinListener implements Listener {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;

    public PlayerJoinListener(EcoPlugin plugin, ServerPollution pollution) {
        this.plugin = plugin;
        this.pollution = pollution;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        double points = pollution.getTotalPoints();
        if(points >= 100){
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.LEVITATION);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("cannot_play_anymore")));
            return;
        }

        if(player.getGameMode() == GameMode.SPECTATOR) {
            player.getInventory().clear();
            player.sendTitle(" ", " ", 0, 3, 0);
            if(points < 100) {
                player.setGameMode(GameMode.SURVIVAL);
            }
        }
    }
}