class TeamHandler {
    var teams = mutableListOf<Team>()

    fun createTeam(teamName: String, teamDescription: String){
        teams.add(Team(teamName, teamDescription))
        println("this works")

    }
    init{

    }
}