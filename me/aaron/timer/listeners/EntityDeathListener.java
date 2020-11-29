package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.ForceMob;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

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

        /*if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB) == SettingsItems.ItemState.ENABLED) {
            ForceMob forceMob = new ForceMob(Main.getInstance());
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                Bukkit.broadcastMessage(e.getEntity().getType() + "");
                Bukkit.broadcastMessage(forceMob.forcedMob + " forced after kill");
                if (e.getEntity().getType().toString().equals(forceMob.forcedMob.toString())) {
                    forceMob.forcedMob = null;
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage("LOLOL");
                    }
                }
            }
        }*/
    }
}
