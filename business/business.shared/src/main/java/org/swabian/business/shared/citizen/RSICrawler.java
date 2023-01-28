package org.swabian.business.shared.citizen;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.scout.rt.platform.text.TEXTS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSICrawler {

	static Logger logger = LoggerFactory.getLogger("RSICrawler");
	private static Map<String, Map<RSIData, String>> handleCache = new HashMap<String, Map<RSIData, String>>();

	public static String queryData(String handle, RSIData rsiData) {
		if (handle != null) {
			if (!handleCache.containsKey(handle)) {
				handleCache.put(handle, new HashMap<RSIData, String>());
			}
			if (handleCache.get(handle).get(rsiData) != null) {
				return handleCache.get(handle).get(rsiData);
			} else {
				try {
					String url = "https://robertsspaceindustries.com/citizens/" + handle;
					Document document = Jsoup.connect(url).get();
					Elements element = document.selectXpath(rsiData.getXpath());
					logger.info(TEXTS.get("HandleQuerySuccessful", handle, url, rsiData.toString()));
					String value = element.text();
					handleCache.get(handle).put(rsiData, value);
					return value;
				} catch (Exception e) {
					// Log a message with exception
					logger.warn(TEXTS.get("HandleError", handle));
				}
			}
		}
		return "";

	}

	public static String queryUrl(String handle, RSIData rsiData) {
		if (handle != null) {
			Map<RSIData, String> rsiDataMap = handleCache.get(handle);
			if (rsiDataMap == null) {
				handleCache.put(handle, new HashMap<RSIData, String>());
			} else if (rsiDataMap.get(rsiData) != null) {
				return rsiDataMap.get(rsiData);
			} else {
				try {
					String url = "https://robertsspaceindustries.com/citizens/" + handle;
					Document document = Jsoup.connect(url).get();
					Elements element = document.selectXpath(rsiData.getXpath());
					logger.info(TEXTS.get("HandleQuerySuccessful", handle, url));
					String value = element.attr("src");
					if (!value.startsWith("https")) {
						value = "https://robertsspaceindustries.com" + value;
					}
					handleCache.get(handle).put(rsiData, value);
					return value;
				} catch (Exception e) {
					// Log a message with exception
					logger.warn(TEXTS.get("HandleError", handle));
				}
			}
		}
		return "";

	}
}
