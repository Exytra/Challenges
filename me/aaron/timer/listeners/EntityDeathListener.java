package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.projects.AllMobs;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDeathListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON) == SettingsItems.ItemState.ENABLED) {
            if (e.getEntity() instanceof EnderDragon) {
                if (Timer.state == Timer.TimerState.RUNNING) {
                    Timer.sendTimer(Timer.getCurrentTime());
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage("§7§m                                                 ");
                        pl.sendMessage(Main.getPrefix("Enderdragon", "Der §6§lEnderdrache §7ist gestorben."));
                        pl.sendMessage(Main.getPrefix("Enderdragon", "§aDie Challange ist hiermit absolviert."));
                        pl.sendMessage(Main.getPrefix("Enderdragon", "Zeit benötigt: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l")));
                        pl.sendMessage("§7§m                                                 ");
                    }
                    Timer.pause(false);
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.WITHER) == SettingsItems.ItemState.ENABLED) {
            if (e.getEntity() instanceof Wither) {
                if (Timer.state == Timer.TimerState.RUNNING) {
                    Timer.sendTimer(Timer.getCurrentTime());
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage("§7§m                                                 ");
                        pl.sendMessage(Main.getPrefix("Wither", "Der §6§lWither §7ist gestorben."));
                        pl.sendMessage(Main.getPrefix("Wither", "§aDie Challange ist hiermit absolviert."));
                        pl.sendMessage(Main.getPrefix("Wither", "Zeit benötigt: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l")));
                        pl.sendMessage("§7§m                                                 ");
                    }
                    Timer.pause(false);
                }
            }
        }

        if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS) == SettingsItems.ItemState.ENABLED) {
            if (e.getEntity().getKiller() != null) {
                AllMobs.killed(e.getEntityType());
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.EVERYTHING_REVERSE) == SettingsItems.ItemState.ENABLED && SettingsModes.customSettingsBooleans.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(2)) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                final Location deathLocation = e.getEntity().getLocation();
                final EntityType entityType = e.getEntityType();
                final World world = e.getEntity().getWorld();
                Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> {
                    world.spawnEntity(deathLocation, entityType);
                }, Utils.TimeToTicks(0, 0, SettingsModes.customSettingsInts.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(0)));
            }
        }

    }
}
