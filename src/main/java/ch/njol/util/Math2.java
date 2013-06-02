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

/**
 * @author Peter Güttinger
 */
public abstract class Math2 {
	
	public static int min(final int... nums) {
		assert nums != null && nums.length != 0;
		int min = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < min)
				min = nums[i];
		}
		return min;
	}
	
	public final static int max(final int... nums) {
		assert nums != null && nums.length != 0;
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max)
				max = nums[i];
		}
		return max;
	}
	
	public final static double max(final double... nums) {
		assert nums != null && nums.length != 0;
		double max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max)
				max = nums[i];
		}
		return max;
	}
	
	/**
	 * finds the smallest positive number (&ge;0) in the sequence
	 * 
	 * @param nums
	 * @return smallest positive number in the sequence or -1 if no number is positive
	 */
	public final static int minPositive(final int... nums) {
		int max = -1;
		for (final int num : nums) {
			if (num >= 0 && (num < max || max == -1))
				max = num;
		}
		return max;
	}
	
	/**
	 * Fits a number into the given interval
	 * 
	 * @param min
	 * @param x
	 * @param max
	 * @return
	 */
	public static final int fit(final int min, final int x, final int max) {
		return x <= min ? min : x >= max ? max : x;
	}
	
	public static final long fit(final long min, final long x, final long max) {
		return x <= min ? min : x >= max ? max : x;
	}
	
	public static final float fit(final float min, final float x, final float max) {
		return x <= min ? min : x >= max ? max : x;
	}
	
	public static final double fit(final double min, final double x, final double max) {
		return x <= min ? min : x >= max ? max : x;
	}
	
	/**
	 * Modulo that returns positive values even for negative arguments.
	 * 
	 * @param d
	 * @param m
	 * @return <tt>d%m < 0 ? d%m + m : d%m</tt>
	 */
	public final static double mod(final double d, final double m) {
		final double r = d % m;
		return r < 0 ? r + m : r;
	}
	
}
