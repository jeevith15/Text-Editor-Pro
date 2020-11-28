package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 * @modified by Dong Pei
 * @modified on June 2017
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    
	public DictionaryLL()
	{
		dict = new LinkedList<String>();
	}
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String lowerWord = word.toLowerCase();    	
    	if (isWord(lowerWord)){
    		return false;
    	} else {
    		dict.add(lowerWord);
            return true;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String lowerS = s.toLowerCase();
    	for (String word : dict){
        	if (word.equals(lowerS)){
        		return true;
        	}
        }
        return false;
    }
    
    
}
