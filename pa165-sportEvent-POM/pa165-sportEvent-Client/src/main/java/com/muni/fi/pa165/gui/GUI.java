package com.muni.fi.pa165.gui;


import com.muni.fi.pa165.client.SportEventClient;
import com.pa165.sportevent.ws.EventDTO;
import com.pa165.sportevent.ws.GradeDTO;
import com.pa165.sportevent.ws.GradeIdDTO;
import com.pa165.sportevent.ws.SportDTO;
import com.pa165.sportevent.ws.SportsmanDTO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
//import net.sourceforge.stripes.integration.spring.SpringBean;


/**
 *
 * @author Maria
 */
public class GUI extends javax.swing.JFrame {

    
    private static final Logger LOGGER = Logger.getLogger(GUI.class.getName());
    private String action;
    private Long grade_eventid;
    private Long grade_sportsmanid;
    
    //@SpringBean
    private SportEventClient client;
    
  
    GradeTableModel gradeTableModel;
    EventTableModel eventTableModel;
    SportsmanTableModel sportsmanTableModel;
    
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);
    private GregorianCalendar gregory = new GregorianCalendar();
    private XMLGregorianCalendar calendar;
    

    private GradesSwingWorker gradesSwingWorker;
    private class GradesSwingWorker extends SwingWorker<Void, GradeDTO> {

	@Override
	protected Void doInBackground() throws Exception {
	    gradeTableModel = (GradeTableModel) grades_table.getModel();
            int counter = 0;
	    for (GradeDTO grade : client.gradeManagerfindAll()) {
                counter++;
                Thread.sleep(150);
		publish(grade);
                setProgress(counter);
	    }
	    return null;
	}
	
	@Override
	protected void process(List<GradeDTO> items) {
	    for (GradeDTO i : items) {
                gradeTableModel.addGrade(i);
	    }
	}

        @Override
        protected void done() {
            grades_load.setEnabled(true);
            grades_progress.setValue(100);
            gradesSwingWorker = null;
        }
    }
    private EventsSwingWorker eventsSwingWorker;
    private class EventsSwingWorker extends SwingWorker<Void, EventDTO> {

	@Override
	protected Void doInBackground() throws Exception {
	    eventTableModel = (EventTableModel) events_table.getModel();
            int counter = 0;
	    for (EventDTO event : client.eventManagerfindAll()) {
                counter++;
                Thread.sleep(50);
		publish(event);
                setProgress(counter);
	    }
	    return null;
	}
	
	@Override
	protected void process(List<EventDTO> items) {
	    for (EventDTO i : items) {
                eventTableModel.addEvent(i);
	    }
	}

        @Override
        protected void done() {
            events_load.setEnabled(true);
            events_progress.setValue(100);
            eventsSwingWorker = null;
        }
    }
    private SportsmansSwingWorker sportsmansSwingWorker;
    private class SportsmansSwingWorker extends SwingWorker<Void, SportsmanDTO> {

	@Override
	protected Void doInBackground() throws Exception {
	    sportsmanTableModel = (SportsmanTableModel) sportsmans_table.getModel();
            int counter = 0;
	    for (SportsmanDTO sportsman : client.sportsmanManagerfindAll()) {
                counter++;
                Thread.sleep(100);
		publish(sportsman);
                setProgress(counter);
	    }
	    return null;
	}
	
	@Override
	protected void process(List<SportsmanDTO> items) {
	    for (SportsmanDTO i : items) {
                sportsmanTableModel.addSportsman(i);
	    }
	}

        @Override
        protected void done() {
            sportsmans_load.setEnabled(true);
            sportsmans_progress.setValue(100);
            sportsmansSwingWorker = null;
        }
    }
    
    private void setUp() throws Exception {
        
    }

    public GUI() throws MalformedURLException {
        
        try {
            setUp();
        } catch (Exception ex) {
	    String msg = "Application setup failed.";
            LOGGER.log(Level.SEVERE, msg, ex);
        }
          
        client = new SportEventClient();
        
	initComponents();
     
        
	        
        gradesSwingWorker = new GradesSwingWorker();
        gradesSwingWorker.addPropertyChangeListener(gradesProgressListener);
        gradesSwingWorker.execute();
        
        eventsSwingWorker = new EventsSwingWorker();
        eventsSwingWorker.addPropertyChangeListener(eventsProgressListener);
        eventsSwingWorker.execute();
        
        sportsmansSwingWorker = new SportsmansSwingWorker();
        sportsmansSwingWorker.addPropertyChangeListener(sportsmansProgressListener);
        sportsmansSwingWorker.execute();
    }
    
    /* SwingWorkers Progress Linsteners */
    private PropertyChangeListener gradesProgressListener = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("progress")) {
                grades_progress.setValue((Integer) evt.getNewValue());
            }
        }
    };
    
    private PropertyChangeListener eventsProgressListener = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("progress")) {
                events_progress.setValue((Integer) evt.getNewValue());
            }
        }
    };
    
    private PropertyChangeListener sportsmansProgressListener = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("progress")) {
                sportsmans_progress.setValue((Integer) evt.getNewValue());
            }
        }
    };
    
    public Vector<String> getNamesOfAllSports(){
        List <SportDTO> sports =client.sportManagergetAll();
        Vector <String> names = new Vector <String>();
        for(SportDTO sport : sports){
            names.add(sport.getSportId() + " " + sport.getName());
        }
        return names;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialog_grades = new javax.swing.JDialog();
        dialog_grades_eventLabel = new javax.swing.JLabel();
        dialog_grades_eventInput = new javax.swing.JTextField();
        dialog_grades_eventHelper = new javax.swing.JLabel();
        dialog_grades_sportsmanLabel = new javax.swing.JLabel();
        dialog_grades_sportsmanInput = new javax.swing.JTextField();
        dialog_grades_sportsmanHelper = new javax.swing.JLabel();
        dialog_grades_gradeLabel = new javax.swing.JLabel();
        dialog_grades_gradeInput = new javax.swing.JTextField();
        dialog_grades_cancel = new javax.swing.JButton();
        dialog_grades_submit = new javax.swing.JButton();
        dialog_events = new javax.swing.JDialog();
        dialog_events_idLabel = new javax.swing.JLabel();
        dialog_events_idInput = new javax.swing.JTextField();
        dialog_events_sportLabel = new javax.swing.JLabel();
        dialog_events_nameLabel = new javax.swing.JLabel();
        dialog_events_nameInput = new javax.swing.JTextField();
        dialog_events_heightLabel = new javax.swing.JLabel();
        dialog_events_heightInput = new javax.swing.JTextField();
        dialog_events_cancel = new javax.swing.JButton();
        dialog_events_submit = new javax.swing.JButton();
        dialog_events_sportInput = new javax.swing.JComboBox();
        dialog_sportsmans = new javax.swing.JDialog();
        dialog_sportsmans_idLabel = new javax.swing.JLabel();
        dialog_sportsmans_idInput = new javax.swing.JTextField();
        dialog_sportsmans_nameLabel = new javax.swing.JLabel();
        dialog_sportsmans_nameInput = new javax.swing.JTextField();
        dialog_sportsmans_submit = new javax.swing.JButton();
        dialog_sportsmans_cancel = new javax.swing.JButton();
        dialog_sportsmans_dateOfBirthLabel = new javax.swing.JLabel();
        dialog_sportsmans_dateOfBirthInput = new javax.swing.JTextField();
        dialog_sportsmans_lastnameLabel = new javax.swing.JLabel();
        dialog_sportsmans_lastnameInput = new javax.swing.JTextField();
        header = new javax.swing.JPanel();
        header_title = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        grades = new javax.swing.JPanel();
        grades_scroll = new javax.swing.JScrollPane();
        grades_table = new javax.swing.JTable();
        grades_add = new javax.swing.JButton();
        grades_title = new javax.swing.JLabel();
        grades_load = new javax.swing.JButton();
        grades_progress = new javax.swing.JProgressBar();
        events = new javax.swing.JPanel();
        events_scroll = new javax.swing.JScrollPane();
        events_table = new javax.swing.JTable();
        events_add = new javax.swing.JButton();
        events_update = new javax.swing.JButton();
        events_delete = new javax.swing.JButton();
        events_title = new javax.swing.JLabel();
        events_use = new javax.swing.JButton();
        events_load = new javax.swing.JButton();
        events_progress = new javax.swing.JProgressBar();
        sportsmans = new javax.swing.JPanel();
        sportsmans_scroll = new javax.swing.JScrollPane();
        sportsmans_table = new javax.swing.JTable();
        sportsmans_add = new javax.swing.JButton();
        sportsmans_update = new javax.swing.JButton();
        sportsmans_delete = new javax.swing.JButton();
        sportsmans_title = new javax.swing.JLabel();
        sportsmans_use = new javax.swing.JButton();
        sportsmans_load = new javax.swing.JButton();
        sportsmans_progress = new javax.swing.JProgressBar();

        dialog_grades.setResizable(false);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Bundle"); // NOI18N
        dialog_grades_eventLabel.setText(bundle.getString("GUI.dialog_grades_eventLabel.text_1")); // NOI18N

        dialog_grades_eventInput.setEnabled(false);

        dialog_grades_eventHelper.setText(bundle.getString("GUI.dialog_grades_eventHelper.text_1")); // NOI18N

        dialog_grades_sportsmanLabel.setText(bundle.getString("GUI.dialog_grades_sportsmanLabel.text_1")); // NOI18N

        dialog_grades_sportsmanInput.setEnabled(false);

        dialog_grades_sportsmanHelper.setText(bundle.getString("GUI.dialog_grades_sportsmanHelper.text_1")); // NOI18N

        dialog_grades_gradeLabel.setText(bundle.getString("GUI.dialog_grades_gradeLabel.text_1")); // NOI18N

        dialog_grades_gradeInput.setPreferredSize(new java.awt.Dimension(200, 28));

        dialog_grades_cancel.setText(bundle.getString("GUI.dialog_grades_cancel.text_1")); // NOI18N
        dialog_grades_cancel.setActionCommand(bundle.getString("GUI.dialog_grades_cancel.actionCommand")); // NOI18N
        dialog_grades_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_grades_cancelActionPerformed(evt);
            }
        });

        dialog_grades_submit.setText(bundle.getString("GUI.dialog_grades_submit.text_1")); // NOI18N
        dialog_grades_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_grades_submitActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dialog_gradesLayout = new org.jdesktop.layout.GroupLayout(dialog_grades.getContentPane());
        dialog_grades.getContentPane().setLayout(dialog_gradesLayout);
        dialog_gradesLayout.setHorizontalGroup(
            dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_gradesLayout.createSequentialGroup()
                .add(11, 11, 11)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(dialog_grades_gradeLabel)
                    .add(dialog_grades_sportsmanLabel)
                    .add(dialog_grades_eventLabel))
                .add(12, 12, 12)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dialog_gradesLayout.createSequentialGroup()
                        .add(dialog_grades_cancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(dialog_grades_submit)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(dialog_grades_gradeInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(dialog_gradesLayout.createSequentialGroup()
                        .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, dialog_grades_sportsmanInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, dialog_grades_eventInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dialog_grades_eventHelper, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(dialog_grades_sportsmanHelper, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        dialog_gradesLayout.setVerticalGroup(
            dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_gradesLayout.createSequentialGroup()
                .add(31, 31, 31)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_grades_eventLabel)
                    .add(dialog_grades_eventInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dialog_grades_eventHelper))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_grades_sportsmanLabel)
                    .add(dialog_grades_sportsmanInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dialog_grades_sportsmanHelper))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_grades_gradeInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dialog_grades_gradeLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 46, Short.MAX_VALUE)
                .add(dialog_gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_grades_cancel)
                    .add(dialog_grades_submit))
                .add(23, 23, 23))
        );

        dialog_events.setResizable(false);

        dialog_events_idLabel.setText(bundle.getString("GUI.dialog_events_idLabel.text_1")); // NOI18N

        dialog_events_idInput.setEnabled(false);
        dialog_events_idInput.setPreferredSize(new java.awt.Dimension(200, 28));
        dialog_events_idInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_events_idInputActionPerformed(evt);
            }
        });

        dialog_events_sportLabel.setText(bundle.getString("GUI.dialog_events_sportLabel.text_1")); // NOI18N

        dialog_events_nameLabel.setText(bundle.getString("GUI.dialog_events_nameLabel.text_1")); // NOI18N

        dialog_events_nameInput.setPreferredSize(new java.awt.Dimension(200, 28));

        dialog_events_heightLabel.setText(bundle.getString("GUI.dialog_events_heightLabel.text_1")); // NOI18N

        dialog_events_heightInput.setPreferredSize(new java.awt.Dimension(200, 28));

        dialog_events_cancel.setText(bundle.getString("GUI.dialog_events_cancel.text_1")); // NOI18N
        dialog_events_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_events_cancelActionPerformed(evt);
            }
        });

        dialog_events_submit.setText(bundle.getString("GUI.dialog_events_submit.text_1")); // NOI18N
        dialog_events_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_events_submitActionPerformed(evt);
            }
        });

        dialog_events_sportInput.setModel(new javax.swing.DefaultComboBoxModel(getNamesOfAllSports()));
        dialog_events_sportInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_events_sportInputActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout dialog_eventsLayout = new org.jdesktop.layout.GroupLayout(dialog_events.getContentPane());
        dialog_events.getContentPane().setLayout(dialog_eventsLayout);
        dialog_eventsLayout.setHorizontalGroup(
            dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_eventsLayout.createSequentialGroup()
                .addContainerGap()
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dialog_eventsLayout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(dialog_events_submit)
                        .add(18, 18, 18)
                        .add(dialog_events_cancel))
                    .add(dialog_eventsLayout.createSequentialGroup()
                        .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_events_sportLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_events_idLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_events_nameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_events_heightLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dialog_events_nameInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .add(dialog_events_heightInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(dialog_eventsLayout.createSequentialGroup()
                                .add(dialog_events_idInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(dialog_events_sportInput, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        dialog_eventsLayout.setVerticalGroup(
            dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_eventsLayout.createSequentialGroup()
                .addContainerGap()
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_events_idLabel)
                    .add(dialog_events_idInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_events_sportLabel)
                    .add(dialog_events_sportInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_events_nameLabel)
                    .add(dialog_events_nameInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_events_heightLabel)
                    .add(dialog_events_heightInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 48, Short.MAX_VALUE)
                .add(dialog_eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dialog_events_cancel)
                    .add(dialog_events_submit))
                .addContainerGap())
        );

        dialog_sportsmans.setResizable(false);

        dialog_sportsmans_idLabel.setText(bundle.getString("GUI.dialog_sportsmans_idLabel.text_1")); // NOI18N

        dialog_sportsmans_idInput.setEnabled(false);
        dialog_sportsmans_idInput.setPreferredSize(new java.awt.Dimension(200, 28));
        dialog_sportsmans_idInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_sportsmans_idInputActionPerformed(evt);
            }
        });

        dialog_sportsmans_nameLabel.setText(bundle.getString("GUI.dialog_sportsmans_nameLabel.text_1")); // NOI18N

        dialog_sportsmans_nameInput.setPreferredSize(new java.awt.Dimension(200, 28));

        dialog_sportsmans_submit.setText(bundle.getString("GUI.dialog_sportsmans_submit.text_1")); // NOI18N
        dialog_sportsmans_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_sportsmans_submitActionPerformed(evt);
            }
        });

        dialog_sportsmans_cancel.setText(bundle.getString("GUI.dialog_sportsmans_cancel.text_1")); // NOI18N
        dialog_sportsmans_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_sportsmans_cancelActionPerformed(evt);
            }
        });

        dialog_sportsmans_dateOfBirthLabel.setText(bundle.getString("GUI.dialog_sportsmans_dateOfBirthLabel.text")); // NOI18N

        dialog_sportsmans_dateOfBirthInput.setPreferredSize(new java.awt.Dimension(200, 28));
        dialog_sportsmans_dateOfBirthInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialog_sportsmans_dateOfBirthInputActionPerformed(evt);
            }
        });

        dialog_sportsmans_lastnameLabel.setText(bundle.getString("GUI.dialog_sportsmans_lastnameLabel.text")); // NOI18N

        dialog_sportsmans_lastnameInput.setPreferredSize(new java.awt.Dimension(200, 28));

        org.jdesktop.layout.GroupLayout dialog_sportsmansLayout = new org.jdesktop.layout.GroupLayout(dialog_sportsmans.getContentPane());
        dialog_sportsmans.getContentPane().setLayout(dialog_sportsmansLayout);
        dialog_sportsmansLayout.setHorizontalGroup(
            dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_sportsmansLayout.createSequentialGroup()
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dialog_sportsmansLayout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(dialog_sportsmans_cancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dialog_sportsmans_submit))
                    .add(dialog_sportsmansLayout.createSequentialGroup()
                        .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(dialog_sportsmansLayout.createSequentialGroup()
                                    .add(59, 59, 59)
                                    .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_sportsmans_nameLabel)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_sportsmans_idLabel)))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_sportsmans_lastnameLabel))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, dialog_sportsmans_dateOfBirthLabel))
                        .add(18, 18, 18)
                        .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dialog_sportsmans_lastnameInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(dialog_sportsmans_dateOfBirthInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(dialog_sportsmans_nameInput, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .add(dialog_sportsmansLayout.createSequentialGroup()
                                .add(dialog_sportsmans_idInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        dialog_sportsmansLayout.setVerticalGroup(
            dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dialog_sportsmansLayout.createSequentialGroup()
                .addContainerGap()
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_sportsmans_idLabel)
                    .add(dialog_sportsmans_idInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_sportsmans_nameLabel)
                    .add(dialog_sportsmans_nameInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_sportsmans_lastnameInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dialog_sportsmans_lastnameLabel))
                .add(10, 10, 10)
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_sportsmans_dateOfBirthLabel)
                    .add(dialog_sportsmans_dateOfBirthInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                .add(dialog_sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dialog_sportsmans_submit)
                    .add(dialog_sportsmans_cancel))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("com/muni/fi/pa165/gui/Bundle"); // NOI18N
        setTitle(bundle1.getString("GUI.title")); // NOI18N

        header.setPreferredSize(new java.awt.Dimension(1085, 40));

        header_title.setFont(header_title.getFont().deriveFont(header_title.getFont().getStyle() | java.awt.Font.BOLD, header_title.getFont().getSize()+12));
        header_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header_title.setText("SPORT EVENTS");

        org.jdesktop.layout.GroupLayout headerLayout = new org.jdesktop.layout.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(headerLayout.createSequentialGroup()
                .addContainerGap()
                .add(header_title, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                .add(388, 388, 388))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(header_title, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        grades_table.setModel(new com.muni.fi.pa165.gui.GradeTableModel());
        grades_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumn grades_col_eventid = grades_table.getColumnModel().getColumn(0);
        grades_col_eventid.setPreferredWidth(300);
        //grades_col_eventid.setMaxWidth(100);
        TableColumn grades_col_event = grades_table.getColumnModel().getColumn(1);
        grades_col_event.setPreferredWidth(300);
        //grades_col_event.setMaxWidth(200);
        TableColumn grades_col_sportsmanid = grades_table.getColumnModel().getColumn(2);
        grades_col_sportsmanid.setPreferredWidth(300);
        //grades_col_sportsmanid.setMaxWidth(200);
        //TableColumn grades_col_sportsman = grades_table.getColumnModel().getColumn(3);
        //grades_col_sportsman.setPreferredWidth(100);
        //grades_col_sportsman.setMaxWidth(50);
        //TableColumn grades_col_from = grades_table.getColumnModel().getColumn(4);
        //grades_col_from.setPreferredWidth(100);
        //grades_col_from.setMaxWidth(50);
        //TableColumn grades_col_to = grades_table.getColumnModel().getColumn(5);
        grades_scroll.setViewportView(grades_table);

        grades_add.setText(bundle.getString("GUI.grades_add.text_1")); // NOI18N
        grades_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grades_addActionPerformed(evt);
            }
        });

        grades_title.setFont(grades_title.getFont().deriveFont(grades_title.getFont().getStyle() | java.awt.Font.BOLD, grades_title.getFont().getSize()+6));
        grades_title.setText(bundle.getString("GUI.grades_title.text_1")); // NOI18N

        grades_load.setText(bundle.getString("GUI.grades_load.text_1")); // NOI18N
        grades_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grades_loadActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout gradesLayout = new org.jdesktop.layout.GroupLayout(grades);
        grades.setLayout(gradesLayout);
        gradesLayout.setHorizontalGroup(
            gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, gradesLayout.createSequentialGroup()
                .addContainerGap()
                .add(grades_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1002, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, grades_add, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, grades_load, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, grades_progress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(grades_title, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
        );
        gradesLayout.setVerticalGroup(
            gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(gradesLayout.createSequentialGroup()
                .addContainerGap()
                .add(gradesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(gradesLayout.createSequentialGroup()
                        .add(grades_title)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(grades_add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 64, Short.MAX_VALUE)
                        .add(grades_load)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(grades_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(grades_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        events_table.setModel(new com.muni.fi.pa165.gui.EventTableModel());
        events_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumn events_col_id = events_table.getColumnModel().getColumn(0);
        events_col_id.setMaxWidth(40);
        TableColumn events_col_firstname = events_table.getColumnModel().getColumn(1);
        TableColumn events_col_lastname = events_table.getColumnModel().getColumn(2);
        TableColumn events_col_birth = events_table.getColumnModel().getColumn(3);
        events_col_birth.setPreferredWidth(100);
        events_col_birth.setMaxWidth(100);
        //TableColumn events_col_email = events_table.getColumnModel().getColumn(4);
        events_scroll.setViewportView(events_table);

        events_add.setText(bundle.getString("GUI.events_add.text_1")); // NOI18N
        events_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                events_addActionPerformed(evt);
            }
        });

        events_update.setText(bundle.getString("GUI.events_update.text_1")); // NOI18N
        events_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                events_updateActionPerformed(evt);
            }
        });

        events_delete.setText(bundle.getString("GUI.events_delete.text_1")); // NOI18N
        events_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                events_deleteActionPerformed(evt);
            }
        });

        events_title.setFont(events_title.getFont().deriveFont(events_title.getFont().getStyle() | java.awt.Font.BOLD, events_title.getFont().getSize()+6));
        events_title.setText(bundle.getString("GUI.events_title.text_1")); // NOI18N

        events_use.setText(bundle.getString("GUI.events_use.text_1")); // NOI18N
        events_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                events_useActionPerformed(evt);
            }
        });

        events_load.setText(bundle.getString("GUI.events_load.text_1")); // NOI18N
        events_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                events_loadActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout eventsLayout = new org.jdesktop.layout.GroupLayout(events);
        events.setLayout(eventsLayout);
        eventsLayout.setHorizontalGroup(
            eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, eventsLayout.createSequentialGroup()
                .addContainerGap()
                .add(events_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1004, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_load, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_use, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_delete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_update, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_add, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(events_progress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, events_title, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26))
        );
        eventsLayout.setVerticalGroup(
            eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(eventsLayout.createSequentialGroup()
                .addContainerGap()
                .add(eventsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(eventsLayout.createSequentialGroup()
                        .add(events_title)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(events_add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(events_update)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(events_delete)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(events_use)
                        .add(18, 45, Short.MAX_VALUE)
                        .add(events_load)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(events_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(events_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        sportsmans_table.setModel(new com.muni.fi.pa165.gui.SportsmanTableModel());
        sportsmans_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumn sportsmans_col_id = sportsmans_table.getColumnModel().getColumn(0);
        sportsmans_col_id.setPreferredWidth(100);
        TableColumn sportsmans_col_model = sportsmans_table.getColumnModel().getColumn(1);
        sportsmans_col_model.setPreferredWidth(300);
        TableColumn sportsmans_col_plate = sportsmans_table.getColumnModel().getColumn(2);
        sportsmans_col_plate.setPreferredWidth(120);
        TableColumn sportsmans_col_fee = sportsmans_table.getColumnModel().getColumn(3);
        sportsmans_col_fee.setPreferredWidth(100);
        sportsmans_scroll.setViewportView(sportsmans_table);

        sportsmans_add.setText(bundle.getString("GUI.sportsmans_add.text_1")); // NOI18N
        sportsmans_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportsmans_addActionPerformed(evt);
            }
        });

        sportsmans_update.setText(bundle.getString("GUI.sportsmans_update.text_1")); // NOI18N
        sportsmans_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportsmans_updateActionPerformed(evt);
            }
        });

        sportsmans_delete.setText(bundle.getString("GUI.sportsmans_delete.text_1")); // NOI18N
        sportsmans_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportsmans_deleteActionPerformed(evt);
            }
        });

        sportsmans_title.setFont(sportsmans_title.getFont().deriveFont(sportsmans_title.getFont().getStyle() | java.awt.Font.BOLD, sportsmans_title.getFont().getSize()+6));
        sportsmans_title.setText(bundle.getString("GUI.sportsmans_title.text_1")); // NOI18N

        sportsmans_use.setText(bundle.getString("GUI.sportsmans_use.text_1")); // NOI18N
        sportsmans_use.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportsmans_useActionPerformed(evt);
            }
        });

        sportsmans_load.setText(bundle.getString("GUI.sportsmans_load.text_1")); // NOI18N
        sportsmans_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sportsmans_loadActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout sportsmansLayout = new org.jdesktop.layout.GroupLayout(sportsmans);
        sportsmans.setLayout(sportsmansLayout);
        sportsmansLayout.setHorizontalGroup(
            sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmansLayout.createSequentialGroup()
                .addContainerGap()
                .add(sportsmans_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1000, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(sportsmans_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_add, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_update, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_delete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_use, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_load, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sportsmans_title, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sportsmansLayout.setVerticalGroup(
            sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(sportsmansLayout.createSequentialGroup()
                .addContainerGap()
                .add(sportsmansLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sportsmansLayout.createSequentialGroup()
                        .add(sportsmans_title)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sportsmans_add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sportsmans_update)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sportsmans_delete)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sportsmans_use)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(sportsmans_load)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sportsmans_progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(sportsmans_scroll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout contentLayout = new org.jdesktop.layout.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentLayout.createSequentialGroup()
                .addContainerGap()
                .add(contentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sportsmans, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(events, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(grades, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(contentLayout.createSequentialGroup()
                .addContainerGap()
                .add(grades, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(events, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sportsmans, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(content, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dialog_sportsmans_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_sportsmans_cancelActionPerformed
	dialog_sportsmans.setVisible(false);
    }//GEN-LAST:event_dialog_sportsmans_cancelActionPerformed

    private void dialog_sportsmans_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_sportsmans_submitActionPerformed
        SportsmanDTO sportsman = new SportsmanDTO();
          
	sportsman.setName(dialog_sportsmans_nameInput.getText());
        sportsman.setLastname(dialog_sportsmans_lastnameInput.getText());
         try {
                gregory.setTime(sdfSource.parse(dialog_sportsmans_dateOfBirthInput.getText()));
                sportsman.setDateOfBirth(calendar);
                try {
                    calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
                    
                } catch (DatatypeConfigurationException ex) {
                    Logger.getLogger(SportsmanTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
        } catch(ParseException e) {
            String msg = "Sportsman date of birth is in wrong format";
            LOGGER.log(Level.INFO, msg);
            JOptionPane.showMessageDialog(new JFrame(),msg, "Dialog",JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            /* Sportsman ID */
            if (dialog_sportsmans_idInput.getText().equals("")) { // Add
                LOGGER.log(Level.INFO, "Adding sportsman");
                client.sportsmanManagersave(sportsman);
                
                //sportsmanTableModel.addSportsman(sportsman);
            } else { // Update
                LOGGER.log(Level.INFO, "Updating sportsman");
                Long sportsmanId = Long.valueOf(dialog_sportsmans_idInput.getText());
                sportsman.setSportsmanId(sportsmanId);
                //SportsmanDTO sportsmanCached = client.sportsmanManagerfindById(sportsmanId);
                SportsmanDTO sportsmanCached = sportsman;
                client.sportsmanManagerupdate(sportsman);
                sportsmanTableModel.removeSportsman(sportsmanCached);
                sportsmanTableModel.addSportsman(sportsman);
            }
            dialog_sportsmans.setVisible(false);
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
                sportsmans_load.setEnabled(false);
                sportsmans_progress.setValue(0);
                sportsmanTableModel.clear();
                sportsmansSwingWorker = new SportsmansSwingWorker();
                sportsmansSwingWorker.addPropertyChangeListener(sportsmansProgressListener);
                sportsmansSwingWorker.execute();
    }//GEN-LAST:event_dialog_sportsmans_submitActionPerformed

    private void grades_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grades_addActionPerformed
        
        dialog_grades_eventInput.setText("");
        dialog_grades_eventHelper.setText(java.util.ResourceBundle.getBundle("Bundle").getString("dialog_grades_eventHelper.text_1"));
        dialog_grades_sportsmanInput.setText("");
        dialog_grades_sportsmanHelper.setText(java.util.ResourceBundle.getBundle("Bundle").getString("dialog_grades_sportsmanHelper.text_1"));
        dialog_grades_gradeInput.setText("");
        dialog_grades.pack();
        dialog_grades.setLocationRelativeTo(null);
        dialog_grades.setVisible(true);
    }//GEN-LAST:event_grades_addActionPerformed

    private void events_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_events_addActionPerformed
        dialog_events_idInput.setText("");
        dialog_events_nameInput.setText("");
        dialog_events_heightInput.setText("");
        dialog_events.pack();
        dialog_events.setLocationRelativeTo(null);
        dialog_events.setVisible(true);
    }//GEN-LAST:event_events_addActionPerformed

    private void sportsmans_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportsmans_addActionPerformed
        dialog_sportsmans_idInput.setText("");
        dialog_sportsmans_nameInput.setText("");
        dialog_sportsmans_dateOfBirthInput.setText("");
        dialog_sportsmans_lastnameInput.setText("");
        dialog_sportsmans.pack();
        dialog_sportsmans.setLocationRelativeTo(null);
        dialog_sportsmans.setVisible(true);
    }//GEN-LAST:event_sportsmans_addActionPerformed

    private void sportsmans_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportsmans_updateActionPerformed
	action = "update";
        
        Long sportsman_id = null;
        try {
            sportsman_id = (Long) sportsmanTableModel.getValueAt(sportsmans_table.getSelectedRow(), 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        
        SportsmanDTO sportsman = client.sportsmanManagerfindById(sportsman_id);
        
        dialog_sportsmans_idInput.setText(String.valueOf(sportsman.getSportsmanId()));
        dialog_sportsmans_nameInput.setText(sportsman.getName());
        dialog_sportsmans_dateOfBirthInput.setText(sdfSource.format((sportsman.getDateOfBirth()).toGregorianCalendar().getTime()));
        dialog_sportsmans_lastnameInput.setText(String.valueOf(sportsman.getLastname()));
        dialog_sportsmans.pack();
        dialog_sportsmans.setLocationRelativeTo(null);
        dialog_sportsmans.setVisible(true);
    }//GEN-LAST:event_sportsmans_updateActionPerformed

    private void dialog_sportsmans_idInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_sportsmans_idInputActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_dialog_sportsmans_idInputActionPerformed

    private void dialog_events_idInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_events_idInputActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_dialog_events_idInputActionPerformed

    private void dialog_events_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_events_submitActionPerformed
        EventDTO event = new EventDTO();
       	event.setName(dialog_events_nameInput.getText());
      	               
       try {
            if (!(dialog_events_heightInput.getText()).equals("")) {
                              
                     gregory.setTime(sdfSource.parse(dialog_events_heightInput.getText()));
                 
                try {
                    calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
                    event.setDateOfEvent(calendar);
                } catch (DatatypeConfigurationException ex) {
                    Logger.getLogger(SportsmanTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
               
        } catch(ParseException e) {
            String msg = "Event data wrong format";
            LOGGER.log(Level.SEVERE, msg);
            JOptionPane.showMessageDialog(new JFrame(),msg, "Dialog",JOptionPane.ERROR_MESSAGE);
            
        }
       String sport = (String) dialog_events_sportInput.getSelectedItem();
       String sportid = sport.substring(0, sport.indexOf(" "));
       Long sportId = Long.valueOf(sportid);
       SportDTO sportforevent = new SportDTO();
       sportforevent.setSportId(sportId);
       event.setSport(sportforevent);
        try {
            /* Event ID */
            if (dialog_events_idInput.getText().equals("")) { // Add
                LOGGER.log(Level.INFO, "Adding event");
                client.eventManagersave(event);
                
            } else { // Update
                LOGGER.log(Level.INFO, "Updating event");
                Long eventId = Long.valueOf(dialog_events_idInput.getText());
                event.setEventId(eventId);
                EventDTO eventCached = client.eventManagerfindById(eventId);
                client.eventManagerupdate(event);
                eventTableModel.removeEvent(eventCached);
                eventTableModel.addEvent(event);
                
            }
            dialog_events.setVisible(false);
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
                events_load.setEnabled(false);
                events_progress.setValue(0);
                eventTableModel.clear();
                eventsSwingWorker = new EventsSwingWorker();
                eventsSwingWorker.addPropertyChangeListener(eventsProgressListener);
                eventsSwingWorker.execute();
         
    }//GEN-LAST:event_dialog_events_submitActionPerformed

    private void dialog_events_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_events_cancelActionPerformed
	dialog_events.setVisible(false);
    }//GEN-LAST:event_dialog_events_cancelActionPerformed

    private void dialog_grades_idInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_grades_idInputActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_dialog_grades_idInputActionPerformed

    private void dialog_grades_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_grades_submitActionPerformed
	GradeDTO grade = new GradeDTO();
        EventDTO event = new EventDTO();
        GradeIdDTO pk = new GradeIdDTO();
        SportsmanDTO sportsman = new SportsmanDTO();
        event.setEventId(Long.parseLong(dialog_grades_eventInput.getText()));
        grade.setEvent(event);
        pk.setEvent(event);
        sportsman.setSportsmanId(Long.parseLong(dialog_grades_sportsmanInput.getText()));
        grade.setSportsman(sportsman);
        pk.setSportsman(sportsman);
        grade.setPk(pk);
        
        try {
           grade.setGrade(Integer.parseInt(dialog_grades_gradeInput.getText()));
        } catch (IllegalArgumentException ex) {
            String msg = "Event-sportsman grade is in wrong format";
            LOGGER.log(Level.SEVERE, msg);
            JOptionPane.showMessageDialog(new JFrame(),msg, "Dialog",JOptionPane.ERROR_MESSAGE);
        }
   
        
            /* Grade ID */
            List <GradeDTO> grades = client.gradeManagerfindAll();
            boolean notexist = true;
            for(GradeDTO gradetemp : grades){
                if (gradetemp.getSportsman().getSportsmanId()== sportsman.getSportsmanId())
                    if (gradetemp.getEvent().getEventId()== event.getEventId())
                            notexist=false;
            }    
         try {
            if (notexist) { // Add
                LOGGER.log(Level.INFO, "Adding event-sportsman");
                client.gradeManagersave(grade);
                grades_load.setEnabled(false);
                grades_progress.setValue(0);
                gradeTableModel.clear();
                gradesSwingWorker = new GradesSwingWorker();
                gradesSwingWorker.addPropertyChangeListener(gradesProgressListener);
                gradesSwingWorker.execute();
            } else { // Update
                LOGGER.log(Level.INFO, "Updating event-sportsman");
                client.gradeManagerupdate(grade);
                gradeTableModel.addGrade(grade);
            }
            dialog_grades.setVisible(false);
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
    }//GEN-LAST:event_dialog_grades_submitActionPerformed

    private void dialog_grades_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_grades_cancelActionPerformed
	dialog_grades.setVisible(false);
    }//GEN-LAST:event_dialog_grades_cancelActionPerformed

    private void dialog_grades_calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_grades_calculateActionPerformed
	        
    }//GEN-LAST:event_dialog_grades_calculateActionPerformed

    private void events_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_events_useActionPerformed
        Long id = (Long) eventTableModel.getValueAt(events_table.getSelectedRow(), 0);
        EventDTO c = client.eventManagerfindById(id); 
        dialog_grades_eventInput.setText(String.valueOf(id));
        dialog_grades_eventHelper.setText(String.valueOf(c.toString()));
    }//GEN-LAST:event_events_useActionPerformed

    private void sportsmans_useActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportsmans_useActionPerformed
        Long id = (Long) sportsmanTableModel.getValueAt(sportsmans_table.getSelectedRow(), 0);
        SportsmanDTO c = client.sportsmanManagerfindById(id); 
        dialog_grades_sportsmanInput.setText(String.valueOf(c.getSportsmanId()));
        dialog_grades_sportsmanHelper.setText(String.valueOf(c.toString()));
    }//GEN-LAST:event_sportsmans_useActionPerformed

    private void grades_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grades_loadActionPerformed
        if (gradesSwingWorker != null) {
            throw new IllegalStateException("Operation is already in progress");
        }
        grades_load.setEnabled(false);
        grades_progress.setValue(0);
        gradeTableModel.clear();
        gradesSwingWorker = new GradesSwingWorker();
        gradesSwingWorker.addPropertyChangeListener(gradesProgressListener);
        gradesSwingWorker.execute();
    }//GEN-LAST:event_grades_loadActionPerformed

    private void events_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_events_loadActionPerformed
        if (eventsSwingWorker != null) {
            throw new IllegalStateException("Operation is already in progress");
        }
        events_load.setEnabled(false);
        events_progress.setValue(0);
        eventTableModel.clear();
        eventsSwingWorker = new EventsSwingWorker();
        eventsSwingWorker.addPropertyChangeListener(eventsProgressListener);
        eventsSwingWorker.execute();
    }//GEN-LAST:event_events_loadActionPerformed

    private void sportsmans_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportsmans_loadActionPerformed
        if (sportsmansSwingWorker != null) {
            throw new IllegalStateException("Operation is already in progress");
        }
        sportsmans_load.setEnabled(false);
        sportsmans_progress.setValue(0);
        sportsmanTableModel.clear();
        sportsmansSwingWorker = new SportsmansSwingWorker();
        sportsmansSwingWorker.addPropertyChangeListener(sportsmansProgressListener);
        sportsmansSwingWorker.execute();
    }//GEN-LAST:event_sportsmans_loadActionPerformed

    private void sportsmans_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sportsmans_deleteActionPerformed
        Long sportsman_id = null;
        try {
            sportsman_id = (Long) sportsmanTableModel.getValueAt(sportsmans_table.getSelectedRow(), 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            String msg = "No row selected";
            LOGGER.log(Level.INFO, msg);
        }
        
        SportsmanDTO sportsman = client.sportsmanManagerfindById(sportsman_id);
        try {
//            for (GradeDTO grade : client.gradeManagerfindBySportsmanId(sportsman_id)) {
//                client.gradeManagerdelete(grade.getEvent().getEventId(), sportsman_id);
//                gradeTableModel.removeGrade(grade);
//	    }
            client.sportsmanManagerdelete(sportsman);
            sportsmanTableModel.removeSportsman(sportsman);
        } catch (Exception ex) {
            String msg = "Deleting failed";
            LOGGER.log(Level.INFO, msg);
        }
    }//GEN-LAST:event_sportsmans_deleteActionPerformed

    private void events_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_events_updateActionPerformed
        Long event_id = (Long) eventTableModel.getValueAt(events_table.getSelectedRow(), 0);
        EventDTO event = client.eventManagerfindById(event_id);
        dialog_events_idInput.setText(String.valueOf(event.getEventId()));
        
        dialog_events_nameInput.setText(String.valueOf(event.getName()));
        dialog_events_sportInput.setSelectedItem(event.getSport().getSportId()+" "+event.getSport().getName());
        dialog_events_heightInput.setText(sdfSource.format((event.getDateOfEvent()).toGregorianCalendar().getTime()));
        
        dialog_events.pack();
        dialog_events.setLocationRelativeTo(null);
        dialog_events.setVisible(true);
    }//GEN-LAST:event_events_updateActionPerformed

    private void events_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_events_deleteActionPerformed
        Long event_id = null;
        try {
            event_id = (Long) eventTableModel.getValueAt(events_table.getSelectedRow(), 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            String msg = "No row selected";
            LOGGER.log(Level.INFO, msg);
        }
        
        EventDTO event = client.eventManagerfindById(event_id);
        try {
//            for (GradeDTO grade : client.gradeManagerfindByEventId(event_id)) {
//                client.gradeManagerdelete(event_id, grade.getSportsman().getSportsmanId());
//                gradeTableModel.removeGrade(grade);
//	    }
            client.eventManagerdelete(event);
            eventTableModel.removeEvent(event);
        } catch (Exception ex) {
            String msg = "Deleting failed";
            LOGGER.log(Level.INFO, msg);
        }
    }//GEN-LAST:event_events_deleteActionPerformed

    private void dialog_sportsmans_dateOfBirthInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_sportsmans_dateOfBirthInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dialog_sportsmans_dateOfBirthInputActionPerformed

    private void dialog_events_sportInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialog_events_sportInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dialog_events_sportInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/*
	 * Set the Nimbus look and feel
	 */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable() {

	    public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JDialog dialog_events;
    private javax.swing.JButton dialog_events_cancel;
    private javax.swing.JTextField dialog_events_heightInput;
    private javax.swing.JLabel dialog_events_heightLabel;
    private javax.swing.JTextField dialog_events_idInput;
    private javax.swing.JLabel dialog_events_idLabel;
    private javax.swing.JTextField dialog_events_nameInput;
    private javax.swing.JLabel dialog_events_nameLabel;
    private javax.swing.JComboBox dialog_events_sportInput;
    private javax.swing.JLabel dialog_events_sportLabel;
    private javax.swing.JButton dialog_events_submit;
    private javax.swing.JDialog dialog_grades;
    private javax.swing.JButton dialog_grades_cancel;
    private javax.swing.JLabel dialog_grades_eventHelper;
    private javax.swing.JTextField dialog_grades_eventInput;
    private javax.swing.JLabel dialog_grades_eventLabel;
    private javax.swing.JTextField dialog_grades_gradeInput;
    private javax.swing.JLabel dialog_grades_gradeLabel;
    private javax.swing.JLabel dialog_grades_sportsmanHelper;
    private javax.swing.JTextField dialog_grades_sportsmanInput;
    private javax.swing.JLabel dialog_grades_sportsmanLabel;
    private javax.swing.JButton dialog_grades_submit;
    private javax.swing.JDialog dialog_sportsmans;
    private javax.swing.JButton dialog_sportsmans_cancel;
    private javax.swing.JTextField dialog_sportsmans_dateOfBirthInput;
    private javax.swing.JLabel dialog_sportsmans_dateOfBirthLabel;
    private javax.swing.JTextField dialog_sportsmans_idInput;
    private javax.swing.JLabel dialog_sportsmans_idLabel;
    private javax.swing.JTextField dialog_sportsmans_lastnameInput;
    private javax.swing.JLabel dialog_sportsmans_lastnameLabel;
    private javax.swing.JTextField dialog_sportsmans_nameInput;
    private javax.swing.JLabel dialog_sportsmans_nameLabel;
    private javax.swing.JButton dialog_sportsmans_submit;
    private javax.swing.JPanel events;
    private javax.swing.JButton events_add;
    private javax.swing.JButton events_delete;
    private javax.swing.JButton events_load;
    private javax.swing.JProgressBar events_progress;
    private javax.swing.JScrollPane events_scroll;
    private javax.swing.JTable events_table;
    private javax.swing.JLabel events_title;
    private javax.swing.JButton events_update;
    private javax.swing.JButton events_use;
    private javax.swing.JPanel grades;
    private javax.swing.JButton grades_add;
    private javax.swing.JButton grades_load;
    private javax.swing.JProgressBar grades_progress;
    private javax.swing.JScrollPane grades_scroll;
    private javax.swing.JTable grades_table;
    private javax.swing.JLabel grades_title;
    private javax.swing.JPanel header;
    private javax.swing.JLabel header_title;
    private javax.swing.JPanel sportsmans;
    private javax.swing.JButton sportsmans_add;
    private javax.swing.JButton sportsmans_delete;
    private javax.swing.JButton sportsmans_load;
    private javax.swing.JProgressBar sportsmans_progress;
    private javax.swing.JScrollPane sportsmans_scroll;
    private javax.swing.JTable sportsmans_table;
    private javax.swing.JLabel sportsmans_title;
    private javax.swing.JButton sportsmans_update;
    private javax.swing.JButton sportsmans_use;
    // End of variables declaration//GEN-END:variables
}

