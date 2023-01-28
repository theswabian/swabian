package org.swabian.business.shared.citizen;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RSICrawler {

	public static String getCommunityMoniker(String handle) {
		if (handle != null) {
			try {
				Document doc = Jsoup.connect("https://robertsspaceindustries.com/citizens/" + handle).get();
				Elements communityMonikerElement = doc.selectXpath(
						"/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[2]/p[1]/strong");
				return communityMonikerElement.text();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static String getAvatarUrl(String handle) {
		if (handle != null) {
			try {
				Document doc = Jsoup.connect("https://robertsspaceindustries.com/citizens/" + handle).get();
				Elements image = doc
						.selectXpath("/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[1]/img");
				String attr = image.attr("src");
				return "https://robertsspaceindustries.com" + attr;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
