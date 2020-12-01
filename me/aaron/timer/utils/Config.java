package me.aaron.timer.utils;

import javafx.geometry.Pos;
import me.aaron.timer.pos.Position;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Config {

    private static File file;
    private static YamlConfiguration config;

    public Config() {
        File dir = new File("./plugins/Challenges");
        if(!dir.exists()) {
            dir.mkdir();
        }

        file = new File(dir, "config.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public static boolean contains(String path) {
        return config.contains(path);
    }
    public static void set(String path, Object value) throws IOException {
        config.set(path, value);
        config.save(file);
    }

    public Object get(String path) {
        if(!contains(path)) {
            return null;
        }
        return config.get(path);
    }
    public void delete(String path) throws IOException {
        config.set(path, null);
        config.save(file);
    }

    public static int getInt(String path) {
        return config.getInt(path);
    }

    public static String getString(String path) {
        return config.getString(path);
    }

    public static Double getDouble(String path) {
        return config.getDouble(path);
    }

    public static ArrayList getArrayList(String path) {
        return (ArrayList) config.getList(path);
    }

    public static Boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public File getFile() {
        return file;
    }

    public static boolean saveConfig() {
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
            config.set("settings.hardcore", SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE).name());
            config.set("settings.bungeecord", SettingsModes.settings.get(SettingsItems.ItemType.BUNGEECORD).name());
            config.set("scoreboard.tabhp", SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP).name());
            config.set("gamerule.natrualregeneration", SettingsModes.gamerule.get(SettingsItems.ItemType.NATURALREGENERATION).name());
            config.set("gamerule.otherregeneration", SettingsModes.gamerule.get(SettingsItems.ItemType.OTHERREGENERATION).name());
            config.set("gamerule.pvp", SettingsModes.gamerule.get(SettingsItems.ItemType.PVP).name());
            config.set("gamerule.keepinventory", SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY).name());
            config.set("challenge.wither", SettingsModes.challenge.get(SettingsItems.ItemType.WITHER).name());
            config.set("challenge.enderdragon", SettingsModes.challenge.get(SettingsItems.ItemType.ENDER_DRAGON).name());
            config.set("positions.list", Position.positionlist.toArray());

            //challenges
            config.set("challenge.flyondamage", SettingsModes.challenge.get(SettingsItems.ItemType.FLYONDAMAGE).name());
            config.set("challenge.speed", SettingsModes.challenge.get(SettingsItems.ItemType.SPEED).name());
            config.set("challenge.dirt", SettingsModes.challenge.get(SettingsItems.ItemType.DIRT).name());
            config.set("challenge.tenhearts", SettingsModes.challenge.get(SettingsItems.ItemType.TENHEARTS).name());
            config.set("challenge.trafficlight", SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT).name());
            config.set("challenge.oneblockoneheart", SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART).name());
            config.set("challenge.damagemirror", SettingsModes.challenge.get(SettingsItems.ItemType.DAMAGEMIRROR).name());
            config.set("challenge.forceblock", SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK).name());
            config.set("challenge.bedrockwall", SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL).name());
            config.set("challenge.thefloorislava", SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA).name());
            config.set("challenge.forcemob", SettingsModes.challenge.get(SettingsItems.ItemType.FORCEMOB).name());
            config.save(file);
            return true;
        } catch (Exception e) {
            resetConfig();
            Bukkit.broadcastMessage("LOLOLOLOLOL");
            return false;
        }
    }

    public static boolean loadConfig() {
        if (Config.file.exists()) {
            try {
                SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.valueOf(Config.getString("timer.reverse")));
                SettingsModes.startTime = Config.getInt("timer.starttime");
                SettingsModes.currentTime = Config.getInt("timer.currenttime");
                SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.valueOf(Config.getString("timer.autostart")));
                SettingsModes.settings.put(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.valueOf(Config.getString("settings.onelife")));
                SettingsModes.settings.put(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.valueOf(Config.getString("settings.geteilteherzen")));
                SettingsModes.settings.put(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.valueOf(Config.getString("settings.respawn")));
                SettingsModes.settings.put(SettingsItems.ItemType.DMGALERT, SettingsItems.ItemState.valueOf(Config.getString("settings.dmgalert")));
                SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.valueOf(Config.getString("settings.timer")));
                SettingsModes.settings.put(SettingsItems.ItemType.BACKPACK, SettingsItems.ItemState.valueOf(Config.getString("settings.backpack")));
                SettingsModes.settings.put(SettingsItems.ItemType.SCOREBOARD, SettingsItems.ItemState.valueOf(Config.getString("settings.scoreboard")));
                SettingsModes.settings.put(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsItems.ItemState.valueOf(Config.getString("settings.showcoordsondeath")));
                SettingsModes.maxHP = Config.getDouble("settings.maxhp");
                SettingsModes.settings.put(SettingsItems.ItemType.SENDTITLE, SettingsItems.ItemState.valueOf(Config.getString("settings.sendtitle")));
                SettingsModes.settings.put(SettingsItems.ItemType.RESETCONFIRM, SettingsItems.ItemState.valueOf(Config.getString("settings.resetconfirm")));
                SettingsModes.settings.put(SettingsItems.ItemType.HARDCORE, SettingsItems.ItemState.valueOf(Config.getString("settings.hardcore")));
                SettingsModes.settings.put(SettingsItems.ItemType.BUNGEECORD, SettingsItems.ItemState.valueOf(Config.getString("settings.bungeecord")));
                SettingsModes.scoreboard.put(SettingsItems.ItemType.TABHP, SettingsItems.ItemState.valueOf(Config.getString("scoreboard.tabhp")));
                SettingsModes.gamerule.put(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.valueOf(Config.getString("gamerule.natrualregeneration")));
                SettingsModes.gamerule.put(SettingsItems.ItemType.OTHERREGENERATION, SettingsItems.ItemState.valueOf(Config.getString("gamerule.otherregeneration")));
                SettingsModes.gamerule.put(SettingsItems.ItemType.PVP, SettingsItems.ItemState.valueOf(Config.getString("gamerule.pvp")));
                SettingsModes.gamerule.put(SettingsItems.ItemType.KEEP_INVENTORY, SettingsItems.ItemState.valueOf(Config.getString("gamerule.keepinventory")));
                SettingsModes.challenge.put(SettingsItems.ItemType.WITHER, SettingsItems.ItemState.valueOf(Config.getString("challenge.wither")));
                SettingsModes.challenge.put(SettingsItems.ItemType.ENDER_DRAGON, SettingsItems.ItemState.valueOf(Config.getString("challenge.enderdragon")));
                if (contains("positions.list")) {
                    Position.positionlist = getArrayList("positions.list");
                }
                //pos.positions.append(Config.getString("positions.list"));

                //challenges
                SettingsModes.challenge.put(SettingsItems.ItemType.FLYONDAMAGE, SettingsItems.ItemState.valueOf(Config.getString("challenge.flyondamage")));
                SettingsModes.challenge.put(SettingsItems.ItemType.SPEED, SettingsItems.ItemState.valueOf(Config.getString("challenge.speed")));
                SettingsModes.challenge.put(SettingsItems.ItemType.DIRT, SettingsItems.ItemState.valueOf(Config.getString("challenge.dirt")));
                SettingsModes.challenge.put(SettingsItems.ItemType.TENHEARTS, SettingsItems.ItemState.valueOf(Config.getString("challenge.tenhearts")));
                SettingsModes.challenge.put(SettingsItems.ItemType.TRAFFICLIGHT, SettingsItems.ItemState.valueOf(Config.getString("challenge.trafficlight")));
                SettingsModes.challenge.put(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsItems.ItemState.valueOf(Config.getString("challenge.oneblockoneheart")));
                SettingsModes.challenge.put(SettingsItems.ItemType.DAMAGEMIRROR, SettingsItems.ItemState.valueOf(Config.getString("challenge.damagemirror")));
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCEBLOCK, SettingsItems.ItemState.valueOf(Config.getString("challenge.forceblock")));
                SettingsModes.challenge.put(SettingsItems.ItemType.BEDROCKWALL, SettingsItems.ItemState.valueOf(Config.getString("challenge.bedrockwall")));
                SettingsModes.challenge.put(SettingsItems.ItemType.THEFLOORISLAVA, SettingsItems.ItemState.valueOf(Config.getString("challenge.thefloorislava")));
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCEMOB, SettingsItems.ItemState.valueOf(Config.getString("challenge.forcemob")));
            } catch (Exception e) {
                resetConfig();
                loadConfig();
            }
        } else {
            resetConfig();
        }
        return true;
    }

    public static boolean resetConfig() {
        config.set("timer.currenttime", 0);
        config.set("timer.reverse", "DISABLED");
        config.set("timer.starttime", 0);
        config.set("timer.autostart", "DISABLED");
        config.set("settings.onelife", "DISABLED");
        config.set("settings.geteilteherzen", "DISABLED");
        config.set("settings.respawn", "ENABLED");
        config.set("settings.dmgalert", "ENABLED");
        config.set("settings.timer", "ENABLED");
        config.set("settings.backpack", "ENABLED");
        config.set("settings.scoreboard", "ENABLED");
        config.set("settings.maxhp", 20.0);
        config.set("settings.sendtitle", "ENABLED");
        config.set("settings.showcoordsondeath", "ENABLED");
        config.set("settings.resetconfirm", "DISABLED");
        config.set("settings.hardcore", "DISABLED");
        config.set("settings.bungeecord", "DISABLED");
        config.set("scoreboard.tabhp", "ENABLED");
        config.set("gamerule.natrualregeneration", "ENABLED");
        config.set("gamerule.otherregeneration", "ENABLED");
        config.set("gamerule.pvp", "ENABLED");
        config.set("gamerule.keepinventory", "DISABLED");
        config.set("challenge.wither", "DISABLED");
        config.set("challenge.enderdragon", "ENABLED");
        config.set("positions.list", null);
        //config.set("positions.list", null);

        //challenges
        config.set("challenge.flyondamage", "DISABLED");
        config.set("challenge.speed", "DISABLED");
        config.set("challenge.dirt", "DISABLED");
        config.set("challenge.tenhearts", "DISABLED");
        config.set("challenge.trafficlight", "DISABLED");
        config.set("challenge.oneblockoneheart", "DISABLED");
        config.set("challenge.damagemirror", "DISABLED");
        config.set("challenge.forceblock", "DISABLED");
        config.set("challenge.bedrockwall", "DISABLED");
        config.set("challenge.thefloorislava", "DISABLED");
        config.set("challenge.forcemob", "DISABLED");

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadConfig();
        return true;
    }
}
