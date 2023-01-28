package org.swabian.business.server.citizen;

import java.util.UUID;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.swabian.business.shared.citizen.CitizenFormData;
import org.swabian.business.shared.citizen.CitizenTablePageData;
import org.swabian.business.shared.citizen.CreateCitizenPermission;
import org.swabian.business.shared.citizen.ICitizenService;
import org.swabian.business.shared.citizen.ReadCitizenPermission;
import org.swabian.business.shared.citizen.UpdateCitizenPermission;

public class CitizenService implements ICitizenService {
	@Override
	public CitizenTablePageData getCitizenTableData(SearchFilter filter) {
		CitizenTablePageData pageData = new CitizenTablePageData();
		String sql = SQLCitizen.CITIZEN_PAGE_SELECT + SQLCitizen.CITIZEN_PAGE_SELECT_INTO;
		SQL.selectInto(sql, new NVPair("page", pageData));
		return pageData;
	}

	@Override
	public CitizenFormData prepareCreate(CitizenFormData formData) {
		if (!ACCESS.check(new CreateCitizenPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		return formData;
	}

	@Override
	public CitizenFormData create(CitizenFormData formData) {
		if (!ACCESS.check(new CreateCitizenPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		if (formData.getId() == null) {
			formData.setId(UUID.randomUUID().toString());
		}
		SQL.insert(SQLCitizen.CITIZEN_INSERT, formData);
		return formData;
	}

	@Override
	public CitizenFormData load(CitizenFormData formData) {
		if (!ACCESS.check(new ReadCitizenPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		SQL.select(SQLCitizen.CITIZEN_SELECT, formData);
		return formData;
	}

	@Override
	public CitizenFormData store(CitizenFormData formData) {
		if (!ACCESS.check(new UpdateCitizenPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		SQL.update(SQLCitizen.CITIZEN_UPDATE, formData);
		return formData;
	}
}
