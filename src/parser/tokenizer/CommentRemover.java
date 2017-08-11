/**
 * Written by Gideon Pfeffer
 * used to take the comments out of a String
 */

package parser.tokenizer;

public class CommentRemover {
	String str;

	public CommentRemover(String s) {
		str = s;
		removeComments();
	}

	/**
	 * removes the comments from the String by looking for "#" and end of line characters
	 */
	private void removeComments() {
		while (str.indexOf('#') >= 0) {
			int comIndex = str.indexOf('#');
			if (str.indexOf("\n", comIndex) >= 0) {
				int newLineIndex = str.indexOf("\n", comIndex);
				String start = str.substring(0, comIndex);
				str = start + str.substring(newLineIndex + 1);
			}
			else{
				str = str.substring(0, comIndex);
			}
		}
	}

	/**
	 * @return the String from which the comments have been removed
	 */
	public String getString() {
		return str;
	}

}
