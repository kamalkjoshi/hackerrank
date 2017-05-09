package org.hr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * This will require json-simple-1.1.jar to be available in classpath
 *
 */
public class URLReaderWithPagination {
	public static void main(String[] args) throws Exception {

		String topUrl = "http://www.omdbapi.com/?s={0}&page={1}";
		String search = "waterworld";
		boolean hasMoreData = true;
		int page =1;
		while (hasMoreData) {
			String url = MessageFormat.format(topUrl, search,page);
			String body = getResponseBody(url);
			page++;
			hasMoreData = parseJson(body.toString());
		}
	}

	private static String getResponseBody(String urlString) throws Exception {

		URL url = new URL(urlString);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()));

		String inputLine;
		StringBuilder body = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			body.append(inputLine);
		}
		in.close();
		return body.toString();

	}

	private static boolean parseJson(String body) throws Exception {
		if (body == null) {
			return false;
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(body);
		Object result = jsonObject.get("Response");
		if ("True".equals(result)) {
//			Object totalCount = jsonObject.get("totalResults");
//			System.out.println(totalCount);
			Object search = jsonObject.get("Search");
			if (search instanceof JSONArray) {
				JSONArray searchArray = (JSONArray) search;
				int length = searchArray.size();
				handleSearchArray(searchArray, length);				
			}
			return true;
		}
		return false;
	}

	private static void handleSearchArray(JSONArray searchArray, int length) {
		for (int i = 0; i < length; i++) {
			Object element = searchArray.get(i);
			if (element instanceof JSONObject) {
				System.out.println(((JSONObject) element).get("Title"));
			}
		}
	}

}