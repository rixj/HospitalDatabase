package view;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controller.ControllerAppointment;
import model.Appointment;

public class ManageAppointmentInfo {

    protected Shell shell;
    private Table table;
    private Combo doctorDdl;
    private Combo patientDdl;
    private Appointment editAppointmentContainer;
    private Button btnCreate;
    private Button btnUpdate;
    private Button btnDelete;
    private Text tappid;
    private Text tdate;
    private Label lblappid;
	private ControllerAppointment ca = new ControllerAppointment();
    /**
     *  Button message handler
     */
    private void ShowMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }
    
    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            ManageAppointmentInfo window = new ManageAppointmentInfo();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void FillGrid(){
        table.removeAll();
        for (Appointment a : ca.findAllAppointment()) {
            TableItem tableItem = new TableItem(table , SWT.NONE);
            tableItem.setText(new String[] {String.valueOf(a.getAppid()), a.getDocname(), a.getPatname(), a.getDatetime()});
            tableItem.setData(a);           
        }
    }
    
    private void FillDropDowns(){
        for (String d : ca.findAllDoctors()) {
                doctorDdl.add(d);
        }
        
        for (String p : ca.findAllPatients()) {
            patientDdl.add(p);
        }
    }

    /**
     * Open the window.
     * @param desktopy 
     * @param desktopx 
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        FillGrid();
        FillDropDowns();
        shell.setLocation(390,150);
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(503, 522);
        shell.setText("Appointment Management");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                TableItem[] selection = table.getSelection();         
                editAppointmentContainer = (Appointment) selection[0].getData();
                tappid.setText(String.valueOf(editAppointmentContainer.getAppid()));
                doctorDdl.setText(editAppointmentContainer.getDocname() + " - " + String.valueOf(editAppointmentContainer.getDocid()));  
                patientDdl.setText(editAppointmentContainer.getPatname() + " - " + String.valueOf(editAppointmentContainer.getPatid())); 
                tdate.setText(editAppointmentContainer.getDatetime());
                btnUpdate.setVisible(true);
                btnCreate.setVisible(false);
                btnDelete.setVisible(true);
                tappid.setVisible(false);
                lblappid.setVisible(false);

            }
        });
        table.setBounds(30, 30, 442, 213);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn tblclmnID = new TableColumn(table, SWT.NONE);
        tblclmnID.setWidth(0);
        tblclmnID.setText("App ID");
        
        TableColumn tblclmnDocName= new TableColumn(table, SWT.NONE);
        tblclmnDocName.setWidth(130);
        tblclmnDocName.setText("Doctor Name");

        TableColumn tblclmnPatName = new TableColumn(table, SWT.NONE);
        tblclmnPatName.setWidth(130);
        tblclmnPatName.setText("Patient Name");

        TableColumn tblclmnAppDate = new TableColumn(table, SWT.NONE);
        tblclmnAppDate.setWidth(250);
        tblclmnAppDate.setText("AppDate");

        // labels
        
        lblappid = new Label(shell, SWT.NONE);
        lblappid.setBounds(30, 335, 55, 15);
        lblappid.setText("App Id");
        
        Label lid = new Label(shell, SWT.NONE);
        lid.setBounds(30, 335+27, 59, 14);
        lid.setText("Doctor");

        Label lname = new Label(shell, SWT.NONE);
        lname.setText("Patient");
        lname.setBounds(30, 335+27*2, 59, 14);

        Label ldues = new Label(shell, SWT.NONE);
        ldues.setText("App Date");
        ldues.setBounds(30, 335+27*3, 59, 14);
        
        // text boxes
        
        tappid = new Text(shell, SWT.BORDER);
        tappid.setBounds(128, 329, 240, 21);
        
        doctorDdl = new Combo(shell, SWT.NONE);
        doctorDdl.setBounds(128, 356, 243, 23);

        patientDdl = new Combo(shell, SWT.NONE);
        patientDdl.setBounds(128, 383, 243, 23);
        
        tdate = new Text(shell, SWT.BORDER);
        tdate.setBounds(128, 410, 240, 24);
        
        Button btnClear = new Button(shell, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
              doctorDdl.setText("");
              patientDdl.setText("");
              
              btnUpdate.setVisible(false);
              btnCreate.setVisible(true);
              btnDelete.setVisible(false);
              tappid.setVisible(true);
              lblappid.setVisible(true);
            }
        });
        
        btnCreate = new Button(shell, SWT.NONE);
        btnCreate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Appointment a = new Appointment();        
                //TODO
//                Random r = new Random();
//                tappid.setText(Integer.toString(r.nextInt(1000000000)+25000));
                a.setAppid(Integer.parseInt(tappid.getText()));
                a.setDocid(Integer.parseInt(doctorDdl.getText().split(" - ")[1]));
                a.setPatid(Integer.parseInt(patientDdl.getText().split(" - ")[1]));
                a.setDatetime(tdate.getText());
                if(ca.create(a)) {
                    ShowMessage("Saved successfully.");
                    FillGrid();
                    doctorDdl.setText("");
                    patientDdl.setText("");
                    
                    btnUpdate.setVisible(false);
                    btnCreate.setVisible(true);
                    btnDelete.setVisible(false);
                    tappid.setVisible(true);
                    lblappid.setVisible(true);
                    
                } else {
                    ShowMessage("Save failed.");
                }
            }
        });
        
        btnDelete = new Button(shell, SWT.NONE);
        btnDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
              TableItem[] selection = table.getSelection();
              int id = Integer.parseInt(selection[0].getText());
              Appointment a = ca.find(id);
              if (ca.delete(a)) {
                  FillGrid();
                  ShowMessage("Delete successful.");
              }
              
            }
        });
        
        
        btnUpdate = new Button(shell, SWT.NONE);
        btnUpdate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                Appointment a = new Appointment();
                a.setAppid(Integer.parseInt(tappid.getText()));
                a.setDocid(Integer.parseInt(doctorDdl.getText().split(" - ")[1]));
                a.setPatid(Integer.parseInt(patientDdl.getText().split(" - ")[1]));
                a.setDatetime(tdate.getText());
                if(ca.edit(a)) {
                    ShowMessage("Updated successfully.");
                    FillGrid();
                } else {
                    ShowMessage( "Update failed.");
                }
            }
        });
        btnClear.setText("Create");
        btnCreate.setText("Save");
        btnDelete.setText("Delete");
        btnUpdate.setText("Update");
        btnClear.setBounds(291, 285, 80, 28);
        btnCreate.setBounds(128, 440, 80, 28);
        btnDelete.setBounds(208, 440, 80, 28);
        btnUpdate.setBounds(288, 440, 80, 28);
        
        btnUpdate.setVisible(false);
        btnCreate.setVisible(true);
        btnDelete.setVisible(false);
    }
    
}
