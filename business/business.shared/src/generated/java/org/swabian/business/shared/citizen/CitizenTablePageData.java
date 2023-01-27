package org.swabian.business.shared.citizen;

import java.util.UUID;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.swabian.business.client.citizen.CitizenTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class CitizenTablePageData extends AbstractTablePageData {
	private static final long serialVersionUID = 1L;

	@Override
	public CitizenTableRowData addRow() {
		return (CitizenTableRowData) super.addRow();
	}

	@Override
	public CitizenTableRowData addRow(int rowState) {
		return (CitizenTableRowData) super.addRow(rowState);
	}

	@Override
	public CitizenTableRowData createRow() {
		return new CitizenTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return CitizenTableRowData.class;
	}

	@Override
	public CitizenTableRowData[] getRows() {
		return (CitizenTableRowData[]) super.getRows();
	}

	@Override
	public CitizenTableRowData rowAt(int index) {
		return (CitizenTableRowData) super.rowAt(index);
	}

	public void setRows(CitizenTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class CitizenTableRowData extends AbstractTableRowData {
		private static final long serialVersionUID = 1L;
		public static final String id = "id";
		public static final String handle = "handle";
		public static final String moniker = "moniker";
		public static final String rank = "rank";
		public static final String fluency = "fluency";
		private UUID m_id;
		private String m_handle;
		private String m_moniker;
		private String m_rank;
		private String m_fluency;

		public UUID getId() {
			return m_id;
		}

		public void setId(UUID newId) {
			m_id = newId;
		}

		public String getHandle() {
			return m_handle;
		}

		public void setHandle(String newHandle) {
			m_handle = newHandle;
		}

		public String getMoniker() {
			return m_moniker;
		}

		public void setMoniker(String newMoniker) {
			m_moniker = newMoniker;
		}

		public String getRank() {
			return m_rank;
		}

		public void setRank(String newRank) {
			m_rank = newRank;
		}

		public String getFluency() {
			return m_fluency;
		}

		public void setFluency(String newFluency) {
			m_fluency = newFluency;
		}
	}
}
