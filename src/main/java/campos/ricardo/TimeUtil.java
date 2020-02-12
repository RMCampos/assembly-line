package campos.ricardo;

class TimeUtil {

    public static String format(Time time) {
        return String.format(
            "%02d:%02d",
            time.getHours(),
            time.getMinutes()
        );
    }
}
