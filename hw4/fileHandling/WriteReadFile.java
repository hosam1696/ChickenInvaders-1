package fileHandling;
import java.io.*;

/**
 * A Class that hold the read/write actions. to be used by the other packages
 */
public class WriteReadFile {
	/**
	 * 
	 * @param game - the Game instance to be saved
	 * @throws GiveUpException
	 * Creates a txt file with all the relevant details for saving the current game.
	 */
	public void saveScore(String toSave) throws GiveUpException {
		WriteToFileBase tWrite = new WriteToFileBase() {
			protected File promptFile() throws AbortException {
				BufferedReader tIn = new BufferedReader(new StringReader("highscores.txt"));
				try {
					String tFilename = tIn.readLine();
					if (tFilename == null || tFilename.isEmpty())
						throw new AbortException();
					return new File(tFilename);
				} catch (IOException e) {
					throw new AbortException();
				}
			}

			protected void reportError(Exception e) {
				e.printStackTrace();
			}

			protected void reportGiveUp(Exception e) {
				e.printStackTrace();
			}

			protected void writeInner(FileWriter f) throws IOException {
				PrintWriter tOut = new PrintWriter(f);
				tOut.println(toSave);
			}
		};
		tWrite.writeToFile();
	}
	/**
	 * Reads the txt file.
	 * @return A string made from the content of the txt file
	 */
	public String loadToString()
	{
		   String content = null;
		   File file = new File("highscores.txt");
		   try {
		       FileReader reader = new FileReader(file);
		       char[] chars = new char[(int) file.length()];
		       reader.read(chars);
		       content = new String(chars);
		       reader.close();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		   return content;
	}
}
