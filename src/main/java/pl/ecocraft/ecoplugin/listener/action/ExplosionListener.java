package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class ExplosionListener implements Listener {

    private final ServerPollution pollution;

    public ExplosionListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        pollution.addPoints(ActionType.EXPLOSIONS.getPoints());
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        pollution.addPoints(ActionType.EXPLOSIONS.getPoints());
    }
}