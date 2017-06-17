package fileHandling;

/**
 * Abort Exception
 */
@SuppressWarnings("serial")
public class AbortException extends Exception {
	
	public AbortException(){
		super("Writer Unexpectedlly aborted");
	}
}
