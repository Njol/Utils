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

import java.io.Serializable;
import java.util.Map.Entry;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author Peter Güttinger
 */
public class NonNullPair<T1, T2> implements Entry<T1, T2>, Cloneable, Serializable {
	private static final long serialVersionUID = 820250942098905541L;
	
	public T1 first;
	public T2 second;
	
	public NonNullPair(final T1 first, final T2 second) {
		this.first = first;
		this.second = second;
	}
	
	public NonNullPair(final NonNullPair<T1, T2> other) {
		this.first = other.first;
		this.second = other.second;
	}
	
	/**
	 * @return "first,second"
	 */
	@Override
	public String toString() {
		return "" + first + "," + second;
	}
	
	/**
	 * Checks for equality with Entries to match {@link #hashCode()}
	 */
	@Override
	public boolean equals(final @Nullable Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Entry))
			return false;
		final Entry<?, ?> other = (Entry<?, ?>) obj;
		return first.equals(other.getKey()) && second.equals(other.getValue());
	}
	
	/**
	 * As defined by {@link Entry#hashCode()}
	 */
	@Override
	public int hashCode() {
		return first.hashCode() ^ second.hashCode();
	}
	
	@Override
	public T1 getKey() {
		return first;
	}
	
	@Override
	public T2 getValue() {
		return second;
	}
	
	@Override
	public T2 setValue(final T2 value) {
		final T2 old = second;
		second = value;
		return old;
	}
	
	/**
	 * @return a shallow copy of this pair
	 */
	@Override
	public NonNullPair<T1, T2> clone() {
		return new NonNullPair<T1, T2>(this);
	}
	
}
