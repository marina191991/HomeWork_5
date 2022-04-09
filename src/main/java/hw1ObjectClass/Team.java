package hw1ObjectClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
* 2. Добавить класс Team, который будет содержать:
название команды;
массив из четырех участников — в конструкторе можно сразу всех участников указывать);
метод для вывода информации о членах команды, прошедших дистанцию;
метод вывода информации обо всех членах команды.*/
public class Team {
    private String nameTeam;
    List<Person> teamList;

    //constructor
    public Team(String nameTeam, List<Person> teamList) {
        this.nameTeam = nameTeam;
        this.teamList = teamList;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public List<Person> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Person> teamList) {
        this.teamList = teamList;
    }

    //метод добавления команды в общий лист
    public static Set<Team> takeTeamAddAllList(Set<Team> teamsAllList, Team team) {
        teamsAllList.add(team);
        return null;
    }

    // метод для вывода информации о членах команды, прошедших дистанцию;
    public static List<Person> whoPassed(Team team) {
        List<Person> list = team.getTeamList().stream().filter(Person::isPassedDistance).collect(Collectors.toList());
        return list;
    }

    //метод вывода информации обо всех членах команды.
    public static List<Person> readPersonTeam(Set<Team> listAllTeams, String nameTeam) {

        List<Team> list = listAllTeams.stream().filter(p -> p.getNameTeam() == nameTeam).collect(Collectors.toList());
        return list.get(0).getTeamList();
    }

    @Override
    public String toString() {
        return "Team{" +
                "nameTeam='" + nameTeam + '\'' +
                ", teamList=" + teamList +
                '}';
    }
}
