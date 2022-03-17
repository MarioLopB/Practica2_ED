package ule.ed.listwithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

//import com.sun.source.tree.BreakTree;
import ule.ed.exceptions.EmptyCollectionException;

public class ArrayListWithRepImpl<T> implements ListWithRep<T> {

	// atributos

	private final int capacityDefault = 5;

	ElemListWithRep<T>[] data;
	private int count;

	// Clase interna
	@SuppressWarnings("hiding")
	public class ElemListWithRep<T> {
		T elem;
		int num;
		public ElemListWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}


	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayListWithRepIterator<T> implements Iterator<T> {

		private int count;
		private int current;
		private ElemListWithRep<T>[] items;

		public ArrayListWithRepIterator(ElemListWithRep<T>[] cola, int count){

			items = cola;
			this.count = count;
			current = 0;

		}

		@Override
		public boolean hasNext() {
			//TODO
			return (current < this.count);


		}

		@Override
		public T next() {
			//TODO
			if(!hasNext())
				throw new NoSuchElementException();
			this.current++;

			return this.items[this.current - 1].elem;
		}



	}
	////// FIN ITERATOR

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayListWithRepIteratorRep<T> implements Iterator<T> {

		private int count;
		private int current;
		private ElemListWithRep<T>[] items;
		private int counter;


		public ArrayListWithRepIteratorRep(ElemListWithRep<T>[] cola, int count){

			items = cola;
			this.count = count;
			current = 1;
			counter = 0;

		}

		@Override
		public boolean hasNext() {
			//TODO
			return (this.current < this.count);


		}

		@Override
		public T next() {
			//TODO
			if(!hasNext())
				throw new NoSuchElementException();

			if(this.counter < this.items[this.current-1].num){
				this.counter++;
			} else{
				this.current++;
			}

			return this.items[this.current-1].elem;
		}



	}
	////// FIN ITERATOR

	// Constructores

	@SuppressWarnings("unchecked")
	public ArrayListWithRepImpl() {
		data =   new ElemListWithRep[capacityDefault];
		count=0;
	}

	@SuppressWarnings("unchecked")
	public ArrayListWithRepImpl(int capacity) {
		data =  new ElemListWithRep[capacity];
		count=0;
	}


	private int search(T element){
		int place = -1;

		for(int index = 0; index < this.count; index++){
			if(this.data[index].elem.equals(element)){
				place = index;
				break;
			}
		}

		return place;
	}

	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		//TODO
		ElemListWithRep<T>[] nuevo= new ElemListWithRep[data.length*2];

		for(int i = 0; i < data.length; i++){
			nuevo[i] = data[i];
		}

		data = nuevo;
		// todo
	}


	@Override
	public void add(T element, int times) {
		// TODO
		if(element == null)
			throw new NullPointerException();
		if(times < 0)
			throw new IllegalArgumentException();

		int rep = 0;

		if(times != 0){
			if(!(contains(element))){
				if(this.count == this.data.length)
					expandCapacity();
				this.data[this.count] = new ElemListWithRep<T>(element, times);
				this.count++;
			} else{
				this.data[this.search(element)].num += times;
			}
		}


	}


	@Override
	public void add(T element) {
		// TODO
		if(element == null)
			throw new NullPointerException();

		if(!(contains(element))){
			if(this.count == data.length)
				expandCapacity();
			this.data[this.count] = new ElemListWithRep<T>(element, 1);
			this.count++;
		} else{
			this.data[this.search(element)].num += 1;
		}
	}

	@Override
	public int remove(T element, int times) throws EmptyCollectionException{
		// TODO
		int removed = 0;

		if(element == null){
			throw new NullPointerException();
		} else if(times < 0){
			throw  new IllegalArgumentException();
		} else if(times == 0){
			return 0;
		} else if(this.isEmpty()){
			throw new EmptyCollectionException("Lista vacia");
		} else if(this.search(element) < 0){
			throw new NoSuchElementException();
		}

		if(times < this.data[this.search(element)].num){
			removed = times;
			this.data[this.search(element)].num = this.data[this.search(element)].num - times;
		} else if(times == this.data[this.search(element)].num){
			removed = times;
			this.data[this.search(element)] = data[this.count-1];
			this.data[this.count-1] = null;
			count--;
		} else {
			removed = this.data[this.search(element)].num;
			this.data[this.search(element)] = data[this.count-1];
			this.data[this.count-1] = null;
			count--;
		}

		return removed;

	}

	@Override
	public int remove() throws EmptyCollectionException {
		// TODO
		if(this.isEmpty()){
			throw new EmptyCollectionException("Lista vacia");
		}

		int  removed = this.data[0].num;
		this.data[0] = this.data[1];
		this.data[1] = this.data[count-1];
		this.data[count-1] = null;
		this.count--;

		return removed;

	}

	@Override
	public void clear() {
		// TODO
		for(int i = 0; i < this.count; i++){
			this.data[i] = null;

		}

		this.count = 0;

	}


	@Override
	public boolean contains(T element) {
		// TODO
		if(element == null)
			throw new NullPointerException();

		int index = 0;
		boolean exist = false;

		while ((index < this.count) && !exist){
			if(data[index].elem.equals(element)){
				exist = true;
			}
			index++;
		}

		return exist;

	}

	@Override
	public boolean isEmpty() {
		// TODO
		boolean exist = false;

		if(this.count == 0){
			exist = true;
		}
		return exist;
	}

	@Override
	public long size() {
		// TODO
		int instances = 0;

		for(int i = 0; i < this.count; i++){
			instances = instances + data[i].num;
		}

		return instances;
	}

	@Override
	public int count(T element) {
		// TODO

		if(element == null){
			throw new NullPointerException();
		}

		int instances = 0;
		int index = 0;
		boolean equal = false;

		if(this.contains(element)){
			while (!equal){
				if(data[index].elem.equals(element)){
					instances = data[index].num;
					equal = true;
				}
				index++;
			}
		}


		return instances;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO
		ArrayListWithRepIterator<T> iterator = new ArrayListWithRepIterator<T>(this.data, this.count);
		return iterator;
	}

	@Override
	public Iterator<T> iteratorRep() {
		// TODO
		ArrayListWithRepIteratorRep<T> iterator = new ArrayListWithRepIteratorRep<>(this.data, this.count);
		return iterator;
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A A A B )
		for(int index = 0; index < this.count; index++){
			for(int i = 0; i < this.data[index].num; i++){
				buffer.append(this.data[index].elem + " ");
			}
		}
		// Se concatena cada elemento seguido por un espacio

		buffer.append(")");

		return buffer.toString();
	}



}