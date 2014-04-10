package anvil.Minefabser.API.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.handler.ConsoleHandler;
import anvil.Minefabser.API.handler.MySQL;
import anvil.Minefabser.API.handler._;

public class SubjectManager implements Listener {
	
	private static Map<String, AnvilSubject> subjectMap = new HashMap<>();
	
	/**
	 * Gibt die Map mit allen Subjekten zurück
	 * 
	 * @return  {@link Map}<{@link String}, {@link AnvilSubject}>
	 */
	public static Map<String, AnvilSubject> getSubjectMap() {
		return subjectMap;
	}
	
	/**
	 * Fügt ein {@link AnvilSubject} zur SubjectMap hinzu
	 * 
	 * @param {@link AnvilSubject}
	 */
	public static void addSubject(AnvilSubject subject) {
		subjectMap.put(subject.getIdentifier(), subject);
	}
	
	/**
	 * Entfernt ein {@link AnvilSubject} von der SubjectMap
	 * 
	 * @param {@link AnvilSubject}
	 */
	public static void removeSubject(AnvilSubject subject) {
		subjectMap.put(subject.getIdentifier(), subject);
	}
	
	public static AnvilSubject getSubjectByIdentifier(String identifier) {
		return subjectMap.get(identifier);
	}
	
	/**
	 * Gibt das {@link AnvilSubject} zu einer UniqueID ({@link UUID}) zurück
	 * 
	 * @param UniqueID - {@link UUID}
	 * 
	 * @return {@link AnvilSubject}
	 */
	public static AnvilSubject getSubject(UUID uniqueID) {
		return getSubjectByIdentifier(uniqueID.toString());
	}
	
	/**
	 * Gibt das {@link AnvilSubject} zu einem CommandSender zurück
	 * 
	 * @param {@link CommandSender}
	 * 
	 * @return {@link AnvilSubject}
	 */
	public static AnvilSubject getSubject(CommandSender cs) {
		return getSubjectByIdentifier(cs instanceof Player? ((Player) cs).getUniqueId().toString() : cs.getName());
	}
	
	/**
	 * Gibt das {@link AnvilSubject} zu einem Namen zurück
	 * 
	 * @param Name
	 * 
	 * @return {@link AnvilSubject}
	 */
	public static AnvilSubject getSubject(String name) {	
		if (name.equals(Bukkit.getConsoleSender().getName()))
			return getSubject(Bukkit.getConsoleSender());
		
		@SuppressWarnings("deprecation")
		Player p = Bukkit.getPlayerExact(name);
		if (p != null)
			return getSubject(p);
		
		return null;
	}
	
	/**
	 * Gibt eine {@link List}e mit Subjekten zurück, die auf die Filter passen
	 * 
	 * @param {@link String} mit dem der Name des Subjekts starten muss
	 * @param Sollen {@link AnvilSubject}s erlaubt sein?
	 * @param Sollen {@link AnvilSender} erlaubt sein?
	 * @param Sollen {@link AnvilPlayer} erlaubt sein?
	 * @param Sollen {@link AnvilGroup}s erlaubt sein?
	 * 
	 * @return {@link List}<{@link String}>
	 */
	public static List<String> getComplete(String arg, boolean userAllowed, boolean senderAllowed, boolean playerAllowed, boolean groupAllowed) {
		List<String> complete = new ArrayList<>();
		
		for (AnvilSubject subject : getSubjectMap().values())
			if (subject.getName().toLowerCase().startsWith(arg.toLowerCase()))
				if (!playerAllowed && subject instanceof AnvilPlayer)
					continue;
				else
				if (!senderAllowed && subject instanceof AnvilSender)
					continue;
				else
				if (!userAllowed && subject instanceof AnvilSubject)
					continue;
				else
				if (!groupAllowed && subject instanceof AnvilGroup)
					continue;
				else
					complete.add(subject.getName());
		
		return complete;
	}
	
	/**
	 * Überprüft anhand eines Identifiers, ob ein {@link AnvilSubject} bereits in der MySQL-Datenbank existiert
	 * 
	 * @param Identifier
	 * @return Existiert das {@link AnvilSubject} in der MySQL-Datenbank
	 * @throws SQLException
	 */
	public static boolean existsInMySQLDatabase(String identifier) throws SQLException {
		ResultSet	resultSet	= MySQL.querySQL("SELECT * FROM `" + MySQL.SUBJECT_TABLE + "` WHERE `identifier` = '" + identifier + "'");
		
		return resultSet.next();
	}
	
	// Listener Stuff \\
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		try {
			new AnvilPlayer(event.getPlayer());
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		removeSubject(getSubject(event.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		removeSubject(getSubject(event.getPlayer()));
	}

}
