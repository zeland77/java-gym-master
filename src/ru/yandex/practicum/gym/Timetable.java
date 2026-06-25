package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();
        ArrayList<TrainingSession> dayListTraining;
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTable;

        dayTable = timetable.get(dayOfWeek);
        if (dayTable == null) {
            dayTable = new TreeMap<>();
        }
        dayListTraining = dayTable.get(timeOfDay);
        if (dayListTraining == null) {
            dayListTraining = new ArrayList<>();
        }

        dayListTraining.add(trainingSession);
        dayTable.put(timeOfDay, dayListTraining);
        timetable.put(dayOfWeek, dayTable);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTable = timetable.get(dayOfWeek);
        return dayTable == null ? null : dayTable.get(timeOfDay);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counters = new HashMap<>();
        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayMap : timetable.values()) {
            for (ArrayList<TrainingSession> listTraining : dayMap.values()) {
                for (TrainingSession training : listTraining) {
                    Coach coach = training.getCoach();
                    counters.put(coach, counters.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> counterByCoaches = new ArrayList<>();
        for (Coach coach : counters.keySet()) {
            counterByCoaches.add(new CounterOfTrainings(coach, counters.get(coach)));
        }
        Collections.sort(counterByCoaches);
        return counterByCoaches;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetable=" + timetable +
                '}';
    }
}
