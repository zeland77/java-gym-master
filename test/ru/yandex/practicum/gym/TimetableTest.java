package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        System.out.println("1)ПОНЕДЕЛЬНИК");
        System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY));
        Assertions.assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        //Проверить, что за вторник не вернулось занятий
        System.out.println("1)ВТОРНИК");
        System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        System.out.println("2)ПОНЕДЕЛЬНИК");
        System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY));
        Assertions.assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        System.out.println("2)ЧЕТВЕРГ");
        System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY));
        Assertions.assertEquals(2, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size());
        // Проверить, что за вторник не вернулось занятий
        System.out.println("2)ВТОРНИК");
        System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        System.out.println("3)ПОНЕДЕЛЬНИК");
        TimeOfDay time = new TimeOfDay(13, 0);
        System.out.println(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, time));
        Assertions.assertEquals(1, timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, time).size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        System.out.println("3)ВТОРНИК");
        time = new TimeOfDay(14, 0);
        System.out.println(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.TUESDAY, time));
        Assertions.assertNull(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.TUESDAY, time));
    }

    @Test
    void testGetCountByCoaches() {
        Timetable timetable = new Timetable();

        Assertions.assertEquals(0, timetable.getCountByCoaches().size());

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Петров", "Иван", "Николаевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить сортировку по убыванию кол-ва тренировок тренерами
        System.out.println("4)ТРЕНИРОВКИ");
        System.out.println(timetable.getCountByCoaches());

        // Проверить, что возвращает 2 тренера
        Assertions.assertEquals(2, timetable.getCountByCoaches().size());

        // Проверить, что возвращает 4 сумму тренировок
        Assertions.assertEquals(4, timetable.getCountByCoaches().stream()
                                                                        .mapToInt(CounterOfTrainings::getCountTraining)
                                                                        .sum());
        // Проверить, что coach1 проверил 1 тренировку
        Assertions.assertEquals(1, timetable.getCountByCoaches().stream()
                                                        .filter(x -> x.getCoach().equals(coach1))
                                                        .mapToInt(CounterOfTrainings::getCountTraining)
                                                        .sum());
        // Проверить, что coach2 проверил 3 тренировки
        Assertions.assertEquals(3, timetable.getCountByCoaches().stream()
                .filter(x -> x.getCoach().equals(coach2))
                .mapToInt(CounterOfTrainings::getCountTraining)
                .sum());
    }

}
