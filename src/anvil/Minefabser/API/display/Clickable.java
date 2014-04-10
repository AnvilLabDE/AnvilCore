package anvil.Minefabser.API.display;

import net.minecraft.server.v1_7_R2.ChatClickable;

/**
 * Repräsentiert ein ChatClickable (NMS)
 */
public class Clickable extends ChatClickable {
	
	private ClickAction	clickAction;
	private String		value;
	
	/**
	 * Erstellt ein neues Clickable-Objekt
	 * @param Die ClickAction, die ausgeführt werden soll
	 * @param Der Wert, der angezeigt werden soll
	 */
	public Clickable(ClickAction clickAction, String value) {
		super(clickAction.getEnumClickAction(), value);
		
		this.clickAction	= clickAction;
		this.value			= value;
	}
	
	/**
	 * Gibt die ClickAction zum Clickable zurück
	 * @return ClickAction
	 */
	public ClickAction getClickAction() {
		return this.clickAction;
	}
	
	/**
	 * Gibt den Wert zum Clickable zurück
	 * @return Wert (Text)
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Gibt den Namen der zugehörigen EnumClickAction (NMS) zurück
	 * @return Name der zugehörigen EnumClickAction (NMS)
	 */
	public String getRawName() {
		return this.clickAction.getEnumClickAction().name().toLowerCase();
	}

}
