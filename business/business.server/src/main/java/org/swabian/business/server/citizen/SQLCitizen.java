package org.swabian.business.server.citizen;

public interface SQLCitizen {
	String CITIZEN_PAGE_SELECT = "SELECT id, handle" + " FROM citizen";
	String CITIZEN_PAGE_SELECT_INTO = " INTO :{page.id}, :{page.handle}";
	String CITIZEN_SELECT = "SELECT handle FROM citizen INTO :handle";
}
