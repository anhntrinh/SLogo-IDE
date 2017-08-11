/**
 * Written by Gideon Pfeffer
 * Handles the doTimes command
 */

package parser.control_structures;

import java.util.Map;

import parser.helpers.doTimesRange;

public class DoTimesHandler extends AbstractIteratingControls{
	private static final String INDICATOR = "DoTimes";

	/**
	 * @param varMap the variable Map of existing variables to be used in compiling if needed
	 * sets the rangeHandler to be a doTimesRange
	 */
	public DoTimesHandler(Map<String, Double> varMap) {
		super(INDICATOR, varMap);
		range = new doTimesRange();
	}
}
