package me.aaron.timer.listeners;

import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("§c« §f" + p.getName());
        if (Bukkit.getOnlinePlayers().size() == 1) {
            if (Timer.state == Timer.TimerState.RUNNING && SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                Timer.pause(false);
                Bukkit.getLogger().info("§cDer Timer wurde pausiert, da sich niemand mehr auf dem Server befindet.");
            }
        }
    }
}
