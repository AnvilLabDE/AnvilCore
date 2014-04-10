package anvil.Minefabser.API.handler;

import java.text.MessageFormat;

/**
 * In diese Klasse ist das bisherige Sprachsystem ausgelagert
 * 
 * @author Minefabser
 */
public class _ {
	
	/**
	 * Formatiert eine Nachricht
	 * @param Nachricht
	 * @param Zu ergänzende Argumente
	 * @return Formatierte Nachricht
	 */
	public static String format(String message, Object... args) {
		return MessageFormat.format(message, args);
	}
	
	public static String general_startupMessage			= "§6AnvilCore§2 für das Projekt§6 AnvilLabDE§2 wurde komplett geladen.";
	
	public static String error_noBoolean				= "§cDer Wert§f {0}§c ist kein Wahrheitswert.";
	public static String error_cannotBeat				= "§cDu darfst diese Aktion nicht auf§6 {0}§c anwenden!";

	public static String mysql_cantLoadDriverClass		= "§cDer§6 Treiber§c für die§6 MySQL-Verbinung§c konnte nicht geladen werden!";
	public static String mysql_sqlExeption				= "§cFehler beim Ausführen eines SQL-Befehls:§f {0}";
	public static String mysql_successfulConnected		= "§aErfolgreich mit der§6 MySQL-Datenbank§a verbunden!";
	public static String mysql_loadedTable				= "§aDie Tabelle§6 {0}§a wurde erfolgreich geladen!";

	//public static String command_noTabSupport			= "Dieser Befehl untersützt die <Tab>-Funktion nicht.";
	public static String command_notEnabled				= "§cDer Befehl§6 {0}§c ist zurzeit §4deaktiviert§c.";
	public static String command_notEnoughRights		= "§cDu hast nicht genügend Rechte für den Befehl§6 {0}§c.";
	public static String command_cannotFind				= "§cDer Befehl§f {0}§c konnte nicht gefunden werden.";
	public static String command_notAnAnvilCommand		= "§cDer Befehl§6 {0}§c ist kein Anvil--Befehl.";

	public static String subject_notLoadedCorrectly		= "§cDas Subjekt§6 {0}§c konnte nicht korrekt geladen werden!";
	public static String subject_cannotFind				= "§cDas Subjekt§6 {0}§c konnte nicht gefunden werden.";
	
	public static String menu_format_topic				= "§8-----§f  {0}  §8-----";
	public static String menu_format_option				= "§7 >>  §9{0}§7:";
	public static String menu_format_value_withDescr	= "§7    >>  §f{0}§f - §b{1}";
	public static String menu_format_value_withoutDescr	= "§7    >>  §f{0}";

	public static String commandCMD_alreadyStatus		= "§cDer Befehl§6 {0}§c ist bereits in diesem Status§c.";
	public static String plus_commandSettingChanged		= "§6{0}§a hat im Befehl§6 {1}§a die Option§6 {2}§a auf§6 {3}§a gesetzt.";
	public static String commandCMD_SettingStatus		= "§cDer Befehl§6 {0}§c ist bereits §2aktiviert§c!";

}
