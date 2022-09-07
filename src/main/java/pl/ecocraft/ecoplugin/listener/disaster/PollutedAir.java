package pl.ecocraft.ecoplugin.listener.disaster;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Sound.BLOCK_LAVA_EXTINGUISH;
import static org.bukkit.Sound.ENTITY_PLAYER_BREATH;


public class PollutedAir implements Listener {

    private final ServerPollution pollution;

    public PollutedAir(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (pollution.getTotalPoints() >= 75) {
            Player player = event.getPlayer();

            if(player.getGameMode() == GameMode.SPECTATOR || player.getGameMode() == GameMode.CREATIVE) {
                return;
            }

            if(player.getHealth() < 1.1) {
                return;
            }

            PotionEffect effect = player.getPotionEffect(PotionEffectType.POISON);
            if(effect != null){
                return;
            }

            if(player.getInventory().getHelmet() != null) {
                if(player.getInventory().getHelmet().getType().toString().contains("GLASS")) {
                    return;
                }
            }

            if(player.getInventory().getHelmet() == null) {
                int random = ThreadLocalRandom.current().nextInt(0, 11);
                if (random == 4) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 1));
                    player.playSound(player.getLocation(), BLOCK_LAVA_EXTINGUISH, 3, 1.7F);
                    player.playSound(player.getLocation(), ENTITY_PLAYER_BREATH, 10, 0.9F);
                }
            }
        }
    }
}