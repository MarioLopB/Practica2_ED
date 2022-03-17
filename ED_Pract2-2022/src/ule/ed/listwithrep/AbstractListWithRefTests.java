package ule.ed.listwithrep;


import static org.junit.Assert.*;


import java.beans.Transient;
import java.util.Iterator;
import java.util.NoSuchElementException;


import org.junit.*;

import ule.ed.exceptions.EmptyCollectionException;


public abstract class AbstractListWithRefTests {

	protected abstract <T> ListWithRep<T> createListWithRep();


	private ListWithRep<String> S1;

	private ListWithRep<String> S2;

	@Before
	public void setupListWithReps() {

		this.S1 = createListWithRep();

		this.S2 = createListWithRep();

		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}

	@Test
	//Las nuevas instancias del TAD tienen tamaño cero:
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}

	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		S1.add("B",1);
		assertEquals(S1.toString(), "(A A A B )");
	}

	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}

	@Test
	public void testAddRepElem() throws Exception{
		S2.add("ABC");
		assertEquals(S2.count("ABC"), 6);
	}


	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() throws EmptyCollectionException {
		assertEquals(0,S2.remove("ABC", 0));
	}

	@Test
	public void testIterator() {
		String e1 = "Hola";
		String e2 = "Probando";
		String e3 = "el";
		String e4 = "iterador";

		S1.add(e1);
		S1.add(e2, 1);
		S1.add(e3, 2);
		S1.add(e4, 1);

		Iterator <String> oIt = S1.iterator();
		String cadena="";
		while(oIt.hasNext()) {
			cadena+=oIt.next()+" ";
		}

		assertEquals("Hola Probando el iterador ", cadena);
	}

	// TODO AÑADIR MAS TESTS

	@Test (expected = NoSuchElementException.class)
	public void testIteratorNoNext() throws Exception{
		Iterator<String> oIt = S2.iterator();
		for(int i = 0; i < 5; i++){
			oIt.next();
		}
	}

	@Test
	public void testIteratorRep() {
		String e1 = "A";
		String e2 = "B";
		String e3 = "C";
		String e4 = "D";

		S1.add(e1);
		S1.add(e2, 1);
		S1.add(e3, 2);
		S1.add(e4, 1);

		Iterator <String> oIt = S1.iteratorRep();
		String cadena="";
		while(oIt.hasNext()) {
			cadena+=oIt.next()+" ";
		}

		assertEquals("A B C C D ", cadena);
	}

	@Test (expected = NoSuchElementException.class)
	public void testIteratorRepNoNext() throws Exception{
		Iterator<String> oIt = S2.iteratorRep();
		for(int i = 0; i < S2.size()+1; i++){
			oIt.next();
		}
	}

	@Test
	public void testAddExpand() throws Exception{
		S2.add("A",3);
		S2.add("B",3);
		S2.add("C",2);
	}

	@Test
	public void testAddSingular() throws Exception{
		S1.add("A");
		S1.add("B");
		S1.add("C");
		S1.add("D");
		S1.add("E");
		assertEquals("(A B C D E )", S1.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNegativeTimes() throws Exception{
		S2.add("A",-2);
	}

	@Test
	public void testAddZeroTimes() throws Exception{
		S2.add("A",0);
		assertFalse(S2.contains("A"));
	}

	@Test(expected = NullPointerException.class)
	public void testAddNull () throws Exception{
		S1.add(null);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullWithTimes () throws Exception{
		S1.add(null,7);
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNull() throws Exception{
		S2.remove(null,3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveNegativeTimes() throws Exception{
		S2.remove("ABC",-2);
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveNoElement() throws Exception{
		S2.remove("231", 1);
	}

	@Test
	public void testRemoveRep () throws Exception{
		assertEquals(S2.remove("ABC",1),1);
	}

	@Test
	public void testRemove0orMoreRep () throws Exception{
		S1.add("A", 2);
		S1.add("B",2);
		S1.add("C",3);
		assertEquals(S1.remove("A",2), 2);
		assertEquals(S1.remove("C",4), 3);
		assertEquals(S1.toString(), "(B B )");

	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveEmpty() throws Exception{
		S1.remove();
	}

	@Test(expected = EmptyCollectionException.class)
	public void testRemoveValorEmpty() throws Exception{
		S1.remove("V",2);
	}

	@Test
	public void testRemove() throws Exception{
		S1.add("A", 2);
		S1.add("B",2);
		S1.add("C",3);
		assertEquals(S1.remove(), 2);
		assertEquals(S1.toString(), "(B B C C C )");
	}

	@Test
	public void testRemove1Middle() throws Exception{
		S1.add("A", 2);
		S1.add("B",2);
		S1.add("C",3);
		assertEquals(S1.remove("B",1),1);
		assertEquals(S1.toString(), "(A A B C C C )");
	}

	@Test
	public void testRemoveAllMiddle() throws Exception{
		S1.add("A", 2);
		S1.add("B",2);
		S1.add("C",3);
		assertEquals(S1.remove("B",3),2);
		assertEquals(S1.toString(), "(A A C C C )");
	}

	@Test
	public void testRemoveFinal() throws Exception{
		S1.add("A", 2);
		S1.add("B",2);
		S1.add("C",3);
		assertEquals(S1.remove("C",3),3);
		assertEquals(S1.toString(), "(A A B B )");
	}

	@Test
	public void testRemoveOne() throws Exception{
		S1.add("A");
		assertEquals(S1.remove(),1);
	}

	@Test
	public void testLastElementWithTwo () throws Exception{
		S1.add("A",3);
		S1.add("B");
		S1.add("C");
		assertTrue(S1.contains("A"));
		assertEquals(S1.size(),5);
		assertEquals(S1.remove(), 3);
		assertFalse(S1.contains("A"));
		assertTrue(S1.contains("B"));
		assertEquals(S1.size(),2);
		assertEquals(S1.toString(),"(B C )");
	}

	@Test
	public void testIsEmpty() throws Exception{
		assertTrue(S1.isEmpty());
	}

	@Test
	public void testIsNotEmpty() throws Exception{
		assertFalse(S2.isEmpty());
	}

	@Test
	public void testClear() throws Exception{
		S1.add("A");
		S1.add("B");
		S1.add("C");
		assertEquals(S1.size(),3);
		S1.clear();
		assertEquals(S1.size(),0);
		assertTrue(S1.isEmpty());
		assertFalse(S1.contains("B"));
	}

	@Test(expected = NullPointerException.class)
	public void testCountNull() throws Exception{
		S2.count(null);
	}

	@Test
	public void testAddImpliesContais () throws Exception{
		S1.add("A",3);
		assertTrue(S1.contains("A"));
	}

	@Test
	public void testMoreCount() throws Exception{
		assertEquals(S2.count("XYZ"),10);
		assertEquals(S2.count("NOEX"),0);
	}

	@Test
	public void testRemoveAllElements() throws Exception{
		S1.add("A", 2);
		assertEquals(S1.remove("A",3),2);
		assertEquals(S1.toString(), "()");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRevoveEmptyNegative() throws Exception{
		S1.remove("A",-2);
	}

	@Test(expected = NullPointerException.class)
	public void testContainsNull() throws Exception{
		S2.contains(null);
	}
}