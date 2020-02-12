package campos.ricardo;

class Task {
    private final String name;
    private final Integer durationInMinutes;

    public Task(String name, Integer duration) {
        this.name = name;
        this.durationInMinutes = duration;
    }

    @Override
    public String toString() {
        return "";
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }
}
