import java.util.ArrayList;
import java.util.UUID;

public class TeamManager {
    private static TeamManager tm;
    ArrayList<Team> teams;
    private TeamManager() {
        teams = new ArrayList<>();
    }
    public static TeamManager getInstance() {
        if (tm == null) 
            tm = new TeamManager();
        return tm;
    }

    public boolean addTeam(String teamName) {
        addTeam(teamName, 0, 0);
    }
    public boolean addTeam(String teamName, int gamesWon, int gamesLost) {
        for(Team team : teams) {
            if (team.getName().equals(teamName)) {
                return false;
            }
        }
        teams.add(new Team(teamName, gamesWon, gamesLost));
    }
    public Team getTeam(UUID teamID) {
        for(Team team : teams) {
            if (team.getId().equals(teamID)) {
                return team;
            }
        }
        return null;
    }
    public Team getTeam(String teamName) {
        for(Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
    public boolean addOpps(Team team, ArrayList<Team> opponents) {
        for(Team opponent : opponents) {
            team.addOpponent(opponent);
        }
    }

    public void printTeamsRPI() {
        for(Team team : teams) {
            System.out.println(team.getName() + " RPI: " + team.calculateRPI());
        }
    }
}