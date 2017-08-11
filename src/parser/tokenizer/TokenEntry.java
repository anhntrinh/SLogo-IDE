/**
 * Written by Gideon Pfeffer
 * A generic key value pairing to be used 
 * for various token generation tasks
 */

package parser.tokenizer;

public class TokenEntry <K,V>{
	
	private K key;
	private V value;
	
	public TokenEntry(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return the Key of the TokenEntry
	 */
	public K getKey(){
		return key;
	}
	
	/**
	 * @return the Value of the TokenEntry
	 */
	public V getValue(){
		return value;
	}

}
