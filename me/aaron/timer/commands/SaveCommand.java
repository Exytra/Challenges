package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class SaveCommand implements CommandExecutor {
    Config config = new Config();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("challenges.saveconfig")) {
            try {
                config.set("timer.currenttime", SettingsModes.currentTime);
                config.set("timer.reverse", SettingsModes.timer.get(SettingsItems.ItemType.REVERSE).name());
                config.set("timer.starttime", SettingsModes.startTime);
                config.set("timer.autostart", SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART).name());
                config.set("settings.onelife", SettingsModes.settings.get(SettingsItems.ItemType.ONELIFE).name());
                config.set("settings.geteilteherzen", SettingsModes.settings.get(SettingsItems.ItemType.GEITEILTEHERZEN).name());
                config.set("settings.respawn", SettingsModes.settings.get(SettingsItems.ItemType.RESPAWN).name());
                config.set("settings.dmgalert", SettingsModes.settings.get(SettingsItems.ItemType.DMGALERT).name());
                config.set("settings.timer", SettingsModes.settings.get(SettingsItems.ItemType.TIMER).name());
                config.set("settings.backpack", SettingsModes.settings.get(SettingsItems.ItemType.BACKPACK).name());
                config.set("settings.scoreboard", SettingsModes.settings.get(SettingsItems.ItemType.SCOREBOARD).name());
                config.set("settings.maxhp", SettingsModes.maxHP);
                config.set("settings.sendtitle", SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE).name());
                config.set("settings.showcoordsondeath", SettingsModes.settings.get(SettingsItems.ItemType.SHOWCOORDSONDEAETH).name());
                config.set("settings.resetconfirm", SettingsModes.settings.get(SettingsItems.ItemType.RESETCONFIRM).name());
                config.set("scoreboard.tabhp", SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP).name());
                config.set("gamerule.natrualregeneration", SettingsModes.gamerule.get(SettingsItems.ItemType.NATURALREGENERATION).name());
                config.set("gamerule.otherregeneration", SettingsModes.gamerule.get(SettingsItems.ItemType.OTHERREGENERATION).name());
                config.set("gamerule.pvp", SettingsModes.gamerule.get(SettingsItems.ItemType.PVP).name());
                config.set("gamerule.keepinventory", SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY).name());
                config.set("challenge.wither", SettingsModes.challenge.get(SettingsItems.ItemType.WITHER).name());
                config.set("challenge.enderdragon", SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON).name());
                //config.set("positions.list", pos.positions);

                //challenges
                config.set("challenge.flyondamage", SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE).name());
                config.set("challenge.speed", SettingsModes.challenge.get(SettingsItems.ItemType.SPEED).name());
                config.set("challenge.dirt", SettingsModes.challenge.get(SettingsItems.ItemType.DIRT).name());
                config.set("challenge.tenhearts", SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS).name());
                sender.sendMessage(Main.getPrefix("Plugin", "Die Config wurde erfolgreich gespeichert"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage(Main.getPrefix("Save Config", "Du hast hierfür §ckeine Berechtigung"));
        }
        return false;
    }
}
