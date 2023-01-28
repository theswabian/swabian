package org.swabian.business.client.common;

import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

public class NonNullStringField extends AbstractStringField {
	@Override
	protected String execValidateValue(String rawValue) {
		if (StringUtility.isNullOrEmpty(rawValue)) {
			throw new VetoException(TEXTS.get("Fehler_Client_Leer", getConfiguredLabel()));
		}
		return rawValue;
	}
}
