package Config_Specific.Timestamp;

import Config_Common.ErrorEvent;

public class Timestamp {

    public static int Day;
    public static int Month;
    public static int Year;
    public static int Hour;
    public static int Minute;
    public static int Second;
    public static long TimestampValue;

    public Timestamp(){}

    public static ErrorEvent InputTimestamp(String timestampString){

        if(timestampString.length() == 0){
            return ErrorEvent.EmptyInput;
        }

        try{
            TimestampValue = Long.parseLong(timestampString);
        }catch(NumberFormatException e){
            return ErrorEvent.GeneralFailure;
        }

        return ErrorEvent.TaskSuccess;
    }


    public static ErrorEvent InputTimestamp(String dayString, String monthString, String yearString, String hourString, String minuteString, String secondString){

        ErrorEvent preCheckResult = preCheckInput(dayString, monthString, yearString, hourString, minuteString, secondString);

        if(preCheckResult != ErrorEvent.PreConditionPassed){
            return preCheckResult;
        }

        Day = Integer.parseInt(dayString);
        Month = Integer.parseInt(monthString);
        Year = Integer.parseInt(yearString);
        Hour = Integer.parseInt(hourString);
        Minute = Integer.parseInt(minuteString);
        Second = Integer.parseInt(secondString);

        preCheckResult = validateTimestamp();
        if(preCheckResult != ErrorEvent.TaskSuccess){
            return preCheckResult;
        }

        return ErrorEvent.TaskSuccess;
    }

    private static ErrorEvent preCheckInput(String dayString, String monthString, String yearString, String hourString, String minuteString, String secondString){
        if(dayString.length() == 0 || monthString.length() == 0 || yearString.length() == 0 || hourString.length() == 0 || minuteString.length() == 0 || secondString.length() == 0){
            return ErrorEvent.EmptyInput;
        }
        return ErrorEvent.PreConditionPassed;
    }

    private static ErrorEvent validateTimestamp(){

        //Validate Second
        if(Second < 0 || Second > 59){
            return ErrorEvent.InvalidRangeInput;
        }

        //Validate Minute
        if(Minute < 0 || Minute > 59){
            return ErrorEvent.InvalidRangeInput;
        }

        //Validate Hour
        if(Hour < 0 || Hour > 23){
            return ErrorEvent.InvalidRangeInput;
        }

        //Validate Month
        if(Month < 1 || Month > 12){
            return ErrorEvent.InvalidRangeInput;
        }

        //Validate Day
        int maxDay = 31;
        switch(Month){
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                if((Year % 4 == 0 && Year % 100 != 0) || (Year % 400 == 0)){
                    maxDay = 29;
                }else{
                    maxDay = 28;
                }
                break;
            default:
                maxDay = 31;
        }

        if(Day < 1 || Day > maxDay){
            return ErrorEvent.InvalidRangeInput;
        }

        return ErrorEvent.TaskSuccess;
    }

    public static void toDate(){
        // ---- Step 1: extract H/M/S ----
        Day = (int)(TimestampValue / 86400L);
        long rem = TimestampValue % 86400L;
        if(rem < 0){
            rem += 86400L;
            Day -= 1;
        }

        Hour = (int)(rem / 3600L);
        rem %= 3600L;
        Minute = (int)(rem / 60L);
        Second = (int)(rem % 60L);

        // ---- Step 2: convert days → Y/M/D ----
        Day += 719468; // days since 0000-03-01

        long era = (Day >= 0 ? Day : Day - 146096) / 146097;
        long dayofEra = Day - era * 146097;          // [0, 146096]

        long yearofEra = (400*dayofEra + 591) / 146097; // [0, 399]
        long dayofYear = dayofEra - (365*yearofEra + yearofEra/4 - yearofEra/100 + yearofEra/400); // [0, 365]

        long mp = (5*dayofYear + 2) / 153;               // [0, 11]

        Day = (int)(dayofYear - (153*mp + 2)/5 + 1);     // [1, 31]
        Month = (int)(mp < 10 ? mp + 3 : mp - 9);        // [1, 12]
        Year = (int)(yearofEra + era * 400 + (Month <= 2 ? 1 : 0)); // year
    }

    public static void toTimestamp(){
        TimestampValue = 0;

        // Step A: adjust year and month
        if (Month <= 2) {
            Year -= 1;
            Month += 12;
        }

        // Step B: calculate days since 0000-03-01
        long days = 365L * Year
                   + Year / 4
                   - Year / 100
                   + Year / 400
                   + (153L * (Month - 3) + 2) / 5
                   + Day - 1;

        // Step C: compute days since 1970-01-01
        long daysEpoch = 719468; // days until 1970-01-01
        long daysSince1970 = days - daysEpoch;

        TimestampValue = daysSince1970 * 86400L + Hour * 3600L + Minute * 60L + Second;
    }


}
