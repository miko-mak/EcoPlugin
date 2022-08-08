package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.entity.WaterMob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class AnimalKillListener implements Listener {

    private final ServerPollution pollution;

    public AnimalKillListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if(killer == null) {
            return;
        }

        if(event.getEntity() instanceof Animals || event.getEntity() instanceof WaterMob) {
            pollution.addPoints(ActionType.ANIMALS_KILL.getPoints());
        }
    }
}