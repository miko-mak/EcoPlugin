package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.*;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class MobKillListener implements Listener {

    private final ServerPollution pollution;

    public MobKillListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (entity instanceof Animals || entity instanceof WaterMob || entity instanceof Villager || entity instanceof WanderingTrader) {
            DamageCause cause = entity.getLastDamageCause().getCause();

            if (cause == DamageCause.FIRE ||
                cause == DamageCause.HOT_FLOOR ||
                cause == DamageCause.DROWNING ||
                cause == DamageCause.LAVA ||
                cause == DamageCause.FALL ||
                cause == DamageCause.BLOCK_EXPLOSION ||
                cause == DamageCause.FALLING_BLOCK) {

                pollution.addPoints(ActionType.MOB_KILL.getPoints());
                return;
            }

            if (killer == null) {
                return;
            }

            pollution.addPoints(ActionType.MOB_KILL.getPoints());
        }
    }
}
