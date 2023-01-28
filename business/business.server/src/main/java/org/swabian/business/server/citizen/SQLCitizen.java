package org.swabian.business.server.citizen;

public interface SQLCitizen {
	String CITIZEN_PAGE_SELECT = "SELECT text(id), handle" + " FROM citizen";
	String CITIZEN_PAGE_SELECT_INTO = " INTO :{page.id}, :{page.handle}";
	String CITIZEN_SELECT = "SELECT handle FROM citizen WHERE id=uuid(:id) INTO :handle";
	String CITIZEN_INSERT = "INSERT INTO citizen(id, handle) VALUES (uuid(:id), :handle)";
	String CITIZEN_UPDATE = "UPDATE citizen SET handle=:handle WHERE id = uuid(:id)";
}
