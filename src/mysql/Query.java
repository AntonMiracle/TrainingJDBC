package mysql;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Project: SQLConnector Query.java
 */

/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public class Query {
	private String query;

	public boolean createTable(String condition) {
		clear();
		if ((condition == null || condition.equals(""))) {
			String tableName = JOptionPane.showInputDialog("Enter table name: ");
			String primaryKey = "";
			String columnNames = "";
			String nameType = "";
			int colNum = Integer.parseInt(JOptionPane.showInputDialog("Enter column number: "));
			for (int i = 0; i < colNum; i++) {
				String name = JOptionPane.showInputDialog("Enter " + (i + 1) + " column name: ");
				nameType += name + " " + JOptionPane.showInputDialog("Enter " + name + " column type: ") + ",";
				columnNames += name + " ";
			}
			primaryKey = JOptionPane.showInputDialog("Enter primary key. You set next names: " + columnNames);
			query = "CREATE TABLE " + tableName + "(" + nameType + " PRIMARY KEY (" + primaryKey + ")" + ")";
		} else {
			query = condition;
		}

		clear();
		return QueryConnection.createTableExecute(query) ? true : false;
	}

	public boolean createTable() {
		return createTable("");
	}

	public boolean insertRowIntoTable(String condition) {
		clear();
		if ((condition == null || condition.equals(""))) {
			String tableName = JOptionPane.showInputDialog("Enter table name where need to add row");
			ArrayList<String> columnsNames = QueryConnection.getTableColumnNames(tableName);
			String value = "";
			String names = "(";
			String values = "(";
			for (int i = 0; i < columnsNames.size(); i++) {
				names += (i < columnsNames.size() - 1) ? columnsNames.get(i) + "," : columnsNames.get(i) + ") VALUES ";
				value = JOptionPane.showInputDialog("Enter '" + columnsNames.get(i) + "' of new row in " + tableName
						+ " " + columnsNames.toString());
				values += (i < columnsNames.size() - 1) ? "'" + value + "'," : "'" + value + "')";
			}
			query = "INSERT INTO " + tableName + " " + names + values;
		} else {
			query = condition;
		}
		return QueryConnection.updateTableExecute(query) ? true : false;
	}

	public boolean insertRowIntoTable() {
		return insertRowIntoTable("");
	}

	
	
	public void clear() {
		setQuery("");
	}

	public boolean isEmpty() {
		if (query == null) query = "";
		return (query.length() == 0) ? true : false;
	}

	/** Getter */
	public String getQuery() {
		return query;
	}

	/** Setter */
	private void setQuery(String query) {
		this.query = query;
	}

	/* @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return query;
	}

	/* @see java.lang.Object#equals(java.lang.Object) */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Query) {
			return ((Query) obj).getQuery().equals(query) ? true : false;
		} else {
			return false;
		}
	}

}
