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
package org.swabian.business.server.sql;

import org.eclipse.scout.rt.platform.config.AbstractBooleanConfigProperty;
import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;

// tag::structure[]
public class DatabaseProperties {

	public static class DatabaseAutoCreateProperty extends AbstractBooleanConfigProperty {
		// defines default value and key

		@Override
		public Boolean getDefaultValue() {
			return Boolean.TRUE; // <1>
		}

		@Override
		public String getKey() {
			return "contacts.database.autocreate"; // <2>
		}

		@Override
		public String description() {
			return "Specifies if the contacts database should automatically be created if it does not exist yet. The default value is true.";
		}
	}

	public static class DatabaseAutoPopulateProperty extends AbstractBooleanConfigProperty {
		// defines default value and key
		// end::structure[]

		@Override
		public Boolean getDefaultValue() {
			return Boolean.TRUE;
		}

		@Override
		public String getKey() {
			return "contacts.database.autopopulate";
		}

		@Override
		public String description() {
			return "Specifies if test data should be inserted into a newly created contact database. The default value is true.";
		}
		// tag::structure[]
	}

	public static class JdbcMappingNameProperty extends AbstractStringConfigProperty {
		// defines default value and key
		// end::structure[]

		@Override
		public String getDefaultValue() {
			return "jdbc:postgresql://localhost:5432/swabian";
		}

		@Override
		public String getKey() {
			return "contacts.database.jdbc.mappingName";
		}

		@Override
		public String description() {
			return "JDBC mapping name for the contacts database. The default value is 'jdbc:derby:memory:contacts-database'.";
		}
		// tag::structure[]
	}
}
// end::structure[]
