class TeamHandler {
    var teams = ArrayList<Team>()
    var p = Persistence();

    fun createTeam(teamName: String, teamDescription: String){
        teams.add(Team(teamName, teamDescription))
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

    fun deleteTeam(teamIndex: Int) {
        teams.removeAt(teamIndex)
        saveTeams()
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
        loadTeams()
        //saveTeams()
    }
}