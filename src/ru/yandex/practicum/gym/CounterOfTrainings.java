package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private int countTraining;

    public CounterOfTrainings(Coach coach, int countTraining) {
        this.coach = coach;
        this.countTraining = countTraining;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "CounterOfTrainings{" +
                "coach=" + coach +
                ", countTrainig=" + countTraining +
                '}';
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
        return o.countTraining - this.countTraining;  // по убыванию
    }

    public int getCountTraining() {
        return countTraining;
    }

    public void setCountTraining(int countTraining) {
        this.countTraining = countTraining;
    }
}
