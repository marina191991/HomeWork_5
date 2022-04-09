package hw1ObjectClass;
import java.util.Set;


/*Добавить класс Course (полоса препятствий), в котором будут находиться:
массив препятствий;
метод, который будет просить команду пройти всю полосу.*/
public class Course {
    private Set<Barrier> barrierList;

    public Set<Barrier> getBarrierList() {
        return barrierList;
    }

    public void setBarrierList(Set<Barrier> barrierList) {
        this.barrierList = barrierList;
    }

    public static Barrier allWasteBarriers() {

        return null;
    }

    public static void go(Team team, Set<Barrier> barrierList) {
        int winn = 0;
        int k = barrierList.stream().mapToInt(p -> p.getWasteEnergy()).sum();
        for (int i = 0; i < 5; i++) {
            int l = team.getTeamList().get(i).getPower() - k;
            if (l > 0) {
                winn++;
                team.getTeamList().get(i).setPassedDistance(true);
            } else team.getTeamList().get(i).setPassedDistance(false);
        }
        if (winn == 5)
            System.out.println("Команда " + team.getNameTeam() + " выйграла");
        else
            System.out.println("Команда " + team.getNameTeam() + " проиграла");
    }


}
