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

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedReference<T> extends Reference<T> {
	
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public SynchronizedReference() {
		super();
	}
	
	public SynchronizedReference(final T value) {
		super(value);
	}
	
	@Override
	public T get() {
		lock.readLock().lock();
		try {
			return super.get();
		} finally {
			lock.readLock().unlock();
		}
	}
	
	@Override
	public void set(final T value) {
		lock.writeLock().lock();
		try {
			super.set(value);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	@Override
	public String toString() {
		lock.readLock().lock();
		try {
			return super.toString();
		} finally {
			lock.readLock().unlock();
		}
	}
	
	@Override
	public boolean equals(final Object obj) {
		lock.readLock().lock();
		try {
			return super.equals(obj);
		} finally {
			lock.readLock().unlock();
		}
	}
	
	@Override
	public int hashCode() {
		lock.readLock().lock();
		try {
			return super.hashCode();
		} finally {
			lock.readLock().unlock();
		}
	}
	
}
