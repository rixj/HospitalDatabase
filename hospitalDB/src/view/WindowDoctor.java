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

import controller.ControllerAppointment;
import controller.ControllerDoctor;
import model.Appointment;
import model.Doctor;

public class WindowDoctor {


	protected Shell shell;
	private ControllerDoctor cd = new ControllerDoctor();
	private ControllerAppointment ca = new ControllerAppointment();
	private Table table;
	Integer doctorid;
	private Text text;

    public WindowDoctor() { }
    public WindowDoctor(int docid) {
        this.doctorid = docid;
    }
    public Integer getDocid() {
        return doctorid;
    }
    public void setDocid(Integer doctorid) {
        this.doctorid = doctorid;
    }
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowDoctor window = new WindowDoctor();
			window.setDocid(122);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void FillGrid(){
		table.removeAll();
		for (Appointment a : ca.findAllforDoctor(doctorid)) {
			TableItem tableItem = new TableItem(table , SWT.NONE);
			tableItem.setText(new String[] {String.valueOf(a.getAppid()), a.getDatetime(), a.getPatname(), a.getIllname()});
			tableItem.setData(a);           
		}
	}


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(doctorid);
		shell.setLocation(350,100);
		shell.open();
		shell.layout();
		FillGrid();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
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
	 * Create contents of the window.
	 */
	protected void createContents(int docid) {
		shell = new Shell();
		shell.setSize(650, 600);
		shell.setText("Doctor");
		Doctor d = cd.find(docid);
		
		// welcome text
		Label lblappts = new Label(shell, SWT.BORDER);
		lblappts.setText("Appointments");
		lblappts.setBounds(87, 88, 225, 16);
		Label lblWelcome = new Label(shell, SWT.BORDER);
		lblWelcome.setFont(SWTResourceManager.getFont("Lucida Grande", 23, SWT.NORMAL));
		lblWelcome.setText("Welcome");
		lblWelcome.setBounds(87, 57, 140, 38);
		Label yourname = new Label(shell, SWT.BORDER);
		yourname.setFont(SWTResourceManager.getFont("Lucida Grande", 23, SWT.NORMAL));
		yourname.setBounds(193, 57, 140, 38);
		yourname.setText("Dr. "+d.getLname());
		
		// buffer in between welcome and manage buttons
		int buffer = 350;
//        Label lblp = new Label(shell, SWT.NONE);
//        lblp.setText("Patient Information");
//        lblp.setBounds(100, 98+buffer, 150, 16);
        Label lbla = new Label(shell, SWT.NONE);
        lbla.setText("Appointment Information");
        lbla.setBounds(100, 120+buffer, 200, 16);        
//        Button btnp = new Button(shell, SWT.NONE);
//        btnp.setText("Manage");
//        btnp.setBounds(268, 90+buffer, 117, 29);
        Button btna = new Button(shell, SWT.NONE);
        btna.setText("Manage");
        btna.setBounds(268, 115+buffer, 117, 29);        
//        btnp.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent arg0) {
//            	try {
//                    ManagePatientInfo window = new ManagePatientInfo();
//                    window.open();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        btna.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    ManageAppointmentInfo window = new ManageAppointmentInfo();
                    window.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem[] selection = table.getSelection();         
				int id = Integer.parseInt(selection[0].getText());
				Appointment a = ca.find(id);
			}
		});
		table.setBounds(90, 110, 450, 300);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnID = new TableColumn(table, SWT.NONE);
		tblclmnID.setWidth(0);
		tblclmnID.setText("App ID");
		
		TableColumn tblclmndate = new TableColumn(table, SWT.NONE);
		tblclmndate.setWidth(200);
		tblclmndate.setText("Date and Time");

		TableColumn tblclmndr = new TableColumn(table, SWT.NONE);
		tblclmndr.setWidth(100);
		tblclmndr.setText("Patient Name");
		
		TableColumn tblclmnil = new TableColumn(table, SWT.NONE);
		tblclmnil.setWidth(150);
		tblclmnil.setText("Illness");

		
	}
	public void setLocation(int desktopx, int desktopy) {
		shell.setLocation(desktopx, desktopy);		
	}
}
