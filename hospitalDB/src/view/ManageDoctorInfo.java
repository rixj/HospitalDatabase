package view;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import controller.ControllerDoctor;
import model.Doctor;

public class ManageDoctorInfo {

    protected Shell shell;
    private Table table;
    private Text tblankid;
    private Text tid;
    private Text tname;
    private Text taddress;
    private Text tphone;
    private Text tdept;
    private ControllerDoctor cd = new ControllerDoctor();

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
/**
 * test the ManageDoctor window indepently of Main.java
 *         try {
            ManageDoctorInfo window = new ManageDoctorInfo();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
 */
    }

    private void FillData(){
        table.removeAll();
        for (Doctor d : cd.findAll()) {
            TableItem tableItem = new TableItem(table , SWT.NONE);
            tableItem.setText(new String[] {String.valueOf(d.getDocid()), d.getName(), d.getAddress(), d.getPhone(), d.getDept()});
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        FillData();
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
        shell.setText("Doctor Management");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
            	tid.setVisible(true);
            	tblankid.setVisible(false);
                TableItem[] selection = table.getSelection();
                int id = Integer.parseInt(selection[0].getText());
                Doctor d = cd.find(id);
                tid.setText(String.valueOf(d.getDocid()));
                tname.setText(d.getName());
                taddress.setText(d.getAddress());
                tphone.setText(d.getPhone());
                tdept.setText(d.getDept());
            }
        });
        table.setBounds(30, 30, 442, 213);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
        tblclmnId.setWidth(63);
        tblclmnId.setText("ID");

        TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
        tblclmnName.setWidth(100);
        tblclmnName.setText("Name");

        TableColumn tblclmnAdd = new TableColumn(table, SWT.NONE);
        tblclmnAdd.setWidth(100);
        tblclmnAdd.setText("Address");

        TableColumn tblclmnPhone = new TableColumn(table, SWT.NONE);
        tblclmnPhone.setWidth(100);
        tblclmnPhone.setText("Phone");

        TableColumn tblclmnDues = new TableColumn(table, SWT.NONE);
        tblclmnDues.setWidth(100);
        tblclmnDues.setText("Department");

        Label lid = new Label(shell, SWT.NONE);
        lid.setBounds(30, 290, 59, 14);
        lid.setText("ID");

        Label lname = new Label(shell, SWT.NONE);
        lname.setText("Name");
        lname.setBounds(30, 315, 59, 14);

        Label laddress = new Label(shell, SWT.NONE);
        laddress.setText("Address");
        laddress.setBounds(30, 340, 59, 14);

        Label lphone = new Label(shell, SWT.NONE);
        lphone.setText("Phone");
        lphone.setBounds(30, 365, 59, 14);

        Label ldues = new Label(shell, SWT.NONE);
        ldues.setText("Department");
        ldues.setBounds(30, 390, 80, 14);

        tid = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
        tid.setBounds(128, 290, 243, 19);
        tid.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
        tid.setVisible(false);
        
        tblankid = new Text(shell, SWT.BORDER);
        tblankid.setBounds(128, 290, 243, 19);

        tname = new Text(shell, SWT.BORDER);
        tname.setBounds(128, 315, 243, 19);

        taddress = new Text(shell, SWT.BORDER);
        taddress.setBounds(128, 340, 243, 19);

        tphone = new Text(shell, SWT.BORDER);
        tphone.setBounds(128, 366, 243, 19);

        tdept = new Text(shell, SWT.BORDER);
        tdept.setBounds(128, 390, 243, 19);

        Button btnClear = new Button(shell, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
            	tid.setVisible(false);
            	tblankid.setVisible(true);
                tblankid.setText("");
                tname.setText("");
                taddress.setText("");
                tphone.setText("");
                tdept.setText("");
            }
        });
        Button btnSave = new Button(shell, SWT.NONE);
        btnSave.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Doctor d = new Doctor();
                d.setDocid(Integer.parseInt(tblankid.getText()));
                d.setName(tname.getText()); 
                d.setAddress(taddress.getText());
                d.setPhone(tphone.getText());
                d.setDept(tdept.getText());
                if(cd.create(d)) {
                    ShowMessage("Saved successfully.");
                    FillData();
                } else {
                    ShowMessage("Save failed.");
                }
            }
        });
        
        Button btnDelete = new Button(shell, SWT.NONE);
        btnDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selection = table.getSelection();
                int id = Integer.parseInt(selection[0].getText());
                Doctor d = cd.find(id);
                cd.delete(d);
                FillData();
/**
 * Alternative dialog for opening the confirmation window 
 *               int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
              if(result == JOptionPane.YES_OPTION) {
                  TableItem[] selection = table.getSelection();
                  int id = Integer.parseInt(selection[0].getText());
                  Patient p = cd.find(id);
                  cd.delete(p);
                  FillData();
              }
 */
            }
        });
        
        
        Button btnUpdate = new Button(shell, SWT.NONE);
        btnUpdate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
            	Doctor d = new Doctor();
                d.setDocid(Integer.parseInt(tid.getText()));
                d.setName(tname.getText()); 
                d.setAddress(taddress.getText());
                d.setPhone(tphone.getText());
                d.setDept(tdept.getText());
                if(cd.edit(d)) {
                    ShowMessage("Updated successfully.");
                    FillData();
                } else {
                    ShowMessage( "Update failed.");
                }
            }
        });
        btnClear.setText("Clear");
        btnSave.setText("Save");
        btnDelete.setText("Delete");
        btnUpdate.setText("Update");
        btnClear.setBounds(291, 256, 80, 28);
        btnSave.setBounds(128, 440, 80, 28);
        btnDelete.setBounds(208, 440, 80, 28);
        btnUpdate.setBounds(288, 440, 80, 28);
    }
}
