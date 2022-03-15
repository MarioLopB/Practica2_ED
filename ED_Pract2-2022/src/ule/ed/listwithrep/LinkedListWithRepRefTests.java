package ule.ed.listwithrep;

public class LinkedListWithRepRefTests extends AbstractListWithRefTests {

	@Override
	protected <T> ListWithRep<T> createListWithRep() {
		
		return new LinkedListWithRepImpl<T>();
	}

}
