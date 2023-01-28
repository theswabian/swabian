package org.swabian.business.client.common;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.swabian.business.shared.Icons;

public class QuickEditMenu extends AbstractMenu {
	@Override
	protected String getConfiguredText() {
		return TEXTS.get("Allgemein_Bearbeiten");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Gear;
	}
}
