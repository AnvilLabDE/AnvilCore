package anvil.Minefabser.API.display;

import net.minecraft.server.v1_7_R2.ChatClickable;

/**
 * Repr�sentiert ein ChatClickable (NMS)
 */
public class Clickable extends ChatClickable {
	
	private ClickAction	clickAction;
	private String		value;
	
	/**
	 * Erstellt ein neues Clickable-Objekt
	 * @param Die ClickAction, die ausgef�hrt werden soll
	 * @param Der Wert, der angezeigt werden soll
	 */
	public Clickable(ClickAction clickAction, String value) {
		super(clickAction.getEnumClickAction(), value);
		
		this.clickAction	= clickAction;
		this.value			= value;
	}
	
	/**
	 * Gibt die ClickAction zum Clickable zur�ck
	 * @return ClickAction
	 */
	public ClickAction getClickAction() {
		return this.clickAction;
	}
	
	/**
	 * Gibt den Wert zum Clickable zur�ck
	 * @return Wert (Text)
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Gibt den Namen der zugeh�rigen EnumClickAction (NMS) zur�ck
	 * @return Name der zugeh�rigen EnumClickAction (NMS)
	 */
	public String getRawName() {
		return this.clickAction.getEnumClickAction().name().toLowerCase();
	}

}
