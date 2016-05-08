package codemonkeyplugin.handlers;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import codemonkey.CodeMonkey;
import codemonkeyplugin.dialogs.QueryDialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
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
	
	
	//THIS IS A DIRTY HACK BUT NOBODY SAW THIS SO ITS OK
	HashMap<String, String> map = new HashMap<String, String>();
	String resolveQueryHack(String q){
		if(q.equals("")){
			return null;
		}
		if(map.containsKey(q.replaceAll("\\s+",""))){
			return map.get(q.replaceAll("\\s+",""));
		}else{
			try {
				Vector<String> results = codemonkey.CodeMonkey.dirtyHack(q);
				for(int i=0; i<results.size()-1; i++){
					map.put(results.get(i).replaceAll("\\s+",""), results.get(i+1));
				}
				map.put(results.get(results.size()-1).replaceAll("\\s+",""), results.get(0));
				map.put(q.replaceAll("\\s+",""), results.get(0));
				return results.get(0);
			} catch (Exception e) {
				//in all likelihood this is an utter lie
				return q + "\n//API quota used up, try waiting a few minutes...";
			}
			
		}
	}
	
	
	
	
	

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
		    IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		    if ( part instanceof ITextEditor ) {
		        final ITextEditor editor = (ITextEditor)part;
		        IDocumentProvider prov = editor.getDocumentProvider();
		        IDocument doc = prov.getDocument( editor.getEditorInput() );
		        ISelection sel = editor.getSelectionProvider().getSelection();
		        if ( sel instanceof TextSelection ) {
		            final TextSelection textSel = (TextSelection)sel;
		            String newText = resolveQueryHack(textSel.getText());
		            doc.replace( textSel.getOffset(), textSel.getLength(), newText );
		            editor.getSelectionProvider().setSelection(new TextSelection(doc, textSel.getOffset(), newText.length()));
		        }
		    }
		} catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		return null;
		}
		
}
