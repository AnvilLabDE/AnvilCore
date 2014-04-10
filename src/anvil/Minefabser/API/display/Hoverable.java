package anvil.Minefabser.API.display;

import net.minecraft.server.v1_7_R2.ChatComponentText;
import net.minecraft.server.v1_7_R2.ChatHoverable;

/**
 * Repr�sentiert ein ChatHoverable (NMS)
 */
public class Hoverable extends ChatHoverable {
	
	private HoverAction	hoverAction;
	private String		value;
	
	/**
	 * Erstellt ein neues Hoverable-Objekt
	 * @param Die HoverAction, die ausgef�hrt werden soll
	 * @param Der Wert, der angezeigt werden soll
	 */
	public Hoverable(HoverAction hoverAction, String value) {
		super(hoverAction.getEnumHoverAction(), new ChatComponentText(value));
		
		this.hoverAction 	= hoverAction;
		this.value			= value;
	}
	
	/**
	 * Gibt die HoverAction zum Hoverable zur�ck
	 * @return HoverAction
	 */
	public HoverAction getHoverAction() {
		return this.hoverAction;
	}
	
	/**
	 * Gibt den Wert zum Hoverable zur�ck
	 * @return Wert (Text)
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Gibt den Namen der zugeh�rigen EnumHoverAction (NMS) zur�ck
	 * @return Name der zugeh�rigen EnumHoverAction (NMS)
	 */
	public String getRawName() {
		return this.hoverAction.getEnumHoverAction().name().toLowerCase();
	}

}
