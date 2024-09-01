import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class DataLoader {
    private static DataLoader dl;
    private String filename;
    private static String defaultFilename = "./dat/2023example.csv";
    private static String delimeter = ",";

    private DataLoader() {
        filename = defaultFilename;
    }
    public static DataLoader getInstance() {
        if (dl == null)
            dl = new DataLoader();
        return dl;
    }
    public void setFilename(String filename) {
        if(filename != null && !filename.isEmpty())
            this.filename = filename;
        else
            this.filename = defaultFilename;
    }
    public boolean loadData() {
        // Load data from filename
        ArrayList<String[]> csv = new ArrayList<String[]>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            fileScanner.nextLine(); // Skip header
            while(fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] lineDel = line.split(delimeter);
                if(lineDel[0].equals("NULL"))
                    continue;
                csv.add(lineDel);

                String teamName = lineDel[0];
                int gamesWon = Integer.parseInt(lineDel[1]);
                int gamesLost = Integer.parseInt(lineDel[2]);
                TeamManager.getInstance().addTeam(teamName, gamesWon, gamesLost);
            }
            fileScanner.close();
        } catch(Exception e) {e.printStackTrace();}

        
        for(String[] row : csv) {
            String teamName = row[0];
            Team team = TeamManager.getInstance().getTeam(teamName);
            ArrayList<Team> opponents = new ArrayList<>();
            for(int i = 3; i < row.length; i++) {
                if(row[i].equals("NULL"))
                    continue;
                Team opponent = TeamManager.getInstance().getTeam(row[i]);
                opponents.add(opponent);
            }
            TeamManager.getInstance().addOpps(team, opponents);
        }

        System.out.println("Data Loaded Successfully.");

        return true;
    }
}
