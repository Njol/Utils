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

package ch.njol.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Utils for collections and arrays. All mehods will not print any errors for <tt>null</tt> collections/arrays, but will return false/-1/etc.
 * 
 * @author Peter Güttinger
 */
public abstract class CollectionUtils {
	private CollectionUtils() {}
	
	/**
	 * Finds an object in an array using {@link Object#equals(Object)} (can find null elements).
	 * 
	 * @param array The array to search in
	 * @param o The object to search for
	 * @return The index of the first occurrence of the given object or -1 if not found
	 */
	public static <T> int indexOf(final T[] array, final T t) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null ? t == null : array[i].equals(t))
				return i;
		}
		return -1;
	}
	
	public static <T> int indexOf(final T[] array, final T t, final int start, final int end) {
		if (array == null)
			return -1;
		for (int i = start; i < end; i++) {
			if (array[i] == null ? t == null : array[i].equals(t))
				return i;
		}
		return -1;
	}
	
	public static <T> boolean contains(final T[] array, final T o) {
		return indexOf(array, o) != -1;
	}
	
	public static <T> boolean containsAny(final T[] array, final T... os) {
		if (array == null || os == null)
			return false;
		for (final T o : os) {
			if (indexOf(array, o) != -1)
				return true;
		}
		return false;
	}
	
	public static <T> boolean containsAll(final T[] array, final T... os) {
		if (array == null || os == null)
			return false;
		for (final T o : os) {
			if (indexOf(array, o) == -1)
				return false;
		}
		return true;
	}
	
	public static int indexOf(final int[] array, final int num) {
		if (array == null)
			return -1;
		return indexOf(array, num, 0, array.length);
	}
	
	public static int indexOf(final int[] array, final int num, final int start) {
		if (array == null)
			return -1;
		return indexOf(array, num, start, array.length);
	}
	
	public static int indexOf(final int[] array, final int num, final int start, final int end) {
		if (array == null)
			return -1;
		for (int i = start; i < end; i++) {
			if (array[i] == num)
				return i;
		}
		return -1;
	}
	
	public static final boolean contains(final int[] array, final int num) {
		return indexOf(array, num) != -1;
	}
	
	/**
	 * finds a string in an array of strings (ignoring case).
	 * 
	 * @param array the array to search in
	 * @param s the string to search for
	 * @return the index of the first occurrence of the given string or -1 if not found
	 */
	public static int indexOfIgnoreCase(final String[] array, final String s) {
		if (array == null)
			return -1;
		int i = 0;
		for (final String a : array) {
			if (a.equalsIgnoreCase(s))
				return i;
			i++;
		}
		return -1;
	}
	
	public static boolean containsIgnoreCase(final String[] array, final String s) {
		return indexOfIgnoreCase(array, s) != -1;
	}
	
	/**
	 * Finds an object in an iterable using {@link Object#equals(Object)}.
	 * 
	 * @param iter the iterable to search in
	 * @param o the object to search for
	 * @return the index of the first occurrence of the given object or -1 if not found
	 */
	public static <T> int indexOf(final Iterable<T> iter, final T o) {
		if (iter == null)
			return -1;
		int i = 0;
		for (final T a : iter) {
			if (a.equals(o))
				return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * Finds a string in a collection of strings (ignoring case).
	 * 
	 * @param iter The iterable to search in
	 * @param s The string to search for
	 * @return The index of the first occurrence of the given string or -1 if not found
	 */
	public static int indexOfIgnoreCase(final Iterable<String> iter, final String s) {
		if (iter == null)
			return -1;
		int i = 0;
		for (final String a : iter) {
			if (a.equalsIgnoreCase(s))
				return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * @param map
	 * @param key
	 * @return A new entry object
	 */
	public static <T, U> Entry<T, U> containsKey(final Map<T, U> map, final T key) {
		assert map != null;
		if (map == null)
			return null;
		final U u = map.get(key);
		if (u == null)
			return null;
		return new Pair<T, U>(key, u);
	}
	
	public static <U> Entry<String, U> containsKeyIgnoreCase(final Map<String, U> map, final String key) {
		assert map != null;
		if (map == null)
			return null;
		for (final Entry<String, U> e : map.entrySet()) {
			if (e.getKey().equalsIgnoreCase(key))
				return e;
		}
		return null;
	}
	
	/**
	 * @param classes Array of classes
	 * @param c The class to look for
	 * @return Whether the class or any of its superclasses are contained in the array
	 */
	public final static boolean containsSuperclass(final Class<?>[] classes, final Class<?> c) {
		assert classes != null && c != null;
		if (classes == null || c == null)
			return false;
		for (final Class<?> cl : classes) {
			if (cl.isAssignableFrom(c))
				return true;
		}
		return false;
	}
	
	/**
	 * @param classes Array of classes
	 * @param c The classes to look for
	 * @return Whether the classes or any of their superclasses are contained in the array
	 */
	public final static boolean containsAnySuperclass(final Class<?>[] classes, final Class<?>... cs) {
		assert classes != null && cs != null;
		if (classes == null || cs == null)
			return false;
		for (final Class<?> cl : classes) {
			for (final Class<?> c : cs) {
				if (cl.isAssignableFrom(c))
					return true;
			}
		}
		return false;
	}
	
	private final static Random random = new Random();
	
	public static <T> T random(final T[] os) {
		assert os != null && os.length != 0;
		return os[random.nextInt(os.length)];
	}
	
	public static <T> T getRandom(final T[] os, final int start) {
		assert os != null && os.length != 0;
		return os[random.nextInt(os.length - start) + start];
	}
	
	public static <T> T getRandom(final List<T> os) {
		assert os != null && !os.isEmpty();
		return os.get(random.nextInt(os.size()));
	}
	
	/**
	 * @param set The set of elements
	 * @param sub The set to test for being a subset of <tt>set</tt>
	 * @return Whether <tt>sub</tt> only contains elements out of <tt>set</tt> or not
	 */
	public static boolean isSubset(final Object[] set, final Object[] sub) {
		for (final Object s : set) {
			if (!contains(sub, s))
				return false;
		}
		return true;
	}
	
	/**
	 * Creates an array from the given objects. Useful for creating arrays of generic types.
	 * 
	 * @param array Some objects
	 * @return The passed array
	 */
	public static <T> T[] array(final T... array) {
		return array;
	}
	
	/**
	 * Creates a permutation of all integers in the interval [start, end]
	 * 
	 * @param start The lowest number which will be included in the permutation
	 * @param end The highest number which will be included in the permutation
	 * @return an array of length end - start + 1
	 */
	public static final int[] permutation(final int start, final int end) {
		final int length = end - start + 1;
		final int[] r = new int[length];
		for (int i = 0; i < length; i++)
			r[i] = start + i;
		for (int i = 0; i < length; i++) {
			final int j = random.nextInt(length);
			final int b = r[i];
			r[i] = r[j];
			r[j] = b;
		}
		return r;
	}
	
	/**
	 * Shorthand for <code>{@link permutation permutation}(0, length - 1)</code>
	 * 
	 * @param length
	 * @return
	 */
	public static final int[] permutation(final int length) {
		return permutation(0, length - 1);
	}
	
}
