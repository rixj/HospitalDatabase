package view;
import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import controller.ControllerDoctor;
import model.Doctor;

public class ReadOnlyDoctorInfo {

	protected Shell shell;
	private Table table;
	private Text tname;
	private Text tphone;
	private Text tdept;
	private ControllerDoctor cd = new ControllerDoctor();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReadOnlyDoctorInfo window = new ReadOnlyDoctorInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void FillData(){
		table.removeAll();
//		for (Doctor d : cd.findAll()) {
		for (Doctor d : cd.viewDocFromPatient()) {
			TableItem tableItem = new TableItem(table , SWT.NONE);
			tableItem.setText(new String[] {d.getName(),d.getPhone(),d.getDept()});
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
		shell.setText("View Doctor Information");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem[] selection = table.getSelection();
				int id = Integer.parseInt(selection[0].getText());
				Doctor d = cd.find(id);
				tname.setText(d.getName());
				tphone.setText(d.getPhone());
				tdept.setText(d.getDept());
			}
		});
		table.setBounds(30, 30, 442, 400);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(170);
		tblclmnName.setText("Name");

		TableColumn tblclmnPhone = new TableColumn(table, SWT.NONE);
		tblclmnPhone.setWidth(102);
		tblclmnPhone.setText("Phone");

		TableColumn tblclmnDues = new TableColumn(table, SWT.NONE);
		tblclmnDues.setWidth(170);
		tblclmnDues.setText("Department");

		// button: order by doctor
		Button btndoc = new Button(shell, SWT.NONE);
		btndoc.setBounds(30, 450, 200, 30);
		btndoc.setText("Order By Doctor Name");
		btndoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// ordered by dr.
				TableItem[] items = table.getItems();
		        Collator collator = Collator.getInstance(Locale.getDefault());
		        for (int i = 1; i < items.length; i++) {
		          String value1 = items[i].getText(0);
		          for (int j = 0; j < i; j++) {
		            String value2 = items[j].getText(0);
		            if (collator.compare(value1, value2) < 0) {
		              String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2) };
		              items[i].dispose();
		              TableItem item = new TableItem(table, SWT.NONE, j);
		              item.setText(values);
		              items = table.getItems();
		              break;
		            }
		          }
		        }
			}
		});
		
		// button: order by dept
		Button btndept = new Button(shell, SWT.NONE);
		btndept.setBounds(270, 450, 200, 30);
		btndept.setText("Order By Dept Name");
		btndept.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// ordered by dr.
				TableItem[] items = table.getItems();
		        Collator collator = Collator.getInstance(Locale.getDefault());
		        for (int i = 1; i < items.length; i++) {
		          String value1 = items[i].getText(2);
		          for (int j = 0; j < i; j++) {
		            String value2 = items[j].getText(2);
		            if (collator.compare(value1, value2) < 0) {
		              String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2) };
		              items[i].dispose();
		              TableItem item = new TableItem(table, SWT.NONE, j);
		              item.setText(values);
		              items = table.getItems();
		              break;
		            }
		          }
		        }
			}
		});

	}
}
