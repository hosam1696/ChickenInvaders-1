package fileHandling;

/**
 * Give Up Exception
 */
@SuppressWarnings("serial")
public class GiveUpException extends Exception {

	public GiveUpException(){
		super("Writer Stopped");
	}
}
