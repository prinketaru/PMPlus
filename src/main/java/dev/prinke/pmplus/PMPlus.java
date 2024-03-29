package dev.prinke.pmplus;

import dev.prinke.pmplus.Commands.*;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PMPlus extends JavaPlugin implements CommandExecutor, Listener {

    public messageManager mM;

    public static PMPlus plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("togglepms").setExecutor(new ToggleCommand());
        getCommand("socialspy").setExecutor(new SocialSpyCommand());
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("pmmute").setExecutor(new ReplyCommand(this));
        getCommand("togglesounds").setExecutor(new SoundToggleCommand());
        mM = new messageManager(this);
        createFiles();
    }

    private File configf;
    private FileConfiguration config;

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();

        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}