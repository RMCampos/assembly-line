package campos.ricardo;

class TimeLine {
    private Time currentTime;

    public TimeLine() {
        this.currentTime = Time.fromString(Constraints.PRODUCTION_START_TIME);
    }

    public void printTask(Task task) {
        System.out.println(TimeUtil.format(this.currentTime) + " " + task.getName());
        this.currentTime.addMinutes(task.getDurationInMinutes());
    }
}
