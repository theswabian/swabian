/*
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
package org.swabian.business.client.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

@ClassId("37736ea5-e861-43d8-a6bc-144dad3c208f")
public class CountryLookupCall extends LocalLookupCall<String> { // <1>

	private static final long serialVersionUID = 1L;

	@Override
	protected List<LookupRow<String>> execCreateLookupRows() { // <2>
		List<LookupRow<String>> rows = new ArrayList<>();

		for (String countryCode : Locale.getISOCountries()) {
			Locale country = new Locale("", countryCode);
			rows.add(new LookupRow<>(countryCode, country.getDisplayCountry())); // <3>
		}

		return rows;
	}
}
