package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 * @modified by Dong Pei
 * @modified on June. 2017 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] sourceWords = sourceText.split("[\\s]+");
		starter = sourceWords[0];
		String prevWord = starter;
		
		// add new LinkList or add a word to currently exist LinkList
		for (int i=1; i<sourceWords.length; i++){
			String curWord = sourceWords[i];
			
			// using helper method to search for node first
			ListNode node = findWord(prevWord);
			if (node == null){
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(curWord);
				wordList.add(newNode);
			} else {
				node.addNextWord(curWord);
			}
			prevWord = curWord;
		}
		
		// add start word as the next word for the last word
		String lastWord = sourceWords[sourceWords.length-1];
		ListNode node = findWord(lastWord);
		if (node == null){
			ListNode newNode = new ListNode(prevWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		} else {
			node.addNextWord(starter);
		}
	}
	
	
	// This method searches wordList and return the node 
	// if the node contains a match word
	// Input: a search word
	// Output: the node that matches the word
	private ListNode findWord(String word){
		for (ListNode node : wordList){
			if (node.getWord().equals(word)){
				return node;
			} 
		}
		return null;
	}

	
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    String currWord = starter;
	    String output = "";
	    output += currWord;
		for (int i=0; i<numWords-1; i++){
			ListNode node = findWord(currWord);
			String randomWord = node.getRandomNextWord(rnGenerator);
			output = output + " " + randomWord;
			currWord = randomWord;
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	
	public String toString()
	{
		
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		
		train(sourceText);
	}
	
	
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String text = "hi there hi leo";
		System.out.println(text);
		gen.train(text);
		System.out.println(gen.toString());
		System.out.println(gen.generateText(12));
		
		
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    int n = generator.nextInt(nextWords.size());
		return nextWords.get(n);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


