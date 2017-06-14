
package spreadsheetUpdates.util;
/**
 * Class having methods for logging data
 * @author    Shraddha Apharande
 */
public class Logger{


	/*Debug Values
	 * 0 = Sum of the cells
	 * 1 = Total cycles detected
	 */
    public static enum DebugLevel {RELEASE ,CYCLE};

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	 
	  case 0: debugLevel = DebugLevel.RELEASE; break;
	  case 1: debugLevel = DebugLevel.CYCLE; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    // @return None
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
