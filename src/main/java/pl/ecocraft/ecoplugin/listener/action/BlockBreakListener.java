package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class BlockBreakListener implements Listener {

    private final ServerPollution pollution;

    public BlockBreakListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getBlock().getType().toString().contains("SAPLING")) {
            if(pollution.getTotalPoints() < 65){
                pollution.addPoints(ActionType.SAPLINGS.getPoints());
                return;
            }
        }

        if(event.getBlock().getType() == Material.BEEHIVE || event.getBlock().getType() == Material.BEE_NEST) {
            pollution.addPoints(ActionType.BEEHIVE.getPoints());
            return;
        }

        if(event.getBlock().getType() == Material.DAYLIGHT_DETECTOR) {
            pollution.addPoints(ActionType.SOLAR_PANELS.getPoints());
        }
    }
}