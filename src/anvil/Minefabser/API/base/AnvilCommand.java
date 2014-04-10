package anvil.Minefabser.API.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R2.CraftServer;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.handler.ConsoleHandler;
import anvil.Minefabser.API.handler.MySQL;
import anvil.Minefabser.API.handler._;

public abstract class AnvilCommand extends Command {
	
	private int		level	= Integer.MAX_VALUE;
	private boolean	enabled	= true;
	
	/**
	 * Erstellt ein AnvilCommand basierend auf einem {@link Command}
	 * 
	 * @param Name des Befehls
	 * @param Beschreibung des Befehls
	 * @param Benutzung des Befehls
	 * @param Aliases (/Synonyme) für den Befehl
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public AnvilCommand(String name, int level, String description, String usageMessage, String... aliases) throws SQLException, ParseException {
		super(name, description, usageMessage, Arrays.asList(aliases));
		
		this.level = level;
		
		((CraftServer) Bukkit.getServer()).getCommandMap().register("anvil", this);
		
		if (!MySQL.querySQL("SELECT * FROM `" + MySQL.COMMAND_TABLE + "` WHERE `name` = '" + super.getName() + "'").next()) {
			JSONArray jsonAliases = new JSONArray();
			jsonAliases.addAll(super.getAliases());
			
			MySQL.updateSQL("INSERT INTO `" + MySQL.COMMAND_TABLE + "` " +
							"(`name`, `level`, `enabled`, `description`, `usageMessage`, `aliases`) VALUES " +
							"('" + super.getName() + "', '" + this.level + "', '" + (this.enabled? 1 : 0) + "', " +
							"'" + super.getDescription() + "', '" + super.getUsage() + "', '" + jsonAliases.toJSONString() + "')");
		} else {
			ResultSet resultSet = MySQL.querySQL("SELECT * FROM `" + MySQL.COMMAND_TABLE + "` WHERE `name` = '" + super.getName() + "'");
			
			while (resultSet.next()) {
				this.level		= resultSet.getInt("level");
				this.enabled	= resultSet.getBoolean("enabled");
				
				super.setDescription(resultSet.getString("description"));
				super.setUsage(resultSet.getString("usageMessage"));
				super.setAliases((JSONArray) new JSONParser().parse(resultSet.getString("aliases")));
			}
		}
	}
	
	/**
	 * Gibt das Level des Befehls zurück
	 * 
	 * @return Level
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Setzt das Level des Befehls
	 * 
	 * @param Neues Level
	 * @throws SQLException 
	 */
	public void setLevel(int level) throws SQLException {
		this.level = level;
		MySQL.updateSQL("UPDATE `" + MySQL.COMMAND_TABLE + "` SET `level` = '" + this.level + "' WHERE `name` = '" + super.getName() + "'");
	}
	
	/**
	 * Gibt zurück, ob der Befehl aktiviert ist
	 * 
	 * @return Ist der Befehl aktiviert?
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	/**
	 * Setzt ob der Befehl aktiviert ist
	 * 
	 * @param Soll der Befehl aktiviert sein?
	 * @throws SQLException 
	 */
	public void setEnabled(boolean enabled) throws SQLException {
		this.enabled = enabled;
		MySQL.updateSQL("UPDATE `" + MySQL.COMMAND_TABLE + "` SET `enabled` = '" + (this.enabled? 1 : 0) + "' WHERE `name` = '" + super.getName() + "'");
	}
	
	/**
	 * Überprüft ob der {@link AnvilSubject} die nötigen Rechte für den Befehl hat
	 * 
	 * @param {@link AnvilSubject}
	 * 
	 * @return Hat der {@link AnvilSubject} die nötigen Rechte?
	 */
	public boolean hasCMDRights(AnvilSubject user) {
		return (user.beats(this.level) || user.hasPermission("anvil.command." + super.getName().toLowerCase()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AnvilCommand setAliases(List<String> aliases) {
		super.setAliases(aliases);
		
		JSONArray jsonAliases = new JSONArray();
		jsonAliases.addAll(super.getAliases());
		try {
			MySQL.updateSQL("UPDATE `" + MySQL.COMMAND_TABLE + "` SET `aliases` = '" + jsonAliases.toJSONString() + "' WHERE `name` = '" + super.getName() + "'");
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
		
		return this;
	}
	
	@Override
	public AnvilCommand setDescription(String description) {
		super.setDescription(description);
		
		try {
			MySQL.updateSQL("UPDATE `" + MySQL.COMMAND_TABLE + "` SET `description` = '" + super.getDescription() + "' WHERE `name` = '" + super.getName() + "'");
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
		
		return this;
	}
	
	@Override
	public AnvilCommand setUsage(String usage) {
		super.setUsage(usage);
		
		try {
			MySQL.updateSQL("UPDATE `" + MySQL.COMMAND_TABLE + "` SET `usage` = '" + this.getUsage() + "' WHERE `name` = '" + super.getName() + "'");
		} catch (SQLException e) {
			ConsoleHandler.sendMessage(_.mysql_sqlExeption, e.getLocalizedMessage());
		}
		
		return this;
	}
	
	/**
	 * Führt die Methode zum Befehl aus
	 * 
	 * @param {@link AnvilSender} der die Methode ausführen soll
	 * @param Verwendeter Alias
	 * @param Angegebene Argumente
	 */
	public abstract void execute(AnvilSender sender, String alias, String[] args);
	
	/**
	 * Führt die TabComplete-Methode zum Befehl aus
	 * 
	 * @param {@link AnvilSender} der die Methode ausführen soll
	 * @param Verwendeter Alias
	 * @param Angegebene Argumente
	 * 
	 * @return TabComplete-{@link List}e
	 */
	public abstract List<String> tabComplete(AnvilSender sender, String alias, String[] args);

	@Override
	public boolean execute(CommandSender cs, String alias, String[] args) {
		AnvilSubject user = SubjectManager.getSubject(cs);
		if (user != null && user instanceof AnvilSender)
			if (this.isEnabled())
				if (this.hasCMDRights(user))
					execute((AnvilSender) user, alias, args);
				else
					((AnvilSender) user).sendMessage(_.command_notEnoughRights, super.getName());
			else
				((AnvilSender) user).sendMessage(_.command_notEnabled, super.getName());
		else
			cs.sendMessage(_.format(_.subject_notLoadedCorrectly, cs.getName()));
		
		return true;
	}
	
	@Override
	public List<String> tabComplete(CommandSender cs, String alias, String[] args) {
		AnvilSubject user = SubjectManager.getSubject(cs);
		if (user != null && user instanceof AnvilSender)
			if (this.isEnabled())
				if (this.hasCMDRights(user))
					return tabComplete((AnvilSender) user, alias, args);
				else
					return Arrays.asList(ChatColor.stripColor(_.format(_.command_notEnoughRights, super.getName())));
			else
				return Arrays.asList(ChatColor.stripColor(_.format(_.command_notEnabled, super.getName())));
		else
			return Arrays.asList(ChatColor.stripColor(_.format(_.subject_notLoadedCorrectly, cs.getName())));
	}
}
