package me.flockshot.maxprestige.utils.filesystem;

import java.io.File;

public interface FlockFile extends FlockConfig
{
    public void setFile(File file);
    public File getFile();

}
