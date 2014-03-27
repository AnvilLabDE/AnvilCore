package anvil.Minefabser.API.base;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Repräsentiert ein Anvil-Plugin basierend auf einem {@link JavaPlugin}
 * 
 * @author Minefabser
 */
public abstract class AnvilPlugin extends JavaPlugin {
	
	/**
	 * Diese Methode wird immer ausgeführt, wenn das Plugin geladen wird
	 */
	public abstract void onEnable();

}
