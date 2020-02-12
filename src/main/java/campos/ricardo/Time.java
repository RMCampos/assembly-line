package campos.ricardo;

import java.math.BigDecimal;

class Time {

    private BigDecimal time24Hours;
    private BigDecimal time12Hours;
    private boolean isAM;
    private boolean isPM;

    public Time(String timeString) {
        boolean containsAmPm =
                timeString.toLowerCase().contains("am") ||
                timeString.toLowerCase().contains("pm");

        this.isAM = false;
        this.isPM = false;

        if (containsAmPm) {
            isAM = timeString.toLowerCase().contains("am");
            isPM = !isAM;
        } else {
            String digits = timeString.replaceAll("\\D+", "");
        }
    }

    private boolean isMorning() {
        return currentTime.contains("am");
    }

    private boolean isAfternoon() {
        return currentTime.contains("pm");
    }
}
