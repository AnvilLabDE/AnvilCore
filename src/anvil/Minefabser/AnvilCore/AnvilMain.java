package anvil.Minefabser.AnvilCore;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.base.AnvilPlayer;
import anvil.Minefabser.API.base.AnvilPlugin;
import anvil.Minefabser.API.base.AnvilSender;
import anvil.Minefabser.API.base.SubjectManager;
import anvil.Minefabser.API.handler.ConsoleHandler;
import anvil.Minefabser.API.handler.MySQL;
import anvil.Minefabser.API.handler._;
import anvil.Minefabser.AnvilCore.Commands.commandCMD;
import anvil.Minefabser.AnvilCore.Commands.subjectCMD;

public class AnvilMain extends AnvilPlugin {
	
	/**
	 * Gibt eine Instanz dieses {@link Plugin}s zurück
	 * 
	 * @return {@link Plugin}
	 */
	public static Plugin getPlugin() {
		return AnvilMain.getPlugin(AnvilMain.class);
	}
	
	@Override
	public void onEnable() {
		ConsoleHandler.setupReplacements();
		
		checkMySQL();
		setupUsers();
		registerCommands();
				
		ConsoleHandler.sendMessage(_.general_startupMessage);
	}
	
	
	private void registerCommands() {
		try {
			new commandCMD();
			new subjectCMD();
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void setupUsers() {
		Bukkit.getPluginManager().registerEvents(new SubjectManager(), this);
		
		try {
			new AnvilSender(Bukkit.getConsoleSender());
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Load AnvilUser
		
		for (Player p : Bukkit.getOnlinePlayers())
			try {
				new AnvilPlayer(p);
			} catch (SQLException e) {
				ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void checkMySQL() {
		try {
			MySQL.setup();
			ConsoleHandler.sendMessage(_.mysql_successfulConnected);
		} catch (ClassNotFoundException e) {
			ConsoleHandler.sendMessage(_.mysql_cantLoadDriverClass);
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
		
		try {
			MySQL.updateSQL("CREATE TABLE IF NOT EXISTS `" + MySQL.SUBJECT_TABLE + "` " +
							"(`serverID` INT(9) AUTO_INCREMENT PRIMARY KEY, `identifier` TEXT, `displayName` TEXT, " +
							"`prefix` TEXT, `color` VARCHAR(15), `level` INT(9), `permissions` TEXT, `extraData` TEXT)");
			ConsoleHandler.sendMessage(_.mysql_loadedTable, MySQL.SUBJECT_TABLE);
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
		
		try {
			MySQL.updateSQL("CREATE TABLE IF NOT EXISTS `" + MySQL.COMMAND_TABLE + "` " +
							"(`serverID` INT(9) AUTO_INCREMENT PRIMARY KEY, `name` TEXT, `level` INT(9), `enabled` BOOLEAN, " +
							"`description` TEXT, `usageMessage` TEXT, `aliases` TEXT)");
			ConsoleHandler.sendMessage(_.mysql_loadedTable, MySQL.COMMAND_TABLE);
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
	}

}
