package campos.ricardo;

class TimeLine {
    private final String MORNING_START = "9:00 am";
    private final String MORNING_END = "12:00 am";
    private final String AFTERNOON_STARTS = "1:00 pm";
    private final String LABOR_GYM_MIN = "4:00 pm";
    private final String LABOR_GYM_MAX = "5:00 pm";
    private final String MAINTENANCE_DURATION = "5 min";

    private Time currentTime;
    private Time nextStop;

    public TimeLine(Time startTime) {
        this.currentTime = startTime;
    }

    public boolean executeTask(Task task) {
        final Time after = this.currentTime.addMinutes(task.getDurationInMinutes());

        if (after.isBeforeNoon()) {
            System.out.println(TimeUtil.format(this.currentTime) + " " + task.getName());
            this.currentTime = this.currentTime.addMinutes(task.getDurationInMinutes());
            return true;
        } else {
            if (after.isLunchTime()) {
                System.out.println(TimeUtil.format(this.currentTime) + " " + task.getName());
                this.currentTime = this.currentTime.addMinutes(60);
                System.out.println(TimeUtil.format(after) + " Almoço");
            } else {
                if (after.getHours().compareTo(17) >= 0) {
                    this.currentTime = this.currentTime.subtractMinutes(task.getDurationInMinutes());
                    return false;
                } else {
                    System.out.println(TimeUtil.format(this.currentTime) + " " + task.getName());
                    this.currentTime = this.currentTime.addMinutes(task.getDurationInMinutes());
                }
            }
        }

        return false;
    }

    public void setCurrentTime(Time currentTime) {
        this.currentTime = currentTime;
    }
}
