package ule.ed.listwithrep;


import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.ed.exceptions.EmptyCollectionException;
	
	/**
	 * TAD 'ListWithRep'
	 * 
	 * Almacena un conjunto de objetos de tipo <code>T</code>, pero cuando varios objetos iguales (según su  
	 * método {@link #equals(Object)}) se añaden a la colección, se mantiene una única referencia al objeto 
	 * y un contador de duplicados.
	 * 
	 * Ejemplos de aplicación de un TAD como éste podría ser llevar el
	 * inventario de un personaje en un juego:
	 * 
	 * 	("Cloth"(10), "Gold"(5), "Silver Key"(2))
	 * 
	 * o almacenar información sobre una caja registradora:
	 * 
	 * 	("5€"(2), "0.5€"(20), "20€"(1))
	 * 
	 * 
	 * Se deben considerar hasta 2^31 - 1 instancias de un mismo elemento,
	 * por lo que se usará un <code>int</code> para almacenar ese dato.
	 * 
	 * El tamaño total de la lista {@link ListWithRep#size()} será un resultado
	 * de tipo <code>long</code>.
	 * 
	 * En cualquier caso, para simplificar la práctica se supondrá que no es necesario hacer comprobaciones de 
	 * rangos. Si fuera necesario, se dispone de {@link Integer#MAX_VALUE} y {@link Long#MAX_VALUE}.
	 * 
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos <code>null</code>. Si a cualquier método que recibe un elemento se le pasa el 
	 * valor <code>null</code>, lanzará una excepción {@link NullPointerException}.
	 * 
	 * Los valores de parámetros <code>times</code> deben ser mayores o iguales a cero, nunca negativos. Si se 
	 * recibe un valor negativo se lanzará {@link IllegalArgumentException}.
	 * 
	 * Ambas son ejemplos de "unchecked exceptions"
	 * {@link http://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html}
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia
	 * como lista vacía.
	 *  
	 * Iteradores
	 * 
	 * Iterator
	 * 
	 * El iterador iterator se desplaza por todos los distintos elementos de la lista, sin tener en cuenta las repeticiones.
	 * 
	 * Por ejemplo, con una lista
	 * 
	 * 	("A"(2), "B"(5))
	 * 
	 * el método {@link Iterator#next()} devolverá uno tras otro
	 * 
	 * 	"A", "B"
	 * 
	 * IteradorRep
	 * 
	 * El iterador iteratorRep se desplaza por todos los elementos de la lista, uno a uno.
	 * 
	 * Por ejemplo, con una lista
	 * 
	 * 	("A"(2), "B"(5))
	 * 
	 * el método {@link Iterator#next()} devolverá uno tras otro
	 * 
	 * 	"A", "A", "B", "B", "B", "B", "B"
	 * 
	 * @author profesor
	 *
	 * @param <T> tipo de elementos en la lista
	 */
	public interface ListWithRep<T> extends Iterable<T> {

		/**
		 * Añade varias instancias de un elemento a esta lista (si no esta en la lista inserta el elemento como ultimo elemento)
		 * 
		 * Si una lista contiene ("XYZ"(1), "123"(5)), y se añaden seis instancias de "ABC", se pasará a 
	           * tener una lista con ("XYZ"(1), "123"(5), ABC"(6)). Si ahora se añaden dos instancias de 
	           * "123", se tendrá ("XYZ"(1), "123"(7), ABC"(8)).
		 * 
		 * @param element el elemento a añadir
		 * @param times el número de instancias
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 * @throws IllegalArgumentException si <code>times</code> fuera negativo
		 */
		public void add(T element, int times);
		
		/**
		 * Añade una instancia de un elemento a esta lista
		 *  ( si esta se incrementa el contador y si no esta se añade como ultimo)
		 * 
		 * @param element el elemento a añadir
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public void add(T element);
		
		
		/**
		 * Saca varias instancias de un elemento de esta lista
		 * 
		 * Por ejemplo, al sacar dos instancias de "ABC" de una
		 * lista ("ABC"(10), "123"(2)), ésta pasará a ser
		 * ("ABC"(8), "123"(2)).
		 * 
		 * Si el numero de instancias a eliminar es mayor que el numero de instancias que hay
		 * en la lista; e.g. si inicialmente se tiene ("ABC"(2))
		 * y se sacan 8 instancias de "ABC", elimina por completo el elemento, y devuelve el
		 * numero de instancias eliminadas
		 * 
		 * @param element elemento a sacar de esta lista
		 * @param times número de instancias a sacar
		 * 
	     * @throws NullPointerException el elemento indicado es <code>null</code>
         * @throws NoSuchElementException el elemento indicado  <code>no está en la lista</code>
      	 * @return el numero de instancias que realmente pudo eliminar
      	 * 
		 */
		public int remove(T element, int times) throws EmptyCollectionException;
		
		/**
		 * Saca el primer elemento completo de esta lista
		 * 
		 * 
		 * @throws EmptyCollectionException si la lista está vacía
		 * @return el numero de apariciones que había en la lista del primer elemento (que elimina)
		 */
		public int remove() throws EmptyCollectionException;
		
		
		/**
		 * Elimina todo el contenido de esta lista
		 */
		public void clear();
		
		
		/**
		 * Indica si el elemento está en esta lista
		 * 
		 * Devuelve <code>true</code> si al menos existe una
		 * instancia del elemento dado en esta lista (es decir,
		 * un elemento 'x' tal que <code>x.equals(element)</code>)
		 * y <code>false</code> en caso contrario.
		 * 
		 * @param element elemento a buscar en esta lista
		 * @return <code>true</code>/<code>false</code> según el resultado
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public boolean contains(T element);
		
		/**
		 * Indica si esta lista está vacía
		 * 
		 * @return <code>true</code> si no contiene elementos
		 */
		public boolean isEmpty();
		
		/**
		 * Devuelve el número total de instancias en esta lista
		 * 
		 * Por ejemplo, para una lista ("5€"(2), "10€"(8)) se 
		 * devolverá un tamaño de 2 + 8 = 10.
		 * 
		 * @return número total de instancias en esta lista
		 */
		public long size();

		/**
		 * Devuelve el número de instancias del elemento dado
		 * 
		 * Es decir, el número de instancias del objeto 'x' tal que
		 * <code>x.equals(element)</code>. Por ejemplo, con una lista
		 * B1 = (AX(2), BX(8)) se indicará 8 si se pregunta por
		 * <code>B1.count(BX)</code>.
		 * 
		 * Si el elemento no está, se devuelve cero.
		 * 
		 * @param element el elemento a buscar en esta lista
		 * @return el número de instancias que se encuentran
		 * 
		 * @throws NullPointerException el elemento indicado es <code>null</code>
		 */
		public int count(T element);
		
		/**
		 * Devuelve un iterador que se desplaza por todos los elementos de la lista, uno a uno.
		 * 
		 * Por ejemplo, con una lista:	("A"(2), "B"(5))
		 * 
		 * el método {@link Iterator#next()} devolverá uno tras otro
		 * 
		 * 	"A", "A", "B", "B", "B", "B", "B"
		 *
		 * @return una instancia de una clase que implememte el interface Iterator<T>
		 * 
		 */
		public Iterator<T> iteratorRep();
		
	}

	
