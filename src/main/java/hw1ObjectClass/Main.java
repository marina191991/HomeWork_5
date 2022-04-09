package hw1ObjectClass;
import java.util.*;

/*
2. Добавить класс Team, который будет содержать:
название команды;
массив из четырех участников — в конструкторе можно сразу всех участников указывать);
метод для вывода информации о членах команды, прошедших дистанцию;
метод вывода информации обо всех членах команды. ++++++++++++++++++++++++++++++++++++++++
3. Добавить класс Course (полоса препятствий), в котором будут находиться:
массив препятствий;
метод, который будет просить команду пройти всю полосу.

В итоге должно получиться похожее:
public static void main(String[] args) {
Course c = new Course(...); // Создаем полосу препятствий
Team team = new Team(...); // Создаем команду
c.doIt(team); // Просим команду пройти полосу
team.showResults(); // Показываем результаты
}
*/
public class Main {
    public static void main(String[] args) {
        //создал список для всех команд
        Set<Team> teamsAllList = new HashSet<>();
        //создаем людей для дальнейшей разбивки на команды
        Person person0 = new Person("Kate", 23, "woman", 7);
        Person person1 = new Person("Danil", 30, "man", 10);
        Person person2 = new Person("Franklin", 27, "man", 15);
        Person person3 = new Person("Sandra", 35, "woman", 11);
        Person person4 = new Person("Jonny", 41, "man", 20);
        Person person5 = new Person("Marta", 49, "woman", 8);
        Person person6 = new Person("Mike", 33, "man", 8);
        Person person7 = new Person("Julie", 18, "woman", 10);
        Person person8 = new Person("Nik", 22, "man", 11);
        Person person9 = new Person("Jenifer", 19, "woman", 10);


//разделили людей на списки для дальнейшей загрузки в команды
        List<Person> takeListPT = Person.takeList(person0, person1, person2, person3, person4);
        List<Person> takeListPT1 = Person.takeList(person5, person6, person7, person8, person9);
//создали команды с названием и уже готовым списком людей
        Team team = new Team("Beavers", takeListPT);
        Team team1 = new Team("Birds", takeListPT1);

//загрузили команды в общий список команд
        Team.takeTeamAddAllList(teamsAllList, team1);
        Team.takeTeamAddAllList(teamsAllList, team);
        // создаем препятствия
        Barrier barrierWater = new Barrier("water", 1);
        Barrier barrierFire = new Barrier("fire", 2);
        Barrier barrierAir = new Barrier("air", 3);
        Barrier barrierGround = new Barrier("ground", 1);
        //создаем сет для всех барьеров
        Set<Barrier> listSetAllBarrier = new HashSet<>();
        //добавляем все барьеры в один лист
        Barrier.addBarrierInSetList(listSetAllBarrier, barrierFire);
        Barrier.addBarrierInSetList(listSetAllBarrier, barrierAir);
        Barrier.addBarrierInSetList(listSetAllBarrier, barrierGround);
        Barrier.addBarrierInSetList(listSetAllBarrier, barrierWater);

        //получили список игроков по названию команды
        System.out.println(Team.readPersonTeam(teamsAllList, "Beavers"));

       //просим пройти дистанцию и смотрим результат
        Course.go(team, listSetAllBarrier);
        Course.go(team1, listSetAllBarrier);

        System.out.println();
        //узнаем, кто из команды прошел дистанцию
        System.out.println(Team.whoPassed(team));
    }
}
