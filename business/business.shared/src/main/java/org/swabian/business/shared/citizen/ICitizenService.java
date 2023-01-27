package org.swabian.business.shared.citizen;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface ICitizenService extends IService {
	CitizenTablePageData getCitizenTableData(SearchFilter filter);

	CitizenFormData prepareCreate(CitizenFormData formData);

	CitizenFormData create(CitizenFormData formData);

	CitizenFormData load(CitizenFormData formData);

	CitizenFormData store(CitizenFormData formData);
}
