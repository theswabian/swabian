package org.swabian.business.shared.citizen;

public enum RSIData {

	CommunityMoniker, AvatarUrl, RankUrl, Rank, Fluency, Location, Enlisted;

	public String getXpath() {
		switch (this) {
		case CommunityMoniker:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[2]/p[1]/strong";
		case AvatarUrl:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[1]/img";
		case RankUrl:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[2]/p[3]/span[1]/img";
		case Rank:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div[2]/p[3]/span[2]";
		case Fluency:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/p[3]/strong";
		case Location:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/p[2]/strong";
		case Enlisted:
			return "/html/body/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/p[1]/strong";
		default:
			return "";
		}
	}
}
