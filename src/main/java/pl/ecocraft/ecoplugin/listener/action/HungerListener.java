package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import java.util.Random;


public class HungerListener implements Listener {

    private final ServerPollution pollution;

    public HungerListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(pollution.getTotalPoints() >= 50) {
            if(event.getEntityType() != EntityType.PLAYER) {
                return;
            }

            Player player = (Player) event.getEntity();
            PotionEffect effect = player.getPotionEffect(PotionEffectType.POISON);
            if(effect != null) {
                return;
            }

            if(event.getItem() != null) {
                if(event.getItem().getType().isEdible()) {
                    return;
                }
            }

            if(player.getFoodLevel() > 3) {
                Random random = new Random();
                int num = random.nextInt(4);
                if(num != 1) {
                    return;
                }

                event.setCancelled(true);
                event.getEntity().setFoodLevel(event.getFoodLevel() - 1);
            }
        }
    }
}