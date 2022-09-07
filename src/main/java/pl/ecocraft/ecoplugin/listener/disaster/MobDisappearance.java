package pl.ecocraft.ecoplugin.listener.disaster;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class MobDisappearance implements Listener {

    private final ServerPollution pollution;

    public MobDisappearance(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent event) {
        if (pollution.getTotalPoints() >= 75) {
            event.setCancelled(true);

            for (Entity mob : event.getEntity().getWorld().getEntities()) {
                if(mob instanceof Animals || mob instanceof WaterMob || mob instanceof Villager || mob instanceof WanderingTrader) {
                    mob.remove();
                }
            }
        }
    }
}