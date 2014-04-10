package anvil.Minefabser.API.base;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import anvil.Minefabser.API.handler.MySQL;
import anvil.Minefabser.API.interfaces.IDisplayable;
import anvil.Minefabser.API.interfaces.IRightHolder;

public class AnvilSubject implements IDisplayable, IRightHolder {
	
	private String			identifier	= String.valueOf(System.currentTimeMillis());
	
	private String			displayName	= this.identifier;
	private String			prefix		= "";
	private ChatColor		color		= ChatColor.RESET;
	
	private int				level		= Integer.MIN_VALUE;
	private List<String>	permissions	= new ArrayList<>();
	
	private JSONObject		extraData	= new JSONObject();
	
	/**
	 * Erstellt/Lädt einen neuen AnvilUser mithilfe seiner UniqueID ({@link UUID})
	 * 
	 * @param UniqueID - {@link UUID}
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public AnvilSubject(String identifier) throws SQLException, ParseException {
		this.identifier		= identifier;
		this.displayName	= identifier;
		
		if (!SubjectManager.existsInMySQLDatabase(this.identifier)) {
			JSONArray jsonPerm = new JSONArray();
			jsonPerm.addAll(this.permissions);
			
			MySQL.updateSQL("INSERT INTO `" + MySQL.SUBJECT_TABLE + "` " +
							"(`identifier`, `displayName`, `prefix`, `color`, `level`, `permissions`, `extraData`) VALUES " +
							"('" + this.identifier + "', '" + this.displayName + "', '" + this.prefix + "', '" + this.color.name() + "', " +
							"'" + this.level + "', '" + jsonPerm.toJSONString() + "', '" + this.extraData.toJSONString() + "')");
		} else {
			ResultSet resultSet = MySQL.querySQL("SELECT * FROM `" + MySQL.SUBJECT_TABLE + "` WHERE `identifier` = '" + identifier + "'");
			
			while (resultSet.next()) {
				this.displayName	= resultSet.getString("displayName");
				this.prefix			= resultSet.getString("prefix");
				this.color			= ChatColor.valueOf(resultSet.getString("color"));
				
				this.level			= resultSet.getInt("level");
				this.permissions	= (JSONArray) new JSONParser().parse(resultSet.getString("permissions"));
				
				this.extraData		= (JSONObject) new JSONParser().parse(resultSet.getString("extraData"));
			}
		}
	}
	
	/**
	 * Gibt den Identifier ({@link String}) des AnvilSubject zurück
	 * 
	 * @return Identifier - {@link String}
	 */
	public String getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * Gibt die ExtraData ({@link JSONObject}) des AnvilSubject zurück
	 * 
	 * @return ExtraData - {@link JSONObject}
	 */
	public JSONObject getExtraData() {
		return this.extraData;
	}
	
	@Override
	public String getName() {
		return ChatColor.stripColor(this.displayName);
	}
	
	@Override
	public String getDisplayName() {
		return this.displayName;
	}
	
	@Override
	public void setDisplayName(String displayName) throws SQLException {
		this.displayName = displayName;
		MySQL.updateSQL("UPDATE `" + MySQL.SUBJECT_TABLE + "` SET `displayName` = '" + this.displayName + "' WHERE `identifier` = '" + this.identifier + "'");
	}

	@Override
	public String getPrefix() {
		return this.prefix;
	}

	@Override
	public void setPrefix(String prefix) throws SQLException {
		this.prefix = prefix;
		MySQL.updateSQL("UPDATE `" + MySQL.SUBJECT_TABLE + "` SET `prefix` = '" + this.prefix + "' WHERE `identifier` = '" + this.identifier + "'");
	}

	@Override
	public ChatColor getColor() {
		return this.color;
	}

	@Override
	public void setColor(ChatColor color) throws SQLException {
		this.color = color;
		MySQL.updateSQL("UPDATE `" + MySQL.SUBJECT_TABLE + "` SET `color` = '" + this.color.name() + "' WHERE `identifier` = '" + this.identifier + "'");
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void setLevel(int level) throws SQLException {
		this.level = level;
		MySQL.updateSQL("UPDATE `" + MySQL.SUBJECT_TABLE + "` SET `level` = '" + this.level + "' WHERE `identifier` = '" + this.identifier + "'");
	}
	
	@Override
	public boolean beats(int level) {
		return (this.level >= level);
	}

	@Override
	public boolean beats(IRightHolder rightHolder) {
		return beats(rightHolder.getLevel());
	}

	@Override
	public void givePermission(String permission) {
		// TODO this.sender.addAttachment(AnvilMain.getPlugin(), permission, true);
	}

	@Override
	public void removePermission(String permission) {
		/// TODO this.sender.addAttachment(AnvilMain.getPlugin(), permission, false);
	}

	@Override
	public boolean hasPermission(String permission) {
		//TODO return this.sender.hasPermission(permission);
		return false;
	}

	@Override
	public List<String> getPermissions() {
		return this.permissions;
	}
	

}
