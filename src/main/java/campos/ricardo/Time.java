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

    public Time() {
        this.hours = 0;
        this.minutes = 0;
    }

    public Time addMinutes(Integer minutes) {
        Time time = new Time();
        time.setHours(getHours());
        time.setMinutes(getMinutes());

        time.minutes += minutes;
        if (time.minutes >= 60) {
            time.hours += 1;
            time.minutes -= 60;
        }

        if (time.hours > 12) {
            time.hours -= 12;
        }

        return time;
    }

    public Time subtractMinutes(Integer minutes) {
        return this.addMinutes(minutes * -1);
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

    public boolean isBeforeNoon() {
        return this.hours.compareTo(12) < 0;
    }

    public boolean isLunchTime() {
        return this.hours.equals(12) &&
            this.minutes.equals(0);
    }
}
