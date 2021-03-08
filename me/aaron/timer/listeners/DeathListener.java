package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.MLG;
import me.aaron.timer.projects.AllDeathMessages;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

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

        if (SettingsModes.projects.get(SettingsItems.ItemType.ALL_DEATHS) == SettingsItems.ItemState.ENABLED) {
            if (DeathCause.contains(AllDeathMessages.currentDeathMessage.replace("<Player>", p.getName()).replace("[Mob]", ""))) {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(Main.getPrefix("Alle Todesnachrichten", "Du hast die Todesnachricht \"§9" + AllDeathMessages.currentDeathMessage.replace("<Player>", pl.getName()) + "§7\" erfolgreich geschafft!"));
                }
                Random random = new Random();
                AllDeathMessages.deathMessages.remove(AllDeathMessages.currentDeathMessage);
                if (!AllDeathMessages.deathMessages.isEmpty()) {
                    AllDeathMessages.currentDeathMessage = AllDeathMessages.deathMessages.get(random.nextInt(AllDeathMessages.deathMessages.size()));
                } else {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(Main.getPrefix("Alle Todesnachrichte", "Du hast §9alle Todesnachrichten §7bekommen!"));
                        pl.sendTitle("§9Alle Todesnachriten", "§7bekommen!", 10, 50, 10);
                        pl.playSound(pl.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 30, 1);
                        AllDeathMessages.bossBars.get(pl).setTitle("Alle Todesnachriten bekommen!");
                    }
                }
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.WATER_MLG) == SettingsItems.ItemState.ENABLED) {
            if (e.getEntity().getLocation().getWorld().getName().equalsIgnoreCase("MLG-World")) {
                MLG.deadPlayers.add(e.getEntity().getPlayer());
            }
        }
    }
}
