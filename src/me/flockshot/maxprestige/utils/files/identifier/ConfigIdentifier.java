package me.flockshot.maxprestige.utils.files.identifier;


public enum ConfigIdentifier implements FileIdentifier
{
    PERMISSION, LIMIT_COMMAND;
    

    
    @Override
    public String toString() {
        return super.toString().replace("$", ".");
    }
}
