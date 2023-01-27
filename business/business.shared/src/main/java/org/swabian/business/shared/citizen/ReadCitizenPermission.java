package org.swabian.business.shared.citizen;

import org.eclipse.scout.rt.security.AbstractPermission;

public class ReadCitizenPermission extends AbstractPermission {
	private static final long serialVersionUID = 1L;

	public ReadCitizenPermission() {
		super("ReadCitizenPermission");
	}
}
