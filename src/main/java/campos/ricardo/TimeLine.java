package campos.ricardo;

class TimeLine {
    private final String MORNING_START = "9:00 am";
    private final String MORNING_END = "12:00 am";
    private final String AFTERNOON_STARTS = "1:00 pm";
    private final String LABOR_GYM_MIN = "4:00 pm";
    private final String LABOR_GYM_MAX = "5:00 pm";
    private final String MAINTENANCE_DURATION = "5 min";

    private String currentTime;
    private String nextStop;

    public TimeLine(String startTime) {
        this.currentTime = currentTime.toLowerCase();
        updateNextStop();
    }

    public void addTimeInMinutes(Integer minutes) throws Exception {

    }

    private boolean updateNextStop() {
        if (TimeUtil)
    }
}
