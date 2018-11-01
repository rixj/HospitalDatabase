package view;
import java.awt.EventQueue;
import java.awt.List;

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
import controller.ControllerPatient;
import model.Appointment;
import model.Patient;
public class WindowPatient {

	protected Shell shell;
	private ControllerAppointment ca = new ControllerAppointment();
	private ControllerPatient cp = new ControllerPatient();
	private Table table;
	Integer patientid;


	public WindowPatient() {
	}
	public WindowPatient(int patid) {
		this.patientid = patid;
	}
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WindowPatient window = new WindowPatient();
			window.setPatientid(2578);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void FillGrid(int patid){
		table.removeAll();
		for (Appointment a : ca.findAllforPatient(patid)) {
			TableItem tableItem = new TableItem(table , SWT.NONE);
			tableItem.setText(new String[] {String.valueOf(a.getAppid()), a.getDocname(), a.getDatetime()});
			tableItem.setData(a);           
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(this.getPatientid());
		shell.setLocation(390,150);
		shell.open();
		shell.layout();
		FillGrid(this.getPatientid());
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
	protected void createContents(int patid) {
		shell = new Shell();
		shell.setSize(500, 500);
		shell.setText("Patient");
		Patient p = cp.find(patid);

		// welcome screen
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

		// contact us
		int begincontacty = 270;
		Label lblCall = new Label(shell, SWT.BORDER);
		lblCall.setBounds(87, begincontacty, 225, 16);
		lblCall.setText("Reschedule an Appointment");
		Button btnCall = new Button(shell, SWT.NONE);
		btnCall.setBounds(313, begincontacty, 117, 28);
		btnCall.setText("Call");
		btnCall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ShowMessage("Call (910) 667-7000");
			}
		});

        // doctor info
        int begindry = begincontacty + 30;
        Label ldr = new Label(shell, SWT.BORDER);
        ldr.setBounds(87, begindry, 225, 16);
        ldr.setText("View Available Doctors");
        Button btnDr = new Button(shell, SWT.NONE);
        btnDr.setBounds(313, begindry, 117, 28);
        btnDr.setText("OK");
        btnDr.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
        		try {
        			ReadOnlyDoctorInfo window = new ReadOnlyDoctorInfo();
        			window.open();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            }
        });

		// buffer from table to payment info 
		int buffer = begincontacty + 65;
		// payment info
		Label lbldues = new Label(shell, SWT.BORDER);
		lbldues.setText("Unpaid Balance");
		lbldues.setBounds(87, buffer, 170, 16);
		Label ldue = new Label(shell, SWT.BORDER);
		ldue.setFont(SWTResourceManager.getFont("Helvetica Neue", 33, SWT.NONE));//SWT.BOLD));
		ldue.setBounds(87, buffer+20, 108, 47);
		Button btnPayNow = new Button(shell, SWT.NONE);
		btnPayNow.setBounds(313, buffer+23, 117, 29);
		btnPayNow.setText("Pay Now");

		// secret menu
		Button btnMinimumBalance = new Button(shell, SWT.RADIO);
		btnMinimumBalance.setBounds(260, buffer + 20, 300, 18);
		btnMinimumBalance.setText("Minimum Balance");
		btnMinimumBalance.setVisible(false);
		Button btnFullBalance = new Button(shell, SWT.RADIO);
		btnFullBalance.setBounds(260, buffer + 50, 300, 18);
		btnFullBalance.setText("Full Balance");
		btnFullBalance.setVisible(false);
		Button btnPay = new Button(shell, SWT.NONE);
		btnPay.setBounds(313, buffer + 80, 117, 28);
		btnPay.setText("Pay");
		btnPay.setVisible(false);

		btnPayNow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				btnPayNow.setVisible(false);
				int money = p.getCurrentDues();
				int min = (int) (money*.1);
				btnMinimumBalance.setText("Minimum Balance "+"$"+min);
				btnMinimumBalance.setVisible(true);
				btnFullBalance.setVisible(true);
				btnPay.setVisible(true);

				btnPay.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						ShowMessage("We charged your card $"+String.valueOf(min)+".");
						p.setCurrentDues(p.getCurrentDues()- min);
						cp.edit(p);
						ldue.setText("$"+String.valueOf(p.getCurrentDues()));
						btnPay.setVisible(false);
						btnMinimumBalance.setVisible(false);
						btnFullBalance.setVisible(false);
						btnPayNow.setVisible(true);
					}
				});
			}
		});
		// the only things that change
		ldue.setText("$"+String.valueOf(p.getCurrentDues()));
		yourname.setText(p.getFname());

		// appt table
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem[] selection = table.getSelection();         
				int id = Integer.parseInt(selection[0].getText());
				Appointment a = ca.find(id);
			}
		});
		table.setBounds(90, 110, 335, 142);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnID = new TableColumn(table, SWT.NONE);
		tblclmnID.setWidth(0);
		tblclmnID.setText("App ID");

		TableColumn tblclmndr = new TableColumn(table, SWT.NONE);
		tblclmndr.setWidth(110);
		tblclmndr.setText("Doctor Name");

		TableColumn tblclmndate = new TableColumn(table, SWT.NONE);
		tblclmndate.setWidth(300);
		tblclmndate.setText("Date and Time");
	}
}
