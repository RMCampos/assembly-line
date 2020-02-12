package campos.ricardo;

class TimeUtil {

    public static String format(Time time) {
        if (time == null) {
            return "";
        }

        return String.format(
            "%02d:%02d",
            time.getHours(),
            time.getMinutes()
        );
    }
}
