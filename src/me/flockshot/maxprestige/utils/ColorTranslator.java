package me.flockshot.maxprestige.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColorTranslator
{    
    public static List<String> getTranslatedLore(List<String> lore)
    {
        List<String> translatedList = new ArrayList<String>();
        
        if(lore!=null && !lore.isEmpty())    
            for(String line : lore)
                translatedList.add(getString(line));

        return translatedList;
    }
    
    public static String getString(String st) {
        return ChatColor.translateAlternateColorCodes('&', st);
    }
    
    public static ItemStack translateItem(ItemStack item)
    {
        if(item.hasItemMeta())
        {
            ItemMeta meta = item.getItemMeta();
            
            if(meta.hasLore())
                meta.setLore(getTranslatedLore(meta.getLore()));
            
            if(meta.hasDisplayName())
                meta.setDisplayName(getString(meta.getDisplayName()));
                
            item.setItemMeta(meta);
        }
        return item;
    }



}
