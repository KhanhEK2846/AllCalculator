package Config_Specific.Converter;

import Config_Common.ErrorEvent;

public class FunctionConvert {

    public ErrorEvent validate(String input){
        try {
            if(input == null || input.isEmpty()){
                return ErrorEvent.EmptyInput;
            }else if(!input.matches("^-?(\\d+(\\.\\d*)?|\\.\\d+)$")){
                return ErrorEvent.InvalidInputFormat;
            }

            Double.parseDouble(input);

        } catch (NumberFormatException e) {
            return ErrorEvent.InvalidInputFormat;
        }
        return ErrorEvent.PreConditionPassed;
    }

    public static double KmphtoMs(Double input){
        return input * 1000 / 3600;
    }

    public static double MstoKmph(Double input){
        return input * 3600 / 1000;
    }

}
