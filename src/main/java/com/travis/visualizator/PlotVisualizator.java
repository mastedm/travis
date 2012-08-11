package com.travis.visualizator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class PlotVisualizator {
	
	public void generateHtml(String indexFileName) {
		
		StringBuilder indexPage = new StringBuilder();
		
		String d1 = "[";

		d1 += "[1, 1], [2, 4], [3, 14], [4, 5], [5, 2], [6, 1], [7, 1], [10, 1]";
		d1 += "]";
		
		String d2 = "[";
		d2 += "[1, 5], [4, 4], [5, 5], [7, 4], [10, 5]";
		d2 += "]";
		
		indexPage.append("<html><head><title>Travis Result</title>")
			.append("<script language=\"javascript\" type=\"text/javascript\" src=\"http://people.iola.dk/olau/flot/jquery.js\"></script>")
			.append("<script language=\"javascript\" type=\"text/javascript\" src=\"http://people.iola.dk/olau/flot/jquery.flot.js\"></script>")
			.append("<link href=\"http://people.iola.dk/olau/flot/examples/layout.css\" rel=\"stylesheet\" type=\"text/css\">")
			.append("</head><body>")
			.append("<h1>Results</h1>")
			.append("<div id=\"placeholder\" style=\"width:600px;height:300px;\"></div>")
			.append("<script type=\"text/javascript\">")
			.append("$(function () {")
			.append("var d1 = ").append(d1).append(";")
			.append("var d2 = ").append(d2).append(";")
			.append("$.plot($(\"#placeholder\"), [ { data : d1, label : 'XXX' }, { data : d2, label : 'ZZZ' }]);")
			.append("});")
			.append("</script>")
			.append("</body></html>");
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(indexFileName));
			writer.append(indexPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
		}
		  
	}
	
	public static void main(String[] args) {
		new PlotVisualizator().generateHtml("index.html");
	}
	
}