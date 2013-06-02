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

public class Reference<T> {
	
	private T value = null;
	
	public Reference() {}
	
	public Reference(final T value) {
		this.value = value;
	}
	
	public T get() {
		return value;
	}
	
	public void set(final T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public boolean equals(final Object obj) {
		return obj == null ? value == null : obj.equals(value);
	}
	
	@Override
	public int hashCode() {
		return value == null ? 0 : value.hashCode();
	}
	
}
