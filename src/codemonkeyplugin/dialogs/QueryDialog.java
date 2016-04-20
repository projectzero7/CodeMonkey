package codemonkeyplugin.dialogs;

/*
 * Code adapted from http://www.vogella.com/tutorials/EclipseDialogs/article.html#tutorialjface_userdefined
 */

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class QueryDialog extends TitleAreaDialog {
	// Declare variables
	private Text textInputBox;
	private String query;

	/**
	 * Instantiate a new query dialog.
	 * 
	 * @param parentShell
	 */
	public QueryDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Creates this window's widgetry in a new top-level shell.
	 */
	@Override
	public void create() {
		// Call the superclass's method
		super.create();
		
		// Set the dialog text in the upper area
		setTitle("Code Monkey");
		setMessage("Please enter a query to return relevant code from Stack Overflow.", IMessageProvider.INFORMATION);
	}

	/**
	 * Creates and returns the contents of the upper part of this dialog (above the button bar).
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		// Create the containers and layout for the area below the message
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		// Add the input areas
		createQueryBox(container);

		// Return the finished product
		return area;
	}

	/**
	 * Returns a boolean indicating whether the dialog should be considered resizable when the shell style is initially set.
	 */
	@Override
	protected boolean isResizable() {
		return true;
	}

	/**
	 * Notifies that the ok button of this dialog has been pressed.
	 */
	@Override
	protected void okPressed() {
		// Copy the text input to variables
		saveInput();
		
		// Finally, have the superclass finish the method
		super.okPressed();
	}
	
	/**
	 * Creates an input box for the query.
	 * 
	 * @param container
	 */
	private void createQueryBox(Composite container) {
		// Set the input area's label
		Label label = new Label(container, SWT.NONE);
		label.setText("Query");

		// Set the input area's format
		GridData grid = new GridData();
		grid.grabExcessHorizontalSpace = true;
		grid.horizontalAlignment = GridData.FILL;

		// Create the input area
		textInputBox = new Text(container, SWT.BORDER);
		textInputBox.setLayoutData(grid);
	}
	
	/**
	 * Stores the input area's text to variables.
	 */
	private void saveInput() {
		query = textInputBox.getText();
	}

	/**
	 * Returns the query.
	 * 
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
} 