package pl.ecocraft.ecoplugin.task;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import pl.ecocraft.ecoplugin.EcoPlugin;
import pl.ecocraft.ecoplugin.pollution.ServerPollution;
import pl.ecocraft.ecoplugin.util.ColorUtil;

import static org.bukkit.Sound.ENTITY_ENDER_DRAGON_DEATH;


public class PollutionTask extends BukkitRunnable {

    private final EcoPlugin plugin;
    private final ServerPollution pollution;

    public PollutionTask(EcoPlugin plugin, ServerPollution pollution) {
        this.plugin = plugin;
        this.pollution = pollution;
    }

    private void finalDisaster(){
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            if(player.getGameMode() == GameMode.SPECTATOR || player.isDead()) {
                return;
            }

            World world = player.getWorld();
            world.strikeLightning(player.getLocation());
            player.setHealth(0);
        });
    }

    private void sendTitle(Player player, String title, String subtitle, int fadein, int duration, int fadeout) {
        if(player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        player.sendTitle(ColorUtil.translateColorCodes(title), ColorUtil.translateColorCodes(subtitle), fadein, duration, fadeout);
    }

    @Override
    public void run() {
        double totalPoints = pollution.getTotalPoints();
        plugin.reloadConfig();
        plugin.getConfig().set("Pollution-points", totalPoints);
        plugin.saveConfig();

        if(totalPoints >= 100) {
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                if(player.getGameMode() == GameMode.SPECTATOR || player.isDead()) {
                    return;
                }

                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 465, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 465, 1));
                player.playSound(player.getLocation(), ENTITY_ENDER_DRAGON_DEATH, 1, 0.5F);
                player.sendTitle(ColorUtil.translateColorCodes(plugin.getConfig().getString("first_title")), ColorUtil.translateColorCodes(plugin.getConfig().getString("first_subtitle")), 12, 150, 0);

            });
            Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getOnlinePlayers().forEach(player -> sendTitle(player, plugin.getConfig().getString("second_title"), plugin.getConfig().getString("second_subtitle"), 0, 150, 0)), 20 * 7);
            Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getOnlinePlayers().forEach(player -> sendTitle(player, plugin.getConfig().getString("third_title"), plugin.getConfig().getString("third_subtitle"), 0, 80, 12)), 20 * 12);
            Bukkit.getScheduler().runTaskLater(plugin, () -> finalDisaster(), 20 * 17);
        }
    }
}