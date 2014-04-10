package anvil.Minefabser.API.interfaces;

public interface IMessageable {
	
	/**
	 * Sendet dem Objekt eine Nachricht
	 * @param Nachricht
	 * @param Eventuell zu erg�nzende Argumente
	 */
	public void sendMessage(String message, Object... args);
	
	/**
	 * Sendet vom Objekt eine Nachricht an den PlusChat
	 * @param Level ({@link Integer}
	 * @param Nachricht
	 * @param Eventuell zu erg�nzende Argumente
	 */
	public void sendPlusChat(int level, String message, Object... args);
	
}
