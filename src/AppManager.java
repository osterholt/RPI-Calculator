import java.time.LocalTime;
import src.TeamManager;
import src.DataLoader;

public class AppManager {
    private static AppManager am;
    
    public static void main(String args[]) {
        DataLoader.getInstance().loadData();
        TeamManager.getInstance().printTeamsRPI();
        am.run();
    }

    public void run() {
        
    }
}
