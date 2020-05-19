package me.flockshot.maxprestige.utils.filesystem.files;

import java.io.File;

import me.flockshot.maxprestige.utils.files.identifier.MessageIdentifier;
import me.flockshot.maxprestige.utils.filesystem.Language;
import me.flockshot.maxprestige.utils.filesystem.UnknownLanguage;

public class English extends UnknownLanguage
{
    
    
    
    public English(File file) {
        super(file);
        setLanguage(Language.EN);
    }


    @Override
    public void setDefaults() {
        
        addDefault(MessageIdentifier.NOT_ENOUGH_MONEY, "&6You don't have enough money to prestige");
        addDefault(MessageIdentifier.NO_PERMISSION, "&4No permission");
        addDefault(MessageIdentifier.ONLY_PLAYER, "&6Only players can use this command");
        addDefault(MessageIdentifier.NO_PRESTIGE_AVAILABLE, "&6There are no more prestiges");        
    }
   
    
    
    
    
}
