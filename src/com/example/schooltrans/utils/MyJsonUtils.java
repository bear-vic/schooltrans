package com.example.schooltrans.utils;

public class MyJsonUtils {
	public static String handleJsonResult(String json) {
		return json.replaceAll("/", "%").replaceAll("\\d{2}:\\d{2}", "").replaceAll(",\"gPicFile\":null", "").trim();
	}

	public static String handleMyGoods(String json) {
		return json.replaceAll("/", "%").replaceAll("\"gDate\\D+\\d+,", "").replaceAll("\\d{2}:\\d{2}", "")
				.replaceAll(",\"gPicFile\":null", "").trim();
	}

}
