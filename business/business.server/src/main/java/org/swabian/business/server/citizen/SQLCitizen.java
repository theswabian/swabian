package org.swabian.business.server.citizen;

public interface SQLCitizen {
	String CITIZEN_PAGE_SELECT = "SELECT id, handle, moniker, rank, fluency" + " FROM citizen";
	String CITIZEN_PAGE_SELECT_INTO = " INTO :{page.id}, :{page.handle}, :{page.moniker}, :{page.rank}, :{page.fluency}";
	String CITIZEN_SELECT = "SELECT handle FROM citizen INTO :handle";
}
