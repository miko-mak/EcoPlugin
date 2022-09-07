package pl.ecocraft.ecoplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;
import pl.ecocraft.ecoplugin.util.SoundUtil;


public class PollutionCheckCommand implements CommandExecutor {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;
    private final SoundUtil sound;

    public PollutionCheckCommand(EcoPlugin plugin, ServerPollution pollution, SoundUtil sound) {
        this.plugin = plugin;
        this.pollution = pollution;
        this.sound = sound;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        double result = pollution.getTotalPoints();

        sound.playSound(player, "pollution_check");
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("living_conditions").replace("{CONDITIONS}", getConditionsMessage(result))));
        player.sendMessage(ColorUtil.translateColorCodes(plugin.getConfig().getString("pollution_size").replace("{PERCENTAGE}", String.format("%.2f", result) + "%")));
        return true;
    }

    private String getConditionsMessage(double points) {
        String result = "";

        if(points <= 9){
            result = plugin.getConfig().getString("conditions_excellent");
        }

        else if(points >= 10 && points < 30) {
            result = plugin.getConfig().getString("conditions_good");
        }

        else if(points >= 30 && points < 50) {
            result = plugin.getConfig().getString("conditions_okay");
        }

        else if(points >= 50 && points < 80) {
            result = plugin.getConfig().getString("conditions_bad");
        }

        else if(points >= 80 && points < 100) {
            result = plugin.getConfig().getString("conditions_terrible");
        }

        else if(points >= 100) {
            result = plugin.getConfig().getString("conditions_horrible");
        }

        return result;
    }
}