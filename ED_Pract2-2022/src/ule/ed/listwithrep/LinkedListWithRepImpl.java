package ule.ed.listwithrep;

import java.beans.PropertyEditorSupport;
import java.util.Iterator;
import java.util.NoSuchElementException;

import jdk.jshell.spi.SPIResolutionException;
import ule.ed.exceptions.EmptyCollectionException;

import javax.swing.*;


public class LinkedListWithRepImpl<T> implements ListWithRep<T> {

	// Atributos
	private ListWithRepNode<T> front;
	int count;


	// Clase interna
	@SuppressWarnings("hiding")
	public class ListWithRepNode<T> {
		T elem;
		int num;
		ListWithRepNode<T> next;

		public ListWithRepNode(T elem, int num) {
			this.elem = elem;
			this.num = num;
		}

	}

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedListWithRepIterator<T> implements Iterator<T> {

		private int count;
		private ListWithRepNode<T> current;

		public LinkedListWithRepIterator(ListWithRepNode<T> collection, int size) {
			this.current = collection;
			this.count = size;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return (current != null);
		}

		@Override
		public T next() throws NoSuchElementException {
			//TODO

			if (!hasNext())
				throw new NoSuchElementException();

			T result = this.current.elem;
			this.current = current.next;
			return result;
		}

	}
	////// FIN ITERATOR


	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedListWithRepIteratorRep<T> implements Iterator<T> {

		private int count;
		private int counter;
		private ListWithRepNode<T> current;

		public LinkedListWithRepIteratorRep(ListWithRepNode<T> collection, int size) {
			this.current = collection;
			this.count = size;
			this.counter = 0;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return (current != null);
		}

		@Override
		public T next() {
			//TODO
			if (!hasNext())
				throw new NoSuchElementException();

			T result = null;

			if (this.counter < this.current.num-1) {
				result = this.current.elem;
				counter++;
			} else {
				result = this.current.elem;
				this.current = current.next;
			}

			return result;
		}
	}
	////// FIN ITERATOR/////////////


	public ListWithRepNode<T> search(T element){
		ListWithRepNode<T> current = this.front;
		boolean exist = false;

		while((current != null) && !exist){
			if(current.elem == element){
				exist = true;
			}else {
				current = current.next;
			}
		}

		return current;
	}

	@Override
	public void add(T element) {
		//TODO
		if(element == null)
			throw new NullPointerException();

		if(!(contains(element))){
			ListWithRepNode<T> node = new ListWithRepNode<T>(element, 1);
			ListWithRepNode<T> current = this.front;

			if(this.isEmpty()){
				this.front = node;

			} else{
				while((current != null) && (current.next != null)){
					current = current.next;
				}
				current.next = node;
			}

			this.count++;
		} else{
			this.search(element).num += 1;
		}
	}

	@Override
	public void add(T element, int times) {
		//TODO
		if(element == null)
			throw new NullPointerException();
		if(times < 0)
			throw new IllegalArgumentException();

		if(times != 0){
			if(!(contains(element))){
				ListWithRepNode<T> node = new ListWithRepNode<T>(element, times);
				ListWithRepNode<T> current = this.front;

				if(this.isEmpty()){
					this.front = node;

				} else{
					while((current != null) && (current.next != null)){
						current = current.next;
					}
					current.next = node;
				}

				this.count++;
			} else{
				this.search(element).num += times;
			}
		}

	}

	@Override
	public int remove(T element, int times) throws EmptyCollectionException{
		//TODO
		boolean found = false;
		ListWithRepNode<T> previous, current;
		int result = 0;
		if (element == null)
			throw new NullPointerException();
		if(times < 0)
			throw new IllegalArgumentException();
		if(times == 0)
			return 0;
		if(this.isEmpty())
			throw new EmptyCollectionException("Lista vacia");
		if(this.search(element) == null)
			throw new NoSuchElementException();
		if (this.front.elem.equals(search(element).elem)){
			if(times < this.search(element).num){
				result = times;
				this.search(element).num = this.search(element).num - times;
			} else if(times >= this.search(element).num){
				result = front.num;
				this.front = front.next;
				this.count--;
			}
		} else{
			if(times < this.search(element).num){
				result = times;
				this.search(element).num = this.search(element).num - times;
			} else if(times >= this.search(element).num){
				previous = this.front;
				current = this.front.next;
				for(int index = 1; index < this.count; index++) {
					if (current.elem != this.search(element).elem) {
						previous = current;
						current = current.next;
					}
				}
				result = search(element).num;
				previous.next = current.next;
				this.count--;
			}
		}
		return result;

	}


	@Override
	public boolean contains(T element) {
		//TODO
		if(element == null)
			throw new NullPointerException();

		ListWithRepNode<T> current = this.front;

		int i = 1;
		boolean exist = false;

		while((current != null) && !exist){
			if(current.elem == element){
				exist = true;
			}
			i++;
			current = current.next;
		}

		return exist;
	}

	@Override
	public long size() {
		//TODO
		int instances = 0;
		ListWithRepNode<T> current = this.front;

		for(int i = 0; i < this.count; i++){
			instances += current.num;
			current = current.next;
		}

		return instances;

	}

	@Override
	public boolean isEmpty() {
		//TODO
		boolean exist = false;

		if(this.count == 0){
			exist = true;
		}
		return exist;
	}

	@Override
	public int remove() throws EmptyCollectionException {
		//TODO
		int removed = 0;
		if(isEmpty())
			throw new EmptyCollectionException("Lista vacia");

		if(this.front != null){
			removed = this.front.num;
			this.front = this.front.next;
		}
		return removed;
	}

	@Override
	public void clear() {
		//TODO
		ListWithRepNode<T> current = this.front;
		while(this.count != 0){
			current = current.next;
			this.count--;
		}
	}

	@Override
	public int count(T element) {
		//TODO
		if(element == null){
			throw new NullPointerException();
		}
		int instances = 0;

		if(this.contains(element))
			instances = this.search(element).num;

		return instances;
	}

	@Override
	public Iterator<T> iterator() {
		//TODO
		return new LinkedListWithRepIterator<T>(this.front, this.count);
	}

	@Override
	public Iterator<T> iteratorRep() {
		// TODO
		return new LinkedListWithRepIteratorRep<T>(this.front, this.count);
	}
	@Override
	public String toString() {
		//TODO
		StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A A A B )
		ListWithRepNode<T> current = this.front;

		for(int index = 0; index < this.count && current != null; index++){
			for(int i = 0; i < current.num; i++){
				buffer.append(current.elem + " ");
			}
			current = current.next;
		}
		// Se concatena cada elemento seguido por un espacio

		buffer.append(")");
		return buffer.toString();
	}








}