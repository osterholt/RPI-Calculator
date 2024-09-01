public class AppManager {
    private static AppManager am;
    
    public static void main(String args[]) {
        DataLoader.getInstance().loadData();
        TeamManager.getInstance().printTeamsRPI();
        AppManager.getInstance().run();
    }
    public static AppManager getInstance() {
        if (am == null)
            am = new AppManager();
        return am;
    }

    public void run() {
        
    }
}
