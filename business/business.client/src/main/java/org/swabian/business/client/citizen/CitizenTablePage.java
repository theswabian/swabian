package org.swabian.business.client.citizen;

import java.util.UUID;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.swabian.business.client.citizen.CitizenTablePage.Table;
import org.swabian.business.client.common.QuickNewMenu;
import org.swabian.business.shared.Icons;
import org.swabian.business.shared.citizen.CitizenTablePageData;
import org.swabian.business.shared.citizen.ICitizenService;

@Data(CitizenTablePageData.class)
public class CitizenTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(ICitizenService.class).getCitizenTableData(filter));
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Citizen");
	}

	public class Table extends AbstractTable {
		@Override
		protected Class<? extends IMenu> getConfiguredDefaultMenu() { // <1>
			return EditMenu.class;
		}

		public HandleColumn getHandleColumn() {
			return getColumnSet().getColumnByClass(HandleColumn.class);
		}

		public FluencyColumn getFluencyColumn() {
			return getColumnSet().getColumnByClass(FluencyColumn.class);
		}

		public RankColumn getRankColumn() {
			return getColumnSet().getColumnByClass(RankColumn.class);
		}

		public MonikerColumn getMonikerColumn() {
			return getColumnSet().getColumnByClass(MonikerColumn.class);
		}

		public IdColumn getIdColumn() {
			return getColumnSet().getColumnByClass(IdColumn.class);
		}

		@Order(10)
		@ClassId("4a8f5e0e-6eb8-4296-8ad7-012151f572f2")
		public class EditMenu extends AbstractMenu {

			@Override
			protected String getConfiguredIconId() {
				return Icons.Pencil;
			}

			@Override
			protected String getConfiguredKeyStroke() {
				return "alt-e";
			}

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

			@Override
			protected void execAction() {
				CitizenForm form = new CitizenForm();
				form.setCitizenId(getIdColumn().getSelectedValue()); // <2>
				form.addFormListener(new CitizenFormListener());
				form.startModify();
			}
		}

		@Order(20)
		public class NewMenu extends QuickNewMenu {

			@Override
			protected void execAction() {
				CitizenForm form = new CitizenForm();
				form.addFormListener(new CitizenFormListener());
				// start the form using its new handler
				form.startNew();
			}

		}

		private class CitizenFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {
				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}
		}

		@Order(1000)
		public class IdColumn extends AbstractColumn<UUID> {

			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			};

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			};
		}

		@Order(2000)
		public class HandleColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Handle");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class MonikerColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("CommunityMoniker");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class RankColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Rank");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class FluencyColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Fluency");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

	}
}
