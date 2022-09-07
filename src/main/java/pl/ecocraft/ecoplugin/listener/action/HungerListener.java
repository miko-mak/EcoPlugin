package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import java.util.concurrent.ThreadLocalRandom;


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

            ItemStack item = event.getItem();
            if(item != null && item.getType().isEdible()) {
                return;
            }

            if(player.getFoodLevel() < 3) {
                return;
            }

            int random = ThreadLocalRandom.current().nextInt(0, 4);

            if(random != 1) {
                return;
            }

            event.setCancelled(true);
            event.getEntity().setFoodLevel(event.getFoodLevel() - 1);
        }
    }
}