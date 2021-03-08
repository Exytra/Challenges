package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;
        if (args.length >= 1) {
            if (p.hasPermission("challenges.timer")) {
                if (args[0].equalsIgnoreCase("pause")) {
                    if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                        if (Timer.state == Timer.TimerState.RUNNING) {
                            Timer.pause(true);
                        } else if (Timer.state == Timer.TimerState.PAUSED) {
                            p.sendMessage("§8[§6Timer§8] §7Der Timer ist bereits §cpausiert.");
                        }
                    } else {
                        p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                    }
                } else if (args[0].equalsIgnoreCase("resume")) {
                    if (SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS) == SettingsItems.ItemState.ENABLED) {
                        p.setHealth(1);
                        p.setFoodLevel(18);
                    }
                    if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                        if (Timer.state == Timer.TimerState.PAUSED) {
                            Timer.resume(true);
                        } else if (Timer.state == Timer.TimerState.RUNNING) {
                            p.sendMessage("§8[§6Timer§8] §7Der Timer §cläuft bereits.");
                        }
                    } else {
                        p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                        Timer.reset();
                        p.sendMessage("§8[§6Timer§8] §7Der Timer wurde §czurückgesetzt");
                    } else {
                        p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                    }
                } else if (args[0].equalsIgnoreCase("reverse")) {
                    if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                        if (args.length == 1) {
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
                } else if (args.length == 3) {
                    if (SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
                        try {
                            int hours = Integer.parseInt(args[0]);
                            int minutes = Integer.parseInt(args[1]);
                            int seconds = Integer.parseInt(args[2]);

                            int time = (seconds + (minutes * 60) + (hours * 3600));

                            if (!(hours < 0 && minutes < 0 && seconds < 0)) {
                                Timer.firststart = false;
                                Timer.setCurrentTime(time);
                                Timer.setStartTime(time);
                            } else {
                                p.sendMessage(Main.getPrefix("Timer", "Der Timer muss im positiven Bereich laufen."));
                            }

                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.sendMessage("§8[§6Timer§8] §7Der Timer wurde von §9§l" + p.getName() + "§7 auf " + Timer.ConvertTimerTime(time, "§6§l") + " §7gesetzt");
                            }
                        } catch (Exception e) {
                            p.sendMessage(Main.getPrefix("Timer", "Benutze §9/timer §8<§7Stunden§8> <§7Minuten§8> <§7Sekunden§8>"));
                        }
                    } else {
                        p.sendMessage(Main.getPrefix("Timer", "Der Timer ist deaktiviert, du kannst ihn in §9/settings §7aktivieren."));
                    }
                } else {
                    p.sendMessage(Main.getPrefix("Timer-Command", "Help:"));
                    p.sendMessage("§9/timer §8<§7resume§8>§7: Setzt den Timer fort.");
                    p.sendMessage("§9/timer §8<§7pause§8>§7: Pausiert den Timer.");
                    p.sendMessage("§9/timer §8<§7reverse§8>§7: Lässt den Timer rückwärts oder vorwärts laufen.");
                    p.sendMessage("§9/timer §8<§7reset§8>§7: Setzt den Timer zurück.");
                    p.sendMessage("§9/timer §8<§7Stunden§8> <§7Minuten§8> <§7Sekunden§8>§7: Setzt den Timer auf die angegebene Zeit.");
                }
            } else {
                p.sendMessage(Main.getPrefix("Timer", "Du hast hierfür §ckeine Berechtigung"));
            }
        } else {
            p.sendMessage(Main.getPrefix("Timer-Command", "Help:"));
            p.sendMessage("§9/timer §8<§7resume§8>§7: Setzt den Timer fort.");
            p.sendMessage("§9/timer §8<§7pause§8>§7: Pausiert den Timer.");
            p.sendMessage("§9/timer §8<§7reverse§8>§7: Lässt den Timer rückwärts oder vorwärts laufen.");
            p.sendMessage("§9/timer §8<§7reset§8>§7: Setzt den Timer zurück.");
            p.sendMessage("§9/timer §8<§7Stunden§8> <§7Minuten§8> <§7Sekunden§8>§7: Setzt den Timer auf die angegebene Zeit.");
        }

        return false;
    }
}
