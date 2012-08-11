package com.travis;

import java.util.List;

import com.travis.algorithms.BurrowsWheelerTransform;
import com.travis.algorithms.LongestCommonPrefix;
import com.travis.algorithms.Repeats;
import com.travis.algorithms.Repeats.Psy1ResultTriple;
import com.travis.algorithms.SuffixArray;
import com.travis.preprocessor.AbstractPreprocessor;
import com.travis.utils.Utils;

public class Travis {
	
	private AbstractPreprocessor preprocessor;
	private String tmpResult;
	
	public Travis(AbstractPreprocessor preprocessor) {
		this.preprocessor = preprocessor;
	}
	
	public void process() {
		String data = preprocessor.getData();
		
		int[] strIntArray = Utils.stringToIntArray(data, 3); 
		int[] SA = new int[data.length() + 3];
		
		SuffixArray.suffixArrayLinear(strIntArray, SA, data.length(), 512);
		int[] lcp = LongestCommonPrefix.getHeight(strIntArray, SA, data.length());
		int[] bwt = BurrowsWheelerTransform.bwt(strIntArray, SA, data.length());
		
		List<Psy1ResultTriple> result = Repeats.psy1(lcp, bwt, 5, data.length());
		
		tmpResult = "PSY1: size = " + result.size() + "\n";
		for (Psy1ResultTriple item : result) {
			tmpResult += item.toStringSmart(data, SA);
		}
	}
	
	public String getTmpResult() {
		return tmpResult;
	}
	
	
}
