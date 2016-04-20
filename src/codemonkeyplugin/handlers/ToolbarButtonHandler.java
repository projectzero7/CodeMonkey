package codemonkeyplugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import codemonkeyplugin.dialogs.QueryDialog;

import org.eclipse.jface.window.Window;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ToolbarButtonHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ToolbarButtonHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		QueryDialog dialog = new QueryDialog(window.getShell());
		dialog.create();
		if (dialog.open() == Window.OK) {
		  System.out.println(dialog.getQuery());
		} 

		return null;
	}
}
