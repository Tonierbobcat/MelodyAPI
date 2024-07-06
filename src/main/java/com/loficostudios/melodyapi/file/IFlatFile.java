/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.file;
import org.bukkit.plugin.java.JavaPlugin;

public interface IFlatFile extends IFile {

    void create(final JavaPlugin plugin);

    void save();

    Object getBase();
}
