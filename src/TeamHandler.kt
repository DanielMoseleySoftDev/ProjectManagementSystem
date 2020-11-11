class TeamHandler {
    var teams = ArrayList<Team>()
    var p = Persistence();

    fun createTeam(teamName: String, teamDescription: String){
        teams.add(Team(teamName, teamDescription))
        println("this works")
        saveTeams()
    }

    fun findTeam(teamName: String):Team{
        teams.forEach { team ->
            if(teamName == team.teamName){
                return team
            }
        }
        return teams[0]
    }

    fun loadTeams() {
        teams = p.loadFromFile(true)
        println(teams)
        println("Tea: "+ teams[0] + "Loaded Successfully" + "\n")
    }

    fun saveTeams() {
        p.saveToFile(teams, true)
        println(teams + "\n")
    }

    init{
        teams.add(Team("N/A","No team assigned"))
        teams.add(Team("A","Team A"))
        teams.add(Team("B","Team B"))
        teams.add(Team("C","Team C"))
    }
}