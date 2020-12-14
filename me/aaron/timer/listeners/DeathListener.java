package me.aaron.timer.listeners;

import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        String DeathCause = e.getDeathMessage();
        if (SettingsModes.settings.get(SettingsItems.ItemType.RESPAWN) == SettingsItems.ItemState.DISABLED) {
            if (SettingsModes.settings.get(SettingsItems.ItemType.ONELIFE) == SettingsItems.ItemState.ENABLED) {
                Timer.pause(false);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.setGameMode(GameMode.SPECTATOR);
                    pl.sendMessage("§7§m                                                 ");
                    pl.sendMessage("§8[§6Tod§8] §7Der Spieler §6§l" + p.getName() + "§7 ist gestorben.");
                    pl.sendMessage("§8[§6Tod§8] §cDie Challenge ist hiermit beendet.");
                    pl.sendMessage("§8[§6Tod§8] §7Zeit verschwendet: " + Timer.ConvertTimerTime(Timer.getCurrentTime(), "§6§l"));
                    pl.sendMessage("§7§m                                                 ");
                }
            } else {
                p.setGameMode(GameMode.SPECTATOR);
            }

        }
        if (SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH) == SettingsItems.ItemState.ENABLED) {
            String world = p.getWorld().getName();
            switch (world) {
                case "world":
                    world = "Overworld";
                    break;
                case "world_nether":
                    world = "Nether";
                    break;
                case "world_the_end":
                    world = "End";
                    break;
            }
            e.setDeathMessage(DeathCause + "§8 [§6" + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ() + ", " + world + "§8]");
        }
    }
}
