package Config_Specific.Converter;

import java.util.function.Function;

import Config_Common.ErrorEvent;
import Utilities.PopUp;

public enum UnitConverter_Enum {

    /*------------------------------------------Velocity----------------------------------------- */
    KMPH_TO_MPH("km/h","m/s",FunctionConvert::KmphtoMs),
    MPH_TO_KMPH("m/s","km/h",FunctionConvert::MstoKmph),
    MPH_TO_MS("mph","m/s",FunctionConvert::MphtoMs),
    MS_TO_MPH("m/s","mph",FunctionConvert::MstoMph),

    /*------------------------------------------Temperature----------------------------------------- */
    C_TO_F("Celsius","Fahrenheit", FunctionConvert::CtoF),
    F_TO_C("Fahrenheit","Celsius", FunctionConvert::FtoC),
    C_TO_K("Celsius","Kelvin", FunctionConvert::CtoK),
    K_TO_C("Kelvin","Celsius", FunctionConvert::KtoC)


    ;
    public final String InputUnit;
    public final String ConvertedUnit;
    private Function<Double, Double> conversionFunction;

    UnitConverter_Enum(String InputUnit, String ConvertedUnit, Function<Double, Double> conversionFunction) {
        this.InputUnit = InputUnit;
        this.ConvertedUnit = ConvertedUnit;
        this.conversionFunction = conversionFunction;
    }

    public double executeConversion(double input) throws Exception{
        if(conversionFunction == null){
            new PopUp(ErrorEvent.ConfigureInvalid);
            return -1;
        }
        return conversionFunction.apply(input);
    }
}
