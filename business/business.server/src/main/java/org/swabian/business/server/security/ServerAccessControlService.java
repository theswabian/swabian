package org.swabian.business.server.security;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.AllPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;
import org.eclipse.scout.rt.security.PermissionLevel;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;
import org.swabian.business.shared.security.AccessControlService;

/**
 * @author phohmann
 */
@Replace
public class ServerAccessControlService extends AccessControlService {

	@Override
	protected IPermissionCollection execLoadPermissions(String userId) {
		IPermissionCollection permissions = BEANS.get(AllPermissionCollection.class);
		permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"), PermissionLevel.ALL);

		// TODO [phohmann]: Fill access control service - or replace this default
		// implementation by simply return BEANS.get(AllPermissionCollection.class)

		permissions.setReadOnly();
		return permissions;
	}
}
