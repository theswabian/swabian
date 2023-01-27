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
package org.swabian.business.shared.citizen;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

//tag::all[]
@ClassId("bbe8fae2-4923-42bc-9745-3bb3ef592b12")
public class GenderCodeType extends AbstractCodeType<String, String> {

	private static final long serialVersionUID = 1L;
	public static final String ID = "Gender";

	@Override
	public String getId() {
		return ID;
	}

	@Order(1000)
	@ClassId("8893e1e4-7b6c-46c2-8c84-42c914ec29d5")
	public static class MaleCode extends AbstractCode<String> {

		private static final long serialVersionUID = 1L;
		public static final String ID = "M";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Male");
		}

		@Override
		public String getId() {
			return ID;
		}
	}

	@Order(2000)
	@ClassId("23e1540e-2914-401f-9f42-e409ac2fb605")
	public static class FemaleCode extends AbstractCode<String> {

		private static final long serialVersionUID = 1L;
		public static final String ID = "F";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Female");
		}

		@Override
		public String getId() {
			return ID;
		}
	}
}
//end::all[]
