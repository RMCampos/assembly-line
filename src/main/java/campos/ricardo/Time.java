package campos.ricardo;

class Time {

    private Integer hours;
    private Integer minutes;

    public static Time ZERO = new Time();

    public static Time fromString(String timeString) {
        boolean containsAmPm =
            timeString.toLowerCase().contains("am") ||
            timeString.toLowerCase().contains("pm");

        if (!containsAmPm) {
            return Time.ZERO;
        }

        String justNumbers = timeString.replaceAll("\\D+", "");
        Integer len = justNumbers.length();
        Integer hours = Integer.parseInt(justNumbers.substring(0, len-2));
        Integer minutes = Integer.parseInt(justNumbers.substring(len-2));

        Time timeLocal = new Time();
        timeLocal.setHours(hours);
        timeLocal.setMinutes(minutes);

        if (timeString.toLowerCase().contains("pm")) {
            timeLocal.addMinutes(60 * 12);
        }

        return timeLocal;
    }

    private Time() {
        this.hours = 0;
        this.minutes = 0;
    }

    public void addMinutes(Integer minutes) {
        this.minutes += minutes;
        while (this.minutes >= 60) {
            this.hours += 1;
            this.minutes -= 60;
        }

        if (this.hours > 24) {
            this.hours -= 24;
        }
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getHours() {
        return hours;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getMinutes() {
        return minutes;
    }
}
