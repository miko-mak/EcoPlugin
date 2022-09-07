package pl.ecocraft.ecoplugin.listener.action;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
        Entity entity = event.getEntity();

        if(event.getEntityType() == EntityType.ENDER_CRYSTAL) {
            World.Environment environment = entity.getWorld().getEnvironment();
            Block below = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);

            if(environment == World.Environment.THE_END && below.getType() == Material.BEDROCK) {
                return;
            }
        }

        pollution.addPoints(ActionType.EXPLOSIONS.getPoints());
    }
}