package anvil.Minefabser.API.display;

import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.minecraft.server.v1_7_R2.ChatSerializer;
import net.minecraft.server.v1_7_R2.PacketPlayOutChat;

/**
 * Repräsentiert einen /tellraw-Text
 */
public class RawText {
	
	private JSONObject json = new JSONObject();
	
	/**
	 * Erstellt einen neuen RawText
	 */
	public RawText() {
		this("");
	}
	
	/**
	 * Erstellt einen neuen RawText mit Text
	 * @param Text
	 */
	@SuppressWarnings("unchecked")
	public RawText(String text) {
		json.put("text", text);
	}
	
	/**
	 * Fügt ein RawExtra zum RawText hinzu
	 * @param Hinzuzufügendes RawExtra
	 */
	@SuppressWarnings("unchecked")
	public void addExtras(RawExtra... extras) {
		if (!this.json.containsKey("extra"))
			this.json.put("extra", new JSONArray());
		
		JSONArray extraArray = (JSONArray) this.json.get("extra");
		for (RawExtra extra : extras)
			extraArray.add(extra.toJSON());
		this.json.put("extra", extraArray);
	}
	
	/**
	 * Gibt das JSON-Objekt zum RawText zurück
	 * @return JSONObject zum RawText
	 */
	public JSONObject toJSON() {
		return this.json;
	}
	
	/**
	 * Sendet den RawText an einen Spieler
	 * @param Der Spieler dem der RawText gesendet werden soll
	 */
	public void sendPlayer(Player p) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(this.json.toJSONString())));
	}

}
