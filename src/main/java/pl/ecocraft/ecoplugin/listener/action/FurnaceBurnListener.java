package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import pl.ecocraft.ecoplugin.listener.ActionType;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;


public class FurnaceBurnListener implements Listener {

    private final ServerPollution pollution;

    public FurnaceBurnListener(ServerPollution pollution) {
        this.pollution = pollution;
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        Block above = event.getBlock().getRelative(BlockFace.UP);
        if(above.getType() == Material.HOPPER) {
            return;
        }

        pollution.addPoints(ActionType.FURNACE_BURN.getPoints());
    }
}