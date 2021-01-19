package me.aaron.timer.utils;

import javafx.geometry.Pos;
import me.aaron.timer.commands.BackpackCommand;
import me.aaron.timer.projects.AllItems;
import me.aaron.timer.projects.AllMobs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

    public static ArrayList getStringList(String path) {
        return (ArrayList) config.getStringList(path);
    }

    public static boolean saveConfig() {
        try {
            config.set("timer.currenttime", SettingsModes.currentTime);
            config.set("timer.reverse", SettingsModes.timer.get(SettingsItems.ItemType.REVERSE).name());
            config.set("timer.starttime", SettingsModes.startTime);
            config.set("timer.autostart", SettingsModes.timer.get(SettingsItems.ItemType.AUTOSTART).name());
            config.set("timer.state", Timer.state.name());
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
            config.set("settings.backup", SettingsModes.ints.get(SettingsItems.ItemType.BACKUP));
            config.set("settings.backuptimer", Backup.timertime);
            config.set("settings.afk", SettingsModes.settings.get(SettingsItems.ItemType.AFK).name());
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
            config.set("challenge.no_crafting", SettingsModes.challenge.get(SettingsItems.ItemType.NO_CRAFTING).name());
            config.set("challenge.no_trading", SettingsModes.challenge.get(SettingsItems.ItemType.NO_TRADING).name());
            config.set("challenge.forceheight", SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_HEIGHT).name());
            config.set("challenge.forcebiome", SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_BIOME).name());

            //projects
            config.set("project.allitems.state", SettingsModes.projects.get(SettingsItems.ItemType.ALL_ITEMS).name());
            if (AllItems.items.size() != 0) {
                config.set("project.allitems.items", AllItems.items.toArray());
            }
            if (AllItems.item != null) {
                config.set("project.allitems.current", AllItems.item.name());
            }

            config.set("project.allmobs.state", SettingsModes.projects.get(SettingsItems.ItemType.ALL_MOBS).name());
            if (AllMobs.mobnames.size() != 0) {
                config.set("project.allmobs.mobs", AllMobs.mobnames);
            }

            for (int i = 0; i < BackpackCommand.inventory.getSize(); i ++) {
                config.set("backpack." + i + ".type", BackpackCommand.inventory.getItem(i) == null ? "AIR" : BackpackCommand.inventory.getItem(i).getType().name());
                config.set("backpack." + i + ".amount", BackpackCommand.inventory.getItem(i) == null ? 0 : BackpackCommand.inventory.getItem(i).getAmount());
            }
            config.save(file);
            return true;
        } catch (Exception e) {
            resetConfig();
            return false;
        }
    }

    public static boolean loadConfig() {
        if (Config.file.exists()) {
            try {
                SettingsModes.timer.put(SettingsItems.ItemType.REVERSE, SettingsItems.ItemState.valueOf(Config.getString("timer.reverse")));
            } catch (Exception e) {
                resetSingle("timer.reverse", "DISABLED");
            }
            try {
                SettingsModes.startTime = Config.getInt("timer.starttime");
            } catch (Exception e) {
                resetSingle("timer.starttime", "0");
            }
            try {
                SettingsModes.currentTime = Config.getInt("timer.currenttime");
            } catch (Exception e) {
                resetSingle("timer.currenttime", "0");
            }
            try {
                SettingsModes.timer.put(SettingsItems.ItemType.AUTOSTART, SettingsItems.ItemState.valueOf(Config.getString("timer.autostart")));
            } catch (Exception e) {
                resetSingle("timer.autostart", "DISABLED");
            }
            try {
                Timer.state = Timer.TimerState.valueOf(Config.getString("timer.state"));
            } catch (Exception e) {
                resetSingle("timer.state", "PAUSED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.ONELIFE, SettingsItems.ItemState.valueOf(Config.getString("settings.onelife")));
            } catch (Exception e) {
                resetSingle("settings.onelife", "DISABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.GEITEILTEHERZEN, SettingsItems.ItemState.valueOf(Config.getString("settings.geteilteherzen")));
            } catch (Exception e) {
                resetSingle("settings.geteilteherzen", "DISABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.RESPAWN, SettingsItems.ItemState.valueOf(Config.getString("settings.respawn")));
            } catch (Exception e) {
                resetSingle("settings.respawn", "ENABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.DMGALERT, SettingsItems.ItemState.valueOf(Config.getString("settings.dmgalert")));
            } catch (Exception e) {
                resetSingle("settings.dmgaltert", "DISABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.TIMER, SettingsItems.ItemState.valueOf(Config.getString("settings.timer")));
            } catch (Exception e) {
                resetSingle("settings.timer", "ENABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.BACKPACK, SettingsItems.ItemState.valueOf(Config.getString("settings.backpack")));
            } catch (Exception e) {
                resetSingle("settings.backpack", "ENABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.SCOREBOARD, SettingsItems.ItemState.valueOf(Config.getString("settings.scoreboard")));
            } catch (Exception e) {
                resetSingle("settings.scoreboard", "ENABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.SHOWCOORDSONDEAETH, SettingsItems.ItemState.valueOf(Config.getString("settings.showcoordsondeath")));
            } catch (Exception e) {
                resetSingle("settings.showcoordsondeath", "ENABLED");
            }
            try {
                SettingsModes.maxHP = Config.getDouble("settings.maxhp");
            } catch (Exception e) {
                resetSingle("settings.maxhp", "20.0");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.SENDTITLE, SettingsItems.ItemState.valueOf(Config.getString("settings.sendtitle")));
            } catch (Exception e) {
                resetSingle("settings.sendtitle", "ENABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.RESETCONFIRM, SettingsItems.ItemState.valueOf(Config.getString("settings.resetconfirm")));
            } catch (Exception e) {
                resetSingle("settings.resetconfirm", "DISABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.HARDCORE, SettingsItems.ItemState.valueOf(Config.getString("settings.hardcore")));
            } catch (Exception e) {
                resetSingle("settings.hardcore", "DISABLED");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.BUNGEECORD, SettingsItems.ItemState.valueOf(Config.getString("settings.bungeecord")));
            } catch (Exception e) {
                resetSingle("settings.bungeecord", "DISABLED");
            }
            try {
                SettingsModes.ints.put(SettingsItems.ItemType.BACKUP, Config.getInt("settings.backup"));
            } catch (Exception e) {
                resetSingle("settings.backup", "0");
            }
            SettingsModes.settings.put(SettingsItems.ItemType.BACKUP, (Config.getInt("settings.backup") == 0) ? SettingsItems.ItemState.DISABLED : SettingsItems.ItemState.ENABLED);
            try {
                Backup.timertime = Config.getInt("settings.backuptimer");
            } catch (Exception e) {
                resetSingle("settings.backuptimer", "0");
            }
            try {
                SettingsModes.settings.put(SettingsItems.ItemType.AFK, SettingsItems.ItemState.valueOf(Config.getString("settings.afk")));
            } catch (Exception e) {
                resetSingle("settings.afk", "ENABLED");
            }
            try {
                SettingsModes.scoreboard.put(SettingsItems.ItemType.TABHP, SettingsItems.ItemState.valueOf(Config.getString("scoreboard.tabhp")));
            } catch (Exception e) {
                resetSingle("scoreboard,tabhp", "ENABLED");
            }
            try {
                SettingsModes.gamerule.put(SettingsItems.ItemType.NATURALREGENERATION, SettingsItems.ItemState.valueOf(Config.getString("gamerule.natrualregeneration")));
            } catch (Exception e) {
                resetSingle("gamerule.naturalregeneration", "ENABLED");
            }
            try {
                SettingsModes.gamerule.put(SettingsItems.ItemType.OTHERREGENERATION, SettingsItems.ItemState.valueOf(Config.getString("gamerule.otherregeneration")));
            } catch (Exception e) {
                resetSingle("gamerule.otherregeneration", "ENABLED");
            }
            try {
                SettingsModes.gamerule.put(SettingsItems.ItemType.PVP, SettingsItems.ItemState.valueOf(Config.getString("gamerule.pvp")));
            } catch (Exception e) {
                resetSingle("gamerule.pvp", "ENABLED");
            }
            try {
                SettingsModes.gamerule.put(SettingsItems.ItemType.KEEP_INVENTORY, SettingsItems.ItemState.valueOf(Config.getString("gamerule.keepinventory")));
            } catch (Exception e) {
                resetSingle("gamerule.keepinventory", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.WITHER, SettingsItems.ItemState.valueOf(Config.getString("challenge.wither")));
            } catch (Exception e) {
                resetSingle("challenge.wither", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.ENDER_DRAGON, SettingsItems.ItemState.valueOf(Config.getString("challenge.enderdragon")));
            } catch (Exception e) {
                resetSingle("challenge.enderdragon", "ENABLED");
            }
            if (contains("positions.list")) {
                Position.positionlist = getArrayList("positions.list");
            }
            //challenges
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.FLYONDAMAGE, SettingsItems.ItemState.valueOf(Config.getString("challenge.flyondamage")));
            } catch (Exception e) {
                resetSingle("challenge.flyondamage", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.SPEED, SettingsItems.ItemState.valueOf(Config.getString("challenge.speed")));
            } catch (Exception e) {
                resetSingle("challenge.speed", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.DIRT, SettingsItems.ItemState.valueOf(Config.getString("challenge.dirt")));
            } catch (Exception e) {
                resetSingle("challenge.dirt", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.TENHEARTS, SettingsItems.ItemState.valueOf(Config.getString("challenge.tenhearts")));
            } catch (Exception e) {
                resetSingle("challenge.tenhearts", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.TRAFFICLIGHT, SettingsItems.ItemState.valueOf(Config.getString("challenge.trafficlight")));
            } catch (Exception e) {
                resetSingle("challenge.trafficlight", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.ONEBLOCKONEHEART, SettingsItems.ItemState.valueOf(Config.getString("challenge.oneblockoneheart")));
            } catch (Exception e) {
                resetSingle("challenge.oneblockoneheart", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.DAMAGEMIRROR, SettingsItems.ItemState.valueOf(Config.getString("challenge.damagemirror")));
            } catch (Exception e) {
                resetSingle("challenge.damagemirror", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCEBLOCK, SettingsItems.ItemState.valueOf(Config.getString("challenge.forceblock")));
            } catch (Exception e) {
                resetSingle("challenge.forceblock", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.BEDROCKWALL, SettingsItems.ItemState.valueOf(Config.getString("challenge.bedrockwall")));
            } catch (Exception e) {
                resetSingle("challenge.bedrockwall", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.THEFLOORISLAVA, SettingsItems.ItemState.valueOf(Config.getString("challenge.thefloorislava")));
            } catch (Exception e) {
                resetSingle("challenge.thefloorislava", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCEMOB, SettingsItems.ItemState.valueOf(Config.getString("challenge.forcemob")));
            } catch (Exception e) {
                resetSingle("challenge.forcemob", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.NO_CRAFTING, SettingsItems.ItemState.valueOf(Config.getString("challenge.no_crafting")));
            } catch (Exception e) {
                resetSingle("challenge.no_crafting", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.NO_TRADING, SettingsItems.ItemState.valueOf(Config.getString("challenge.no_trading")));
            } catch (Exception e) {
                resetSingle("challenge.no_trading", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_HEIGHT, SettingsItems.ItemState.valueOf(Config.getString("challenge.forceheight")));
            } catch (Exception e) {
                resetSingle("challenge.forceheight", "DISABLED");
            }
            try {
                SettingsModes.challenge.put(SettingsItems.ItemType.FORCE_BIOME, SettingsItems.ItemState.valueOf(Config.getString("challenge.forcebiome")));
            } catch (Exception e) {
                resetSingle("challenge.forcebiome", "DISABLED");
            }

            //projects
            try {
                SettingsModes.projects.put(SettingsItems.ItemType.ALL_ITEMS, SettingsItems.ItemState.valueOf(Config.getString("project.allitems.state")));
            } catch (Exception e) {
                resetSingle("project.allitems.state", "DISABLED");
            }
            if (contains("project.allitems.items")) {
                AllItems.items = getArrayList("project.allitems.items");
            }
            if (contains("project.allitems.current")) {
                AllItems.item = Material.valueOf(Config.getString("project.allitems.current"));
            }

            try {
                SettingsModes.projects.put(SettingsItems.ItemType.ALL_MOBS, SettingsItems.ItemState.valueOf(Config.getString("project.allmobs.state")));
            } catch (Exception e) {
                resetSingle("project.allmobs.state", "DISABLED");
            }
            if (contains("project.allmobs.mobs")) {
                AllMobs.mobnames = getArrayList("project.allmobs.mobs");
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
        config.set("timer.state", "PAUSED");
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
        config.set("settings.backup", 0);
        config.set("settings.backuptimer", 0);
        config.set("settings.afk", "ENABLED");
        config.set("scoreboard.tabhp", "ENABLED");
        config.set("gamerule.natrualregeneration", "ENABLED");
        config.set("gamerule.otherregeneration", "ENABLED");
        config.set("gamerule.pvp", "ENABLED");
        config.set("gamerule.keepinventory", "DISABLED");
        config.set("challenge.wither", "DISABLED");
        config.set("challenge.enderdragon", "ENABLED");
        config.set("positions.list", null);

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
        config.set("challenge.no_crafting", "DISABLED");
        config.set("challenge.no_trading", "DISABLED");
        config.set("challenge.forceheight", "DISABLED");
        config.set("challenge.forcebiome", "DISABLED");

        //projects
        config.set("project.allitems.state", "DISABLED");
        config.set("project.allitems.items", null);
        config.set("project.allitems.current", null);

        config.set("project.allmobs.state", "DISABLED");
        config.set("project.allmobs.mobs", null);

        //backpack
        config.set("backpack", null);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadConfig();
        return true;
    }

    public static void resetSingle(String path, String value) {
        config.set(path, value);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadConfig();
    }
}
