package me.aaron.timer;
//import me.aaron.timer.commands.BetterTimerCommand;
import me.aaron.timer.TabCompletes.*;
import me.aaron.timer.challenges.ForceBlock;
import me.aaron.timer.challenges.Trafficlight;
import me.aaron.timer.commands.*;
import me.aaron.timer.dorfspawn.Dorfspawn;
import me.aaron.timer.listeners.*;
import me.aaron.timer.pos.Position;
import me.aaron.timer.refresh.Reload;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.commands.ResetCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.aaron.timer.commands.HubCommand;
import me.aaron.timer.commands.WorldCommand;
import me.aaron.timer.commands.GmCommand;
import me.aaron.timer.Backpack.BackpackCommand;
import me.aaron.timer.commands.PositionCommand;
import me.aaron.timer.commands.InvseeCommand;
import me.aaron.timer.commands.HealCommand;
import me.aaron.timer.utils.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
//import me.aaron.timer.timer.timer;


public final class Main extends JavaPlugin {
    public Trafficlight trafficlight;
    Config config = new Config();

    public static boolean started = false;
    public static Main plugin;
    public static boolean deletedFolders = false;

    @Override
    public void onLoad() {
        Position pos = new Position();
        if(Config.contains("reset.isReset") && Config.getBoolean("reset.isReset")) {
            deleteFolder("world");
            deleteFolder("world_nether");
            deleteFolder("world_the_end");
            pos.reset();
            Timer.reset();
        }
        deletedFolders = true;
        if(Config.contains("reset.isReset") && Config.getBoolean("reset.isReset")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
        }
        try {
            config.set("reset.isReset", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        Timer.firststart = true;
        Config config = new Config();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        if (!config.getFile().exists()) {
            Config.resetConfig();
        } else {
            Config.loadConfig();
            Bukkit.getLogger().info("§aDas Plugin §8[§6Challenges§8]§a wurde erfolgreich gestartet.");
        }
        for (World wl : Bukkit.getWorlds()) {
            wl.setGameRule(GameRule.KEEP_INVENTORY, SettingsModes.gamerule.get(SettingsItems.ItemType.KEEP_INVENTORY) == SettingsItems.ItemState.ENABLED);
            wl.setPVP(SettingsModes.gamerule.get(SettingsItems.ItemType.PVP) == SettingsItems.ItemState.ENABLED);
        }
        plugin = this;
        started = true;
        Timer.setCurrentTime(Config.getInt("timer.currenttime"));
        Timer.run();

        listenerRegistration();
        commandRegistration();
        TabCompleterRegistration();

        if (SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT) == SettingsItems.ItemState.ENABLED) {
            trafficlight = new Trafficlight(this);
            trafficlight.start();
        }

        if (SettingsModes.settings.get(SettingsItems.ItemType.HARDCORE) == SettingsItems.ItemState.ENABLED) {
            for (World wl : Bukkit.getWorlds()) {
                wl.setHardcore(true);
            }
        } else {
            for (World wl : Bukkit.getWorlds()) {
                wl.setHardcore(false);
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK) == SettingsItems.ItemState.ENABLED) {
            ForceBlock forceBlock = new ForceBlock(getInstance());
            forceBlock.start();
        }

    }

    @Override
    public void onDisable() {
        Config.saveConfig();
    }


    private void listenerRegistration() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new QuitListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new TeleportListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new TriggerListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new KickListener(), this);
        //pm.registerEvents(new InteractListener(), this);
        pm.registerEvents(new DamageRemover(), this);
        pm.registerEvents(new HealListener(), this);
        pm.registerEvents(new MoveListener(), this);
        pm.registerEvents(new FoodListener(), this);
        pm.registerEvents(new AdvancementsListener(), this);
        pm.registerEvents(new DamageByEntityListener(), this);
    }

    private void commandRegistration() {
        this.getCommand("timer").setExecutor(new TimerCommand());
        this.getCommand("hub").setExecutor(new HubCommand());
        this.getCommand("world").setExecutor(new WorldCommand());
        this.getCommand("gm").setExecutor(new GmCommand());
        this.getCommand("gamemode").setExecutor(new GmCommand());
        this.getCommand("backpack").setExecutor(new BackpackCommand());
        this.getCommand("bp").setExecutor(new BackpackCommand());
        this.getCommand("position").setExecutor(new PositionCommand());
        this.getCommand("pos").setExecutor(new PositionCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("nv").setExecutor(new NvCommand());
        this.getCommand("invsee").setExecutor(new InvseeCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("reset").setExecutor(new ResetCommand());
        this.getCommand("refresh").setExecutor(new Reload());
        this.getCommand("rl").setExecutor(new Reload());
        this.getCommand("sun").setExecutor(new SunCommand());
        this.getCommand("rain").setExecutor(new RainCommand());
        this.getCommand("thunder").setExecutor(new ThunderCommand());
        this.getCommand("settings").setExecutor(new SettingsCommand());
        this.getCommand("trash").setExecutor(new TrashCommand());
        this.getCommand("seed").setExecutor(new SeedCommand());
        this.getCommand("l").setExecutor(new HubCommand());
        this.getCommand("dorf").setExecutor(new Dorfspawn());
        this.getCommand("save").setExecutor(new SaveCommand());
    }

    private void TabCompleterRegistration() {
        this.getCommand("timer").setTabCompleter(new TimerTabComplete());
        this.getCommand("hub").setTabCompleter(new HubTabCompleter());
        this.getCommand("world").setTabCompleter(new WorldTabCompleter());
        this.getCommand("gm").setTabCompleter(new GmTabCompleter());
        this.getCommand("gamemode").setTabCompleter(new GmTabCompleter());
        this.getCommand("position").setTabCompleter(new PositionTabCompleter());
        this.getCommand("pos").setTabCompleter(new PositionTabCompleter());
    }

    public void copy(File sourceLocation, File targetLocation) throws IOException {
        if(sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if(!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    private void deleteFolder(String folder) {
        if(Files.exists(Paths.get(folder))) {
            try {
                Files.walk(Paths.get(folder)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Main getInstance() {
        return plugin;
    }

    public static String getPrefix(String name, String Content) {
        return "§8[§6" + name + "§8] §7" + Content;
    }

    public ArrayList<String> getPermissions(Player p) {
        ArrayList<String> permissions = new ArrayList<>();
        if (getConfig().contains(p.getUniqueId().toString() + ".permissions")) {
            permissions = (ArrayList<String>) getConfig().getStringList(p.getUniqueId().toString() + ".permissions");
        }

        return permissions;
    }

}
