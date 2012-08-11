package com.travis.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Algorithms for computing all repeats in a string.
 * 
 * @author Sergey Alexeev
 *
 */
public class Repeats {
	
	/**
	 * Algorithm PSY1, based on suffix array construction, computes all the 
	 * complete nonextendible repeats in x of length p >= p_min. PSY1 executes
	 * in Θ(n) time independent of alphabet size.
	 * 
	 * Article: "Fast Optimal Algorithms for Computing All the Repeats in a 
	 * String" Simon J. Puglishi, William F. Smyth, Munina Yusufu
	 */
	
	static class Psy1Triple {
		
		public Psy1Triple(int lcp, int lb, int bwt) {
			this.lcp = lcp; this.lb = lb; this.bwt = bwt;
		}
		
		int lcp, lb, bwt;		
	}
	
	public static class Psy1ResultTriple {
		
		public Psy1ResultTriple(int p, int i, int j) {
			this.p = p; this.i = i; this.j = j;
		}
		
		@Override
		public String toString() {
			return "Psy1ResultTriple: (" + p + "; " + i + ", " + j + ")";
		}
		
		public String toStringSmart(String str, int[] SA) {
			StringBuilder sb = new StringBuilder();
			sb.append(toString()).append("\n");
			
			for (int k = i; k <= j; k++) {
				sb.append(SA[k]).append(", ")
				  .append(str.substring(SA[k], SA[k] + p))
				  .append("\n");
			}
			
			return sb.toString();
		}
		
		@Override
		public int hashCode() {
			return p<<16 + i<<8 + j;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (!(obj instanceof Psy1ResultTriple)) return false;
			
			Psy1ResultTriple triple = (Psy1ResultTriple)obj;
			
			return triple.p == this.p && 
				   triple.i == this.i && 
				   triple.j == this.j; 
		}
		
		int p, i, j;
	}
	
	public static List<Psy1ResultTriple> psy1(int[] LCP, int[] BWT, int p_min, int n) {
		List<Psy1ResultTriple> result = new ArrayList<Psy1ResultTriple>();
		int bwt, bwt1, bwt2;
		
		int lcp = -1;
		int lb = 0;
		
		bwt1 = BWT[0];
		
		Stack<Psy1Triple> LB = new Stack<Psy1Triple>();
		LB.push(new Psy1Triple(lcp, lb, bwt1));
		
		for (int j = 0; j < n; j++) {
			lb = j;
			
			lcp  = j + 1 < n ? LCP[j + 1] : -1;
			bwt2 = j + 1 < n ? BWT[j + 1] : Common.TERMINATION_CHAR;
			
			bwt = LEletter(bwt1, bwt2);
			bwt1 = bwt2;
			
			while (LB.peek().lcp > lcp) {
				Psy1Triple prev = LB.pop();
				
				if (prev.bwt == Common.TERMINATION_CHAR && prev.lcp >= p_min) {
					result.add(new Psy1ResultTriple(prev.lcp, prev.lb, j));
				}
				
				lb = prev.lb;
				LB.peek().bwt = LEletter(prev.bwt, LB.peek().bwt);
				bwt = LEletter(prev.bwt, bwt);
			}
			
			if (LB.peek().lcp == lcp) {
				LB.peek().bwt = LEletter(LB.peek().bwt, bwt);
			} else {
				LB.push(new Psy1Triple(lcp, lb, bwt));
			}
		}
		
		return result;
	}
	
	private static int LEletter(int l1, int l2) {
		if (l1 == Common.TERMINATION_CHAR || l1 != l2) {
			return Common.TERMINATION_CHAR;
		} else {
			return l1;
		}
	}
	
	/**
	 * Algorithm PSY2 for computing all complete supernonextendible repeats in 
	 * x that also executes in Θ(n) time independent of alphabet size.
	 * 
	 * Article: "Fast Optimal Algorithms for Computing All the Repeats in a 
	 * String" Simon J. Puglishi, William F. Smyth, Munina Yusufu
	 */
	public static void psy2() {
		int j = 0;
		int p = -1;
		int q = 0;
		
		
		
	}
}