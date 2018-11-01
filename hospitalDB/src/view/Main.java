package view;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import controller.ControllerPatient;
import controller.ControllerUser;
import model.User;
public class Main {

    protected Shell shell;

    private ControllerPatient cp = new ControllerPatient();
    private ControllerUser up = new ControllerUser();
    private Text fname;
    private Text fpw;
	
	/**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            Main window = new Main();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        Monitor primary = display.getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = shell.getBounds();
        
        int desktopx = bounds.x + (bounds.width - rect.width) / 2;
        int desktopy = bounds.y + (bounds.height - rect.height) / 2;
        
        shell.setLocation(desktopx, desktopy);
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
        shell.setSize(500, 500);
        shell.setText("Patient Management");
        // username
        fname = new Text(shell, SWT.BORDER);
        fname.setBounds(210, 182, 175, 24);
        
        // pw
        fpw = new Text(shell, SWT.PASSWORD | SWT.BORDER);
        fpw.setBounds(210, 215, 175, 24);
        
        // login button
        Button btnLogin = new Button(shell, SWT.BORDER);
        btnLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
        btnLogin.setFont(SWTResourceManager.getFont("Helvetica Neue", 16, SWT.NORMAL));
        btnLogin.setBounds(112, 256, 277, 53);
        btnLogin.setText("Login");
        btnLogin.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	User u = up.find(fname.getText(), fpw.getText());
            	if(u.getAccess() > 0) {
//                    ShowMessage("Welcome");
                    if(u.getAccess() == 1) {
                    	//open doctor
                		try {
                			WindowDoctor window = new WindowDoctor();
                			window.setDocid(u.getId());
                			window.open();
                		} catch (Exception ex) {
                			ex.printStackTrace();
                		}
                    } else if(u.getAccess() == 2) {
                    	//open patient
                    	try {
                			WindowPatient window = new WindowPatient();
                			window.setPatientid(u.getId());
                			window.open();
                		} catch (Exception ex) {
                			ex.printStackTrace();
                		}
                    } else if(u.getAccess() == 3) {
                    	//open admin
                    	try {
                			WindowAdmin window = new WindowAdmin();
                			window.open();
                		} catch (Exception ex) {
                			ex.printStackTrace();
                		}
                    }
                } else {
                    ShowMessage("Incorrect username or password.");
                }
            }
        });
        
        Label lblHospitalDatabase = new Label(shell, SWT.NONE);
        lblHospitalDatabase.setForeground(SWTResourceManager.getColor(SWT.COLOR_LINK_FOREGROUND));
        lblHospitalDatabase.setFont(SWTResourceManager.getFont("Helvetica Neue", 30, SWT.NORMAL));
        lblHospitalDatabase.setBounds(102, 127, 301, 42);
        lblHospitalDatabase.setText("Hospital Database 0.0");
        Label luser = new Label(shell, SWT.NONE);
        luser.setFont(SWTResourceManager.getFont("Helvetica Neue", 16, SWT.NORMAL));
        luser.setBounds(112, 180, 115, 24);
        luser.setText("Username");
        
        Label lpw = new Label(shell, SWT.NONE);
        lpw.setText("Password");
        lpw.setFont(SWTResourceManager.getFont("Helvetica Neue", 16, SWT.NORMAL));
        lpw.setBounds(112, 213, 115, 24);
        
        // example button
        Button button = new Button(shell, SWT.PUSH);
        button.setImage(SWTResourceManager.getImage("/Users/Danielle/Documents/hospitalWorkspace/hospitalDB/info.png"));
        button.setBounds(445, 426, 45, 42);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
            	try {
        			Examples window = new Examples();
        			window.open();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }
        });
        
    }

}
