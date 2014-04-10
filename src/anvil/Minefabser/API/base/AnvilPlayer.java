package anvil.Minefabser.API.base;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

public class AnvilPlayer extends AnvilSender {
	
	private UUID	uniqueID;

	/**
	 * Erstellt/lädt einen AnvilPlayer basierend auf einem {@link AnvilSender}, welcher auf einem {@link AnvilSubject} basiert
	 * 
	 * @param {@link Player} der dem AnvilPlayer zugeordnet sein soll
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public AnvilPlayer(Player p) throws SQLException, ParseException {
		super(p);
		
		this.uniqueID	= p.getUniqueId();
	}
	
	/**
	 * Gibt den {@link Player} zum AnvilPlayer zurück
	 * 
	 * @return {@link Player}
	 */
	public Player getPlayer() {
		return (Player) super.getCommandSender();
	}
	
	/**
	 * Gibt die UniqueID ({@link UUID}) des AnvilUser zurück
	 * 
	 * @return UniqueID - {@link UUID}
	 */
	public UUID getUniqueID() {
		return this.uniqueID;
	}

}
