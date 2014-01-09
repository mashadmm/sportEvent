package com.muni.fi.pa165.gui;

import com.muni.fi.pa165.client.SportEventClient;
import com.pa165.sportevent.ws.GradeDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class GradeTableModel extends AbstractTableModel {
 
    private static final Logger LOGGER = Logger.getLogger(GradeTableModel.class.getName());
    
    
    private List<GradeDTO> grades = new ArrayList<GradeDTO>();
    private static enum COLUMNS {
        SPORTSMAN, EVENT, GRADE
    }
    
    private SportEventClient client;
    public  GradeTableModel(){
        this.client = new SportEventClient();
    } 
    
    @Override
    public int getRowCount() {
        return grades.size();
    }
 
    @Override
    public int getColumnCount() {
        return COLUMNS.values().length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {

	    case SPORTSMAN:
            case EVENT:
                return String.class;
            case GRADE:
		return Integer.class;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }
   
    
    @Override
    public String getColumnName(int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case SPORTSMAN:
		return java.util.ResourceBundle.getBundle("Bundle").getString("efficiencies_table_sportsman");
            case EVENT:
		return java.util.ResourceBundle.getBundle("Bundle").getString("efficiencies_table_event");
	    case GRADE:
		return java.util.ResourceBundle.getBundle("Bundle").getString("efficiencies_grade");
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GradeDTO grade = grades.get(rowIndex);
        switch (COLUMNS.values()[columnIndex]) {
            case SPORTSMAN:
                return (grade.getSportsman().getName() + " "+ grade.getSportsman().getLastname());
            case EVENT:
                return grade.getEvent().getName();
            case GRADE:
                return grade.getGrade();

            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    public void addGrade(GradeDTO grade) {
	grades.add(grade);
	fireTableDataChanged();
    }
    
    public void removeGrade(GradeDTO grade) {
	grades.remove(grade);
	fireTableDataChanged();
    }
    
    public void clear() {
	grades.clear();
        fireTableDataChanged();
    }
    
     public List<GradeDTO> getAllEfficiencies() {
	return grades;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	GradeDTO grade = grades.get(rowIndex);
	switch (COLUMNS.values()[columnIndex]) {
	    case GRADE:
		grade.setGrade((Integer) aValue);
		break;
	   default:
		throw new IllegalArgumentException("columnIndex");
	}
        try {
            client.gradeManagerupdate(grade);
            fireTableDataChanged();
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case SPORTSMAN:
	    case EVENT:
                 return false;
	    case GRADE:
		return true;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }

}
