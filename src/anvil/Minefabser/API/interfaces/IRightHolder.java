package anvil.Minefabser.API.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRightHolder {
	
	/**
	 * Gibt das Level des Objekts zurück
	 * 
	 * @return Level
	 */
	public int getLevel();
	
	/**
	 * Setzt das Level des Objekts
	 * 
	 * @param Neues Level
	 * @throws SQLException 
	 */
	public void setLevel(int level) throws SQLException;
	
	/**
	 * Überprüft ob das Objekt ein Level schlagen kann
	 * 
	 * @param Zu schlagendes Level
	 * 
	 * @return Kann das Objekt das Level schlagen?
	 */
	public boolean beats(int level);
	
	/**
	 * Überprüft ob das Objekt ein anderes Objekt schlagen kann
	 * 
	 * @param Anderes RightHolder-Objekt
	 * 
	 * @return Kann das Objekt ein anderes Objekt schlagen?
	 */
	public boolean beats(IRightHolder rightHolder);
	
	/**
	 * Gibt dem Objekt eine {@link Permission}
	 * 
	 * @param Zu gebende {@link Permission}
	 */
	public void givePermission(String permission);
	
	/**
	 * Nimmt dem Objekt eine {@link Permission}
	 * 
	 * @param Zu nehmende {@link Permission}
	 */
	public void removePermission(String permission);
	
	/**
	 * Überprüft ob das Objekt eine {@link Permission} hat
	 * 
	 * @param Zu überprüfende {@link Permission}
	 * 
	 * @return Hat das Objekt die Permission? 
	 */
	public boolean hasPermission(String permission);
	
	/**
	 * Gibt eine Liste der {@link Permission}s des Objekts zurück
	 * 
	 * @return Liste der {@link Permission}s
	 */
	public List<String> getPermissions();

}
