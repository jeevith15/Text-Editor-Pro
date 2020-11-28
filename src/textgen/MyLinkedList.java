package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @Modified Dong Pei
 * @Modified on: May. 2017
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList, head node and tail node does not have values */
	public MyLinkedList() {			
		head = new LLNode<E>();
		tail = new LLNode<E>();
		size = 0;
		// head.prev=null, tail.next=null
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null){
			throw new NullPointerException("invalid input");
		} else {
			// change next and prev in neighborhood nodes.
			LLNode<E> node = new LLNode<E>(element);
			node.next = tail;
			node.prev = tail.prev;
			tail.prev = node;
			node.prev.next = node;
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index > size-1 || index < 0){
			throw new IndexOutOfBoundsException("invalid index number!");
		} else {
			LLNode<E> node = head;
			for (int i=0; i<=index; i++){
				node = node.next;
			}
			
			// extract data from the chosen node
			return node.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		
		if (index > size-1){
			throw new IndexOutOfBoundsException("invalid index number!");
		} else if (element == null){
			throw new NullPointerException("invalid input");
		} else {
			LLNode<E> node = new LLNode<E>(element);
			
			// based on index, find the nextNode to be inserted 
			LLNode<E> nextNode = head;
			for (int i=0; i<=index; i++){
				nextNode = nextNode.next;
			}
			
			// update next and prev in each node
			node.next = nextNode;
			node.prev = nextNode.prev;
			nextNode.prev = node;
			node.prev.next = node;
			size++;
			
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index > size-1){
			throw new IndexOutOfBoundsException("invalid index number!");
		} else {
			LLNode<E> node = head;
			for (int i=0; i<=index; i++){
				node = node.next;
			}
			
			// extract data from the chosen node
			E removedData = node.data;
			
			// update prev and next in neighbourhood nodes
			node.next.prev = node.prev;
			node.prev.next = node.next;
			size = size - 1;
			
			return removedData;
		}
		
		
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index > size-1){
			throw new IndexOutOfBoundsException("invalid index number!");
		} else if (element == null){
			throw new NullPointerException("invalid input");
		} else {
			
			LLNode<E> node = head;
			for (int i=0; i<=index; i++){
				node = node.next;
			}
			
			// extract data from the chosen node
			E replaceData = node.data;
			node.data = element;
			return replaceData;
		}
		
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// This constructor is useful for initiate head 
	// and tail node
	public LLNode() 
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
