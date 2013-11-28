/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Copyright 2011-2013 Peter Güttinger
 * 
 */

package ch.njol.util.coll;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;

/**
 * @author Peter Güttinger
 */
public final class CyclicList<E> extends AbstractList<E> {
	
	private final Object[] items;
	private int start = 0;
	
	public CyclicList(final int size) {
		this.items = new Object[size];
	}
	
	public CyclicList(final E[] array) {
		this.items = new Object[array.length];
		System.arraycopy(array, 0, items, 0, array.length);
	}
	
	public CyclicList(final Collection<E> c) {
		this.items = c.toArray();
	}
	
	private final int toInternalIndex(int index) {
		return (start + index) % items.length;
	}
	
	private final int toExternalIndex(int internal) {
		return (internal - start + items.length) % items.length;
	}
	
	@Override
	public boolean add(final E e) {
		return addLast(e);
	}
	
	public boolean addFirst(final E e) {
		start = (start + items.length - 1) % items.length;
		items[start] = e;
		return true;
	}
	
	public boolean addLast(final E e) {
		items[start] = e;
		start = (start + 1) % items.length;
		return true;
	}
	
	@Override
	public void add(final int index, final E e) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		for (final E e : c)
			add(e);
		return true;
	}
	
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
	
	private void rangeCheck(final int index) {
		if (index < 0 || index >= items.length)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + items.length);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(final int index) {
		rangeCheck(index);
		return (E) items[toInternalIndex(index)];
	}
	
	@Override
	public int indexOf(final Object o) {
		return toExternalIndex(CollectionUtils.indexOf(items, o));
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public int lastIndexOf(final Object o) {
		return toExternalIndex(CollectionUtils.lastIndexOf(items, o));
	}
	
	@Override
	public boolean remove(final Object o) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public E remove(final int index) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean removeAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean retainAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E set(final int index, final E e) {
		rangeCheck(index);
		int i = toInternalIndex(index);
		final E old = (E) items[i];
		items[i] = e;
		return old;
	}
	
	@Override
	public int size() {
		return items.length;
	}
	
	@Override
	public Object[] toArray() {
		return toArray(new Object[items.length]);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(final T[] array) {
		if (array == null)
			return (T[]) toArray();
		if (array.length < items.length)
			return toArray((T[]) Array.newInstance(array.getClass().getComponentType(), items.length));
		System.arraycopy(items, start, array, 0, items.length - start);
		System.arraycopy(items, 0, array, items.length - start, start);
		if (array.length > items.length)
			array[items.length] = null;
		return array;
	}
	
}
