package anvil.Minefabser.API.base;

import java.sql.SQLException;

import org.json.simple.parser.ParseException;

public class AnvilGroup extends AnvilSubject {

	public AnvilGroup(String name) throws SQLException, ParseException {
		super(name);
	}

}
