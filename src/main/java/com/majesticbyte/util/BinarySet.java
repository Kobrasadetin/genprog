/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.util;
import java.util.Arrays;

/**
 * a binary mask set: constant time contains, add, remove
 *
 * @author mkarjanm
 */
public class BinarySet {

    final int[] bitmask;
    final int cellCount;

    public BinarySet(int size) {
        cellCount = size / 32 + 1;
        bitmask = new int[cellCount];
        for (int i = 0; i < cellCount; i++) {
            bitmask[i] = 0;
        }
    }

    public BinarySet(BinarySet other) {
        cellCount = other.cellCount;
        bitmask = new int[cellCount];
        System.arraycopy(other.bitmask, 0, bitmask, 0, cellCount);
    }

    public void union(BinarySet other) {
        for (int i = 0; i < cellCount; i++) {
            bitmask[i] |= other.bitmask[i];
        }
    }

    public boolean contains(int index) {
        final int maskIndex = 1 << (index & 31);
        return (bitmask[(index & ~31) >> 5] & maskIndex) == maskIndex;
    }

    public void listValues(Integer[] values) {
        int count = 1;
        for (int i = 0; i < cellCount; i++) {
            int ccount = i << 5;
            final int bits = bitmask[i];
            if (bits != 0) {
                for (int j = 0, mask = 1; j < 32; j++, mask = mask << 1) {
                    if ((bits & mask) != 0) {
                        values[count++] = ccount | j;
                    }
                }
            }
        }
        values[0] = count-1;
    }

    public void add(int index) {
        int maskIndex = 1 << (index & 31);
        bitmask[(index & ~31) >> 5] |= maskIndex;
    }

    public void remove(int index) {
        int maskIndex = 1 << (index & 31);
        bitmask[(index & ~31) >> 5] &= ~maskIndex;
    }

    public void substract(BinarySet other) {
        for (int i = 0; i < cellCount; i++) {
            bitmask[i] &= ~other.bitmask[i];
        }
    }

    public void clear() {
        for (int i = 0; i < cellCount; i++) {
            bitmask[i] = 0;
        }
    }

    public int count() {
        int total = 0;
        for (int i = 0; i < cellCount; i++) {
            if (bitmask[i] != 0) {
                total += Integer.bitCount(bitmask[i]);
            }
        }
        return total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.hashCode(this.bitmask);
        hash = 59 * hash + this.cellCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinarySet other = (BinarySet) obj;
        if (this.cellCount != other.cellCount) {
            return false;
        }
        if (!Arrays.equals(this.bitmask, other.bitmask)) {
            return false;
        }
        return true;
    }

}