package pl.ecocraft.ecoplugin.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.ecocraft.ecoplugin.EcoPlugin;

public class SoundUtil {

    private final EcoPlugin plugin;

    public SoundUtil(EcoPlugin plugin) {
        this.plugin = plugin;
    }

    public void playSound(Player player, String path){
        String name = plugin.getConfig().getString(path + "_sound");
        if(name == null || name.isEmpty()) {
            return;
        }

        Sound sound = Sound.valueOf(name);
        float pitch = Float.parseFloat(plugin.getConfig().getString(path + "_pitch"));
        float volume = Float.parseFloat(plugin.getConfig().getString(path + "_volume"));
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
