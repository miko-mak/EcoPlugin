package pl.ecocraft.ecoplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;
import pl.ecocraft.ecoplugin.util.SoundUtil;


public class CurrentDisastersCommand implements CommandExecutor {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;
    private final SoundUtil sound;

    public CurrentDisastersCommand(EcoPlugin plugin, ServerPollution pollution, SoundUtil sound) {
        this.plugin = plugin;
        this.pollution = pollution;
        this.sound = sound;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        double points = pollution.getTotalPoints();

        if(points < 50) {
            player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("no_disasters")));
            return true;
        }

        sound.playSound(player, "disasters_list");
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("disasters_list").replace("{DISASTERS}", getCurrentDisasters(points))));
        return true;
    }

    private String getCurrentDisasters(double points) {
        String disasters = "";

        if(points >= 50 && points < 65) {
            disasters = plugin.getConfig().getString("more_hunger");
        }

        else if(points >= 65 && points < 75) {
            disasters = plugin.getConfig().getString("more_hunger") + " " + plugin.getConfig().getString("broken_growth");
        }

        else if(points >= 75) {
            disasters = plugin.getConfig().getString("more_hunger") + " " + plugin.getConfig().getString("broken_growth") + " " + plugin.getConfig().getString("no_animals") + " " + plugin.getConfig().getString("polluted_air");
        }

        return disasters;
    }
}