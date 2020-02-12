package campos.ricardo;

import java.util.Objects;

class Task {
    private final String name;
    private final Integer durationInMinutes;

    public Task(String name, Integer duration) {
        this.name = name;
        this.durationInMinutes = duration;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return name.equals(task.name) &&
            durationInMinutes.equals(task.durationInMinutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, durationInMinutes);
    }
}
