package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class ResetCommand implements CommandExecutor {
    public static int resetSchedular;
    private int waittime = 0;
    Config config = new Config();
    public static boolean attemptonce = false;
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
        if (p.hasPermission("challenges.reset")) {
            if (SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM) == SettingsItems.ItemState.ENABLED) {
                if (args.length == 0) {
                    if (!attemptonce) {
                        p.sendMessage(Main.getPrefix("Reset", "Nutze §9/reset confirm §7um den Reset auszuführen."));
                        attemptonce = true;
                        waittime = 0;
                        resetSchedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), () -> {
                            if (waittime <= 10) {
                                waittime++;
                            } else {
                                Bukkit.getScheduler().cancelTask(resetSchedular);
                                attemptonce = false;
                            }
                        }, 0, 20);
                    } else {
                        p.sendMessage(Main.getPrefix("Reset", "Du kannst bereits §9/reset confirm §7eingeben."));
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("confirm")) {
                        if (attemptonce) {
                            Main.getInstance().saveConfig();
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.kickPlayer("§8[§6Reset§8] \n §7Die Welten werden nun §9zurückgesetzt. \n §7Durch §9§l" + p.getName() + "§7 veranlasst. \n Du kannst den Server in ca. §9§l1 Minute §7wieder betreten.");
                                pl.sendMessage("§8§m                    §8[§6Reset§8]§m                    §7\n " + " \n §7Die Welten werden nun §9zurückgesetzt. \n §7Durch §9§l" + p.getName() + "§7 veranlasst. \n Du kannst den Server in ca. §9§l1 Minute §7wieder betreten. \n " + " \n§8§m                                                 ");
                                try {
                                    config.set("reset.isReset", true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Bukkit.getServer().dispatchCommand(p, "restart");
                            }
                        } else {
                            p.sendMessage(Main.getPrefix("Reset", "Deine Zeit für den Reset ist §cabgelaufen"));
                        }
                    } else {
                        p.sendMessage(Main.getPrefix("Reset", "Es gibt nur §9/reset confirm"));
                    }
                }
            } else {
                if (args.length == 0) {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        if (SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD)  == SettingsItems.ItemState.ENABLED) {
                            pl.sendMessage("§8§m                    §8[§6Reset§8]§m                    §7\n " + " \n §7Die Welten werden nun §9zurückgesetzt. \n §7Durch §9§l" + p.getName() + "§7 veranlasst. \n Du kannst den Server in ca. §9§l1 Minute §7wieder betreten. \n " + " \n§8§m                                                 ");
                        } else {
                            pl.kickPlayer("§8[§6Reset§8] \n §7Die Welten werden nun §9zurückgesetzt. \n §7Durch §9§l" + p.getName() + "§7 veranlasst. \n Du kannst den Server in ca. §9§l1 Minute §7wieder betreten.");
                        }
                        try {
                            config.set("reset.isReset", true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bukkit.getServer().dispatchCommand(p, "restart");
                    }
                }
            }
        } else {
            p.sendMessage(Main.getPrefix("Reset", "Du hast hierfür §ckeine Berechtigung"));
        }
        return false;
    }
}
