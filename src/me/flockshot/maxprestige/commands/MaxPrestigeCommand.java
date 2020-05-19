package me.flockshot.maxprestige.commands;

import java.lang.reflect.Field;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.clip.ezprestige.EZPrestige;
import me.clip.ezprestige.PrestigeManager;
import me.clip.ezprestige.objects.Prestige;
import me.flockshot.maxprestige.MaxPrestigePlugin;
import me.flockshot.maxprestige.utils.ColorTranslator;
import me.flockshot.maxprestige.utils.files.identifier.MessageIdentifier;

public class MaxPrestigeCommand implements CommandExecutor, TabCompleter
{
    MaxPrestigePlugin plugin;

    public MaxPrestigeCommand(MaxPrestigePlugin plugin) {
        this.plugin = plugin;
    }

    String command = "/maxprestige ";

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if(sender.hasPermission("maxprestige.command"))
            {                
                Prestige next = PrestigeManager.getNextPrestige(player);
                if(next!=null)
                {
                    if(plugin.econ.getBalance(player)>=next.getCost())
                    {
                        TreeMap<Integer, Prestige> prestigeList = null;
                        try 
                        {
                            Field field = PrestigeManager.class.getDeclaredField("prestigeList");
                            field.setAccessible(true);
                            
                            prestigeList = (TreeMap<Integer, Prestige>) field.get(null);
                        }
                        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException    | SecurityException e) {
                            e.printStackTrace();
                        }
                        
                        Prestige last = null;
                        final int from = next.getPrestige()-1;
                        int to = next.getPrestige();
                        for(int j = next.getPrestige(); j <= prestigeList.size(); j++)
                        {
                            last = prestigeList.get(j);                            
                            double currentCost = last.getCost();
                            
                            if(plugin.econ.getBalance(player)>=currentCost)
                            {
                                plugin.econ.withdrawPlayer(player, currentCost);
                                to = j;
                                for(String line : last.getPrestigeCommands())
                                {
                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), line.replace("%cost%", (new StringBuilder(String.valueOf(last.getCost()))).toString())
                                            .replace("%balance%", (new StringBuilder(String.valueOf(plugin.econ.getBalance(player)))).toString())
                                            .replace("%player%", (new StringBuilder(String.valueOf(player.getName()))).toString())
                                            .replace("%prestige%", (new StringBuilder(String.valueOf(last.getPrestige()))).toString())
                                            .replace("%prestigerank%", EZPrestige.getOptions().getPrestigeRank())
                                            .replace("%displaytag%", last.getDisplayName()));
                                }
                            }
                            else
                                break;
                        }
                        
                        Bukkit.broadcastMessage(ColorTranslator.getString(plugin.getConfig().getString("PrestigeMessage").replaceAll("%player%", player.getName())
                                .replaceAll("%from%", from+"").replaceAll("%to%", to+"")));
                    }
                    else
                        plugin.getLanguageHandler().getLangFile().sendMessage(player, MessageIdentifier.NOT_ENOUGH_MONEY);    
                }
                else
                    plugin.getLanguageHandler().getLangFile().sendMessage(player, MessageIdentifier.NO_PRESTIGE_AVAILABLE);
            
            }
            else
                plugin.getLanguageHandler().getLangFile().sendMessage(player, MessageIdentifier.NO_PERMISSION);
        }
        else
            sender.sendMessage((String) plugin.getLanguageHandler().getLangFile().getLine(MessageIdentifier.ONLY_PLAYER));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
