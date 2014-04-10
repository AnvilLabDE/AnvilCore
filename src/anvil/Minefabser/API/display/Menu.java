package anvil.Minefabser.API.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import anvil.Minefabser.API.base.AnvilPlayer;
import anvil.Minefabser.API.base.AnvilSender;
import anvil.Minefabser.API.base.AnvilSubject;
import anvil.Minefabser.API.handler._;

/**
 * Erzeugt ein (interaktives) Text-Men�
 */
public class Menu {
	
	private String			topic;
	private List<Option>	options	= new ArrayList<>();
	
	/**
	 * Erstellt ein neues Men� mit einem Thema
	 * @param Thema des Men�s
	 */
	public Menu(String topic) {
		this.topic = topic;
	}
	
	/**
	 * Gibt das Thema des Men�s zur�ck
	 * @return Thema des Men�s
	 */
	public String getTopic() {
		return this.topic;
	}
	
	/**
	 * Gibt die Optionen des Men� zur�ck
	 * @return Liste der Optionen
	 */
	public List<Option> getOptions() {
		return this.options;
	}
	
	/**
	 * F�gt eine oder mehrere Optionen zum Men� hinzu
	 * @param Eine oder mehrere {@link Option}en
	 */
	public void addOptions(Option... options) {
		this.options.addAll(Arrays.asList(options));
	}
	
	/**
	 * Zeigt das Menu einem {@link AnvilSubject}
	 * @param AnvilUser, dem das Men� gezeigt werden soll
	 */
	public void showMenu(AnvilSender sender) {
		if (this.topic != null)
			sender.sendMessage(_.menu_format_topic, this.topic);
		
		for (Option opt : this.options) {
			sender.sendMessage(_.menu_format_option, opt.getName());
			
			for (Value val : opt.getValues())
				if (sender instanceof AnvilPlayer) {
					RawText		raw		= new RawText();
					RawExtra	extra	= new RawExtra(_.format(_.menu_format_value_withoutDescr, val.getDisplay()));
					
					if (val.getDescription() != null)
						extra.setHoverable(new Hoverable(HoverAction.SHOW_TEXT, val.getDescription()));
					if (val.getClickable() != null)
						extra.setClickable(val.getClickable());
					
					raw.addExtras(extra);
					raw.sendPlayer(((AnvilPlayer) sender).getPlayer());
				} else {
					if (val.getDescription() != null)
						sender.sendMessage(_.menu_format_value_withDescr, val.getDisplay(), val.getDescription());
					else
						sender.sendMessage(_.menu_format_value_withoutDescr, val.getDisplay());
				}
		}
	}

}
