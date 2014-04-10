package anvil.Minefabser.API.display;

import org.json.simple.JSONObject;

public class RawExtra {
	
	private JSONObject json = new JSONObject();
	
	/**
	 * Erstellt ein neues RawExtra mit Text
	 * @param Text
	 */
	@SuppressWarnings("unchecked")
	public RawExtra(String text) {
		this.json.put("text", text);
	}
	
	/**
	 * Fügt die Aktion beim Klicken (Clickable) zum Extra hinzu
	 * @param Clickable - Was soll beim Klicken passieren?
	 */
	@SuppressWarnings("unchecked")
	public void setClickable(Clickable clickable) {
		JSONObject clickEvent = new JSONObject();
		clickEvent.put("value", clickable.getValue());
		clickEvent.put("action", clickable.getRawName());
		this.json.put("clickEvent", clickEvent);
	}
	
	/**
	 * Fügt die Aktion beim Drüberfahren (Hoverable) zum Extra hinzu
	 * @param Hoverable - Was soll beim Drüberfahren passieren?
	 */
	@SuppressWarnings("unchecked")
	public void setHoverable(Hoverable hoverable) {
		JSONObject hoverEvent = new JSONObject();
		hoverEvent.put("value", hoverable.getValue());
		hoverEvent.put("action", hoverable.getRawName());
		this.json.put("hoverEvent", hoverEvent);
	}
	
	/**
	 * Gibt das JSON-Objekt des Extra zurück
	 * @return JSON-Objekt
	 */
	public JSONObject toJSON() {
		return this.json;
	}

}
