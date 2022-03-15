package ule.ed.listwithrep;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListWithRepRefTests extends AbstractListWithRefTests {

	@Override
	protected <T> ListWithRep<T> createListWithRep() {	
		return new ArrayListWithRepImpl<T>();
	}
	
	// tests solo para implementación Array, que no aplican a la implementación  Linked
@Test
public void expandCapacityTest() {
	ArrayListWithRepImpl<Integer> aq = new ArrayListWithRepImpl<Integer>(2);
	aq.add(1);
	aq.add(2);
	aq.add(3);
	aq.add(4);
	aq.add(5);
	aq.add(6);
	aq.add(7);
	aq.add(8);
	aq.add(15);
	aq.add(16);
	aq.add(17);
	aq.add(18);
	Assert.assertEquals(12, aq.size());
}

}
