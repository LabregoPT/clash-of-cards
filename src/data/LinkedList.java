package data;

import java.util.Comparator;
import model.*;

/**
 * @author Jhon Edward Mora / Juan Andres Orozco - Universidad ICESI
 * @version 0.1a - 05/2019
 */
public class LinkedList{

	//Nested classes
	/**This class handles each attendant as a node in a linked list. Does not modify the data on any of them.*/
	private class Element extends Card{
		
		//Relations to handle each attendant as nodes in a linked list.
		/**Next node of the linked list.*/
		private Element next;
		/**Previous node of the linked list.*/
		private Element prev;
		
		//Methods
		/**Simple constructor.
		 * @param e the Attendant represented by this node.
		 */
		public Element(Card e) {
			super(e.getName(), e.image, e.getAttributes());
		}
		
		//Below methods are only to handle each attendant as a node in a linked list.
		/**
		 * Returns the next node to this node of the list.
		 * @return the next node to this node of the list.
		 */
		public Element getNext() {
			return next;
		}
		
		/**
		 * Returns the previous node to this node of the list.
		 * @return the previous node to this node of the list.
		 */
		public Element getPrev() {
			return prev;
		}
		
		/**
		 * Sets the next node of this node to the one in the argument.
		 * @param e the node to be set as next node to this node.
		 */
		public void setNext(Element e) {
			next = e;
		}
		
		/**
		 * Sets the previous node of this node to the one in the argument.
		 * @param e the node to be set as previous node to this node. 
		 */
		public void setPrev(Element e) {
			prev = e;
		}
		
		/**
		 * Creates an unlinked node identical to this element of the list.
		 */
		public Element clone() {
			return new Element(this);
		}
	}

	//Relations
	/**First element of the list, also known as the list's head*/
	private Element first;
	
	//Methods
	/**
	 * Adds an Attendant to the list in the list's tail (last element) and links it with its previous and next element.
	 */
	public void add(Card card) {
		Element e = new Element(card);
		if(first==null) {
			first = e;
		}else {
			Element last = first;
			while(last.getNext() != null) {
				last = last.getNext();
				}
			e.setPrev(last);
			last.setNext(e);
			}
	}

	/**
	 * True if the list's head is null, false if not.
	 */
	public boolean isEmpty() {
		boolean empty = first==null;
		return empty;
	}
	
	/**
	 * Returns the number of elements that this list currently has, defined as the number of times an Attendant can perform the getNext method until the next element is null.
	 */
	public int size() {
		int totalElements = 0;
		Element current = first;
		if(current!=null) {
			totalElements=1;
			while(current.getNext() != null) {
				current = current.getNext();
				totalElements++;
			}
		}
		return totalElements;
	}
	
	/**
	 * Returns the Attendant located at the given index.
	 */
	public Card get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Tried to get index " + index + " when size is " + size());
		}
		Element found = first;
		int currentIndex = 0;
		while(currentIndex < index) {
			currentIndex++;
			found  = found.getNext();
		}
		return found.clone();
	}

	/**
	 * Sets the Attendant located in the given index to the one in the parameter, and updates the next and prev fields of the next and previous elements of the replaced element.
	 */
	public void set(int index, Card element) throws IndexOutOfBoundsException {
		Element el = new Element(element);
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Tried to set " + element + " in index " + index+ "when size is " + size());
		}
		if(index == 0) {
			if(first == null) {
				first = el;
			}else {
				Element next = first.getNext();
				next.setPrev(el);
				el.setNext(next);
				first = el;
			}
		}else {
			Element found = first;
			int currentIndex = 0;
			while(currentIndex < index) {
				currentIndex++;
				found  = found.getNext();
			}
			if(found.getNext() != null) {
				Element prev = found.getPrev();
				Element next = found.getNext();
				prev.setNext(el);
				next.setPrev(el);
				el.setNext(next);
				el.setPrev(prev);
			}else {
				Element prev = found.getPrev();
				el.setPrev(prev);
				prev.setNext(el);
			}
		}
	}

	
	/**
	 * Starts recursion of the sorting methods. Most of the code taken from geeksforgeeks.com
	 * @see https://www.geeksforgeeks.org/merge-sort/
	 */
	public void sort(Comparator<Card> c) {
		sort(this, 0, this.size()-1,c);
	}

	/**Sorts the elements of the list.*/
	private void sort(LinkedList list, int init, int last, Comparator<Card> c) {
		if(init < last) {
			int mid = (init+last)/2;
			sort(list, init, mid,c);
			sort(list, mid+1, last,c);
			merge(list, init, mid, last,c);
		}
	}

	/**Merges two sublists into one that, but now sorted.*/
	private void merge(LinkedList list, int init, int mid, int last, Comparator<Card> c) {
		//Sizes
		int size1 = mid-init+1;
		int size2 = last-mid;
		//Sublists, halves
		LinkedList sub1 = new LinkedList();
		LinkedList sub2 = new LinkedList();
		//Copy data
		for(int i = 0; i<size1; i++) {
			sub1.add(list.get(i));
		}
		for(int j = 0; j<size2; j++) {
			sub2.add(list.get(j));
		}
		
		//Merge halves
		int i = 0, j = 0, k = init;
		while(i<size1 && j < size2) {
			if(c.compare(sub1.get(i),sub2.get(j)) <=0) {
				list.set(k, sub1.get(i));
				i++;
			}else {
				list.set(k, sub2.get(j));
				j++;
			}
		}
		//Add remaining elements
		while(i<size1) {
			list.set(k, sub1.get(i));
			i++;k++;
		}
		while(j<size2) {
			list.set(k, sub2.get(j));
			j++;k++;
		}
	}

	/**
	 * Returns the first element of this list.
	 */
	public Card getFirst() {
		return first;
	}
	
	/**
	 * Returns the list's tail, AKA last element
	 */
	public Card getLast() {
		Element ret = first;
		while(ret!=null && ret.getNext()!= null) {
			ret = ret.getNext();
		}
		return ret;
	}
	
	public void delete(Card c) {
		Element nc = new Element(c);
		Element curr = first;
		while(nc != curr) {
			nc = nc.getNext();
		}
		if(nc == first) {
			first = nc.getNext();
			nc.getNext().setPrev(null);
		}else if(nc == getLast()) {
			nc.getPrev().setNext(null);
		}else {
			Element prev = nc.getPrev();
			Element next = nc.getNext();
			prev.setNext(next);
			next.setPrev(prev);
		}
	}
}
