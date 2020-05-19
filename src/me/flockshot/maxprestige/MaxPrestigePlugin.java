package me.flockshot.maxprestige;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.clip.ezprestige.EZPrestige;
import me.flockshot.maxprestige.commands.MaxPrestigeCommand;
import me.flockshot.maxprestige.utils.PlaceholderTranslator;
import me.flockshot.maxprestige.utils.filesystem.Language;
import me.flockshot.maxprestige.utils.handlers.LanguageHandler;
import net.milkbowl.vault.economy.Economy;


public class MaxPrestigePlugin extends JavaPlugin implements Listener
{
    
    private boolean running = false;
    public Economy econ = null;
    private static EZPrestige ezPrestige;
    private LanguageHandler languageHandler;
    //private ConfigHandler configHandler;
    
    
    
    
    public void onEnable()    
    {

            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                new PlaceholderTranslator(this).register();
            
            getConfig().options().copyDefaults(true);        
            saveConfig();
            
            //setConfigHandler(new ConfigHandler(this));
            setLanguageHandler(new LanguageHandler(this, Language.EN));
            
            if(!running)
            {
                if (!setupEconomy() ) {
                    getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
                    getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                setupEZPrestige();
          
                getCommand("maxprestige").setExecutor(new MaxPrestigeCommand(this));
                getServer().getPluginManager().registerEvents(this, this);
                running = true;

            }

    }
    

    public void onDisable() {
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupEZPrestige() {
        if (getServer().getPluginManager().getPlugin("EZPrestige") == null) {
            return false;
        }
        RegisteredServiceProvider<EZPrestige> rsp = getServer().getServicesManager().getRegistration(EZPrestige.class);
        if (rsp == null) {
            return false;
        }
        setEzPrestige(rsp.getProvider());
        return getEzPrestige() != null;
    }
    
    


    public LanguageHandler getLanguageHandler() {
        return languageHandler;
    }
    public void setLanguageHandler(LanguageHandler languageHandler) {
        this.languageHandler = languageHandler;
    }

    public EZPrestige getEzPrestige() {
        return ezPrestige;
    }
    public static void setEzPrestige(EZPrestige ezPrestige) {
        MaxPrestigePlugin.ezPrestige = ezPrestige;
    }

    
    
}
