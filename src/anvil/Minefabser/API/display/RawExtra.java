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
	 * F�gt die Aktion beim Klicken (Clickable) zum Extra hinzu
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
	 * F�gt die Aktion beim Dr�berfahren (Hoverable) zum Extra hinzu
	 * @param Hoverable - Was soll beim Dr�berfahren passieren?
	 */
	@SuppressWarnings("unchecked")
	public void setHoverable(Hoverable hoverable) {
		JSONObject hoverEvent = new JSONObject();
		hoverEvent.put("value", hoverable.getValue());
		hoverEvent.put("action", hoverable.getRawName());
		this.json.put("hoverEvent", hoverEvent);
	}
	
	/**
	 * Gibt das JSON-Objekt des Extra zur�ck
	 * @return JSON-Objekt
	 */
	public JSONObject toJSON() {
		return this.json;
	}

}
