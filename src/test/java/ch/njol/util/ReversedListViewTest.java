package ch.njol.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ReversedListViewTest {
	
	@Test
	public void test() {
		
		final ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 4, 7));
		final ReversedListView<Integer> reverse = new ReversedListView<Integer>(list);
		
		assertEquals(reverse.get(0), list.get(list.size() - 1));
		assertEquals(list.indexOf(1), list.size() - reverse.lastIndexOf(1) - 1);
		assertEquals(new ReversedListView<Integer>(reverse), list);
		assertEquals(reverse.listIterator(1).next(), list.get(list.size() - 2));
		
	}
	
}
