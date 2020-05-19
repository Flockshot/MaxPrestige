package me.flockshot.maxprestige.utils.files.identifier;

public enum MessageIdentifier implements FileIdentifier
{
    NO_CLAIMS_LEFT, NOT_ENOUGH_MONEY, NO_PERMISSION, ONLY_PLAYER, NO_PRESTIGE_AVAILABLE ;
    //NULL_NAME;
    
    
    
    
    
    
    
    @Override
    public String toString() {
        return super.toString().replace("$", ".");
    }
}
