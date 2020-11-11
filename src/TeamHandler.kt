class TeamHandler {
    var teams = ArrayList<Team>()

    fun createTeam(teamName: String, teamDescription: String){
        teams.add(Team(teamName, teamDescription))
        println("this works")

    }

    fun findTeam(teamName: String):Team{
        teams.forEach { team ->
            if(teamName == team.teamName){
                return team
            }
        }
        return teams[0]
    }

    init{
        teams.add(Team("N/A","No team assigned"))
        teams.add(Team("A","Team A"))
        teams.add(Team("B","Team B"))
        teams.add(Team("C","Team C"))
    }
}