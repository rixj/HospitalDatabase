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
import org.eclipse.swt.widgets.Text;

import controller.ControllerPatient;
import controller.ControllerUser;
import model.Patient;

public class WindowAdmin {


	protected Shell shell;

	private ControllerPatient cp = new ControllerPatient();
	private ControllerUser up = new ControllerUser();
	private Text fname;
	private Text fpw;
    Integer adminid;


    public WindowAdmin() {

    }
    public WindowAdmin(int admid) {
        this.adminid = admid;
    }
    public Integer getPatientid() {
        return adminid;
    }
    public void setPatientid(Integer adminid) {
        this.adminid = adminid;
    }
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
/** Testing out window independently of Main.java
 
		try {
		WindowAdmin window = new WindowAdmin();
		window.open();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
 */
	}



	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
	protected void createContents() {
		shell = new Shell();
		shell.setSize(503, 522);
		shell.setText("Administrative");
        Label lblp = new Label(shell, SWT.NONE);
        lblp.setText("Patient Information");
        lblp.setBounds(76, 60, 150, 16);        
        Label lbld = new Label(shell, SWT.NONE);
        lbld.setText("Doctor Information");
        lbld.setBounds(76, 90, 150, 16);        
        Label lbla = new Label(shell, SWT.NONE);
        lbla.setText("Appointment Information");
        lbla.setBounds(76, 120, 200, 16);        
        Button btnp = new Button(shell, SWT.NONE);
        btnp.setText("Manage");
        btnp.setBounds(268, 55, 117, 29);        
        Button btnd = new Button(shell, SWT.NONE);
        btnd.setText("Manage");
        btnd.setBounds(268, 85, 117, 29);        
        Button btna = new Button(shell, SWT.NONE);
        btna.setText("Manage");
        btna.setBounds(268, 115, 117, 29);        
        btnp.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
            	try {
                    ManagePatientInfo window = new ManagePatientInfo();
                    window.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    ManageDoctorInfo window = new ManageDoctorInfo();
                    window.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
	}
	public void setLocation(int desktopx, int desktopy) {
		shell.setLocation(desktopx, desktopy);		
	}
}
