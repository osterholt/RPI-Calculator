import java.util.ArrayList;
import java.util.UUID;

public class Team {
    private UUID id;
    private String name;
    private int gamesPlayed;
    private int wins;
    private ArrayList<Team> teamsPlayed;
    
    public Team() {
        this.id = UUID.randomUUID();
        this.name = "Default";
        this.gamesPlayed = 0;
        this.wins = 0;
        this.teamsPlayed = new ArrayList<Team>();
    }
    public Team(String name, int wins, int losses) {
        this.id = UUID.randomUUID();
        setName(name);
        setWins(wins);
        setGamesPlayed(losses + wins);
        this.teamsPlayed = new ArrayList<Team>();
    }
    public boolean addOpponent(String teamName) {
        if (teamName != null && !teamName.isEmpty()) {
            // get team.
            Team opp = TeamManager.getInstance().getTeam(teamName);
            teamsPlayed.add(opp);
            return true;
        }
        return false;
    }
    public boolean addOpponent(Team team) {
        return teamsPlayed.add(team);
    }
    /**
     * (WP * 0.3) + (OWP * 0.4) + (OOWP * 0.3)
     * @return
     */
    public double calculateRPI() {
        double wp = getWinPercentage();
        double owp = getOpponentWinPercentage();
        double oowp = getOppOpponentWinPercentage();
        double rpi = (wp * 0.3) + (owp * 0.4) + (oowp * 0.3);
        return rpi;
    }
    public double getWinPercentage() {
        return (double) wins / gamesPlayed;
    }
    public double getOpponentWinPercentage() {
        double opponentWinPercentage = 0.0;
        for (Team team : teamsPlayed) {
            if(team == null)
                continue;
            if(team.teamsPlayed.isEmpty())
                continue;
            opponentWinPercentage += team.getWinPercentage();
        }
        return opponentWinPercentage / teamsPlayed.size();
    }
    private double getOppOpponentWinPercentage() {
        double opponentOpponentWinPercentage = 0.0;
        for (Team team : teamsPlayed) {
            if(team == null)
                continue;
            if(team.teamsPlayed.isEmpty())
                continue;
            opponentOpponentWinPercentage += team.getOpponentWinPercentage();
        }
        return opponentOpponentWinPercentage / teamsPlayed.size();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && !name.isEmpty())
            this.name = name;
        else
            this.name = "Default";
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        if (gamesPlayed >= 0)
            this.gamesPlayed = gamesPlayed;
        else  
            this.gamesPlayed = 0;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        if(wins >= 0)
            this.wins = wins;
        else
            this.wins = 0;
    }
    public ArrayList<Team> getTeamsPlayed() {
        return teamsPlayed;
    }
}
