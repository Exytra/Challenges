package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("challenges.timer")) {
            if (args[0].equalsIgnoreCase("pause")) {
                if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                    if (Timer.state == Timer.TimerState.RUNNING) {
                        Timer.pause(true);
                    } else if (Timer.state == Timer.TimerState.PAUSED) {
                        p.sendMessage("§8[§6Timer§8] §c Der Timer ist bereits pausiert.");
                    }
                } else {
                    p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                }
            }

            if (args[0].equalsIgnoreCase("resume")) {
                if (SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS) == SettingsItems.ItemState.ENABLED) {
                    p.setHealth(1);
                    p.setFoodLevel(18);
                }
                if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                    if (Timer.state == Timer.TimerState.PAUSED) {
                        Timer.resume(true);
                    } else if (Timer.state == Timer.TimerState.RUNNING) {
                        p.sendMessage("§8[§6Timer§8] §cDer Timer läuft bereits.");
                    }
                } else {
                    p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                }
            }

            if (args[0].equalsIgnoreCase("reset")) {
                if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                    Timer.pause(false);
                    Timer.reset();
                    p.sendMessage("§8[§6Timer§8] §fDer Timer wurde §czurückgesetzt");
                } else {
                    p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                }
            }

            if (args[0].equalsIgnoreCase("reverse")) {
                if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                    if (args.length == 1) {
                        Timer.pause(true);
                        if (SettingsModes.timer.get(SettingsItems.ItemType.REVERSE) == SettingsItems.ItemState.DISABLED) {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.ENABLED);
                            p.sendMessage("§8[§6Timer§8] §7Der Timer läuft nun §6rückwärts.");
                        } else {
                            SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.DISABLED);
                            p.sendMessage("§8[§6Timer§8] §7Der Timer läuft nun §6vorwärts.");
                        }
                    }
                } else {
                    p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                }
            }

            if (args.length == 3) {
                if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                    try {
                        int hours = Integer.parseInt(args[0]);
                        int minutes = Integer.parseInt(args[1]);
                        int seconds = Integer.parseInt(args[2]);

                        int time = (seconds + (minutes * 60) + (hours * 3600));

                        Timer.pause(false);
                        Timer.setCurrentTime(time);
                        Timer.setStartTime(time);

                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage("§8[§6Timer§8] §7Der Timer wurde von §9§l" + p.getName() + "§7 auf " + Timer.ConvertTimerTime(time, "§6§l") + " §7gesetzt");
                        }
                    } catch (Exception e) {
                        p.sendMessage("§8[§cError§8] §f/timer §5<hours> <minutes> <seconds>");
                    }
                } else {
                    p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                }
            }
        } else {
            p.sendMessage(Main.getPrefix("Timer", "Du hast hierfür §ckeine Berechtigung"));
        }

        return false;
    }
}
