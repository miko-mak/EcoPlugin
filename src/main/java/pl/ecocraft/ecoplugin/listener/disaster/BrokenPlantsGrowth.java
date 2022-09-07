package pl.ecocraft.ecoplugin.listener.disaster;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.world.StructureGrowEvent;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class BrokenPlantsGrowth implements Listener {

    private final ServerPollution pollution;

    public BrokenPlantsGrowth(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {
        if(pollution.getTotalPoints() >= 65) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        if (pollution.getTotalPoints() >= 65) {
            event.setCancelled(true);
        }
    }
}