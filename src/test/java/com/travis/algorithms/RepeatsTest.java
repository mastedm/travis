package com.travis.algorithms;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.travis.algorithms.Repeats.Psy1ResultTriple;
import com.travis.utils.Utils;

public class RepeatsTest {

	@Test
	public void psy1() {
		
		String str = "aaabbbcccaaacccbbb";
		int[] strIntArray = Utils.stringToIntArray(str, 3); 
		int[] SA = new int[str.length() + 3];
		
		SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 512);
		int[] lcp = LongestCommonPrefix.getHeight(strIntArray, SA, str.length());
		int[] bwt = BurrowsWheelerTransform.bwt(strIntArray, SA, str.length());
		
		List<Psy1ResultTriple> result = Repeats.psy1(lcp, bwt, 3, str.length());
		
		assertEquals(3, result.size());
		assertEquals(new Psy1ResultTriple(3, 0, 1), result.get(0));
		assertEquals(new Psy1ResultTriple(3, 8, 9), result.get(1));
		assertEquals(new Psy1ResultTriple(3, 16, 17), result.get(2));
		
		
		for (Psy1ResultTriple item : result) {
			System.out.println(item.toStringSmart(str, SA));
		}
		
	}

}
