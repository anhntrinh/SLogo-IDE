/**
 * Written by Gideon Pfeffer
 * Handles the for command
 */
package parser.control_structures;

import java.util.Map;
import parser.helpers.forRange;

public class ForHandler extends AbstractIteratingControls{
	private static final String INDICATOR = "For";

	/**
	 * @param varMap the variable Map of existing variables to be used in compiling if needed
	 * sets the rangeHandler to be a forRange
	 */
	public ForHandler(Map<String, Double> varMap) {
		super(INDICATOR, varMap);
		range = new forRange();
	}
}
