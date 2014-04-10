package anvil.Minefabser.API.interfaces;

import java.sql.SQLException;

import org.bukkit.ChatColor;

public interface IDisplayable {
	
	/**
	 * Gibt den Namen des Objekts zur�ck
	 * 
	 * @return Name
	 */
	public String getName();
	
	/**
	 * Gibt den Anzeige-Namen des Objekts zur�ck
	 * 
	 * @return Anzeige-Name
	 */
	public String getDisplayName();
	
	/**
	 * Setzt den Anzeige-Namen des Objekts
	 * 
	 * @param Neuer Anzeige-Name
	 * @throws SQLException 
	 */
	public void setDisplayName(String displayName) throws SQLException;
	
	/**
	 * Gibt den Pr�fix des Objekts zur�ck
	 * 
	 * @return Pr�fix
	 */
	public String getPrefix();
	
	/**
	 * Setzt den Pr�fix des Objekts
	 * 
	 * @param Neuer Pr�fix
	 * @throws SQLException 
	 */
	public void setPrefix(String prefix) throws SQLException;
	
	/**
	 * Gibt die (Anzeige-){@link ChatColor} des Objekts zur�ck
	 * 
	 * @return (Anzeige-){@link ChatColor}
	 */
	public ChatColor getColor();
	
	/**
	 * Setzt die (Anzeige-){@link ChatColor} des Objekts
	 * 
	 * @param Neue (Anzeige-){@link ChatColor}
	 * @throws SQLException 
	 */
	public void setColor(ChatColor color) throws SQLException;
	
}
