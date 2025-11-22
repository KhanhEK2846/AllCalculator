package Config_Common;

import Config_Specific.AES_CMAC.AES_CMAC_GUI;
import Config_Specific.Basic_Calculator.BasicCalculator;
import Config_Specific.CRC.CRC_GUI;
import Config_Specific.Converter.Converter_GUI;

public class SupportCalculatorROM{

    private Calculator[] calculators;

    public SupportCalculatorROM(){
    }

    public void InitCalculators(){
        calculators = new Calculator[SupportCalculatorEnum.values().length];
        calculators[SupportCalculatorEnum.Basic_Calculator.ordinal()] = new BasicCalculator();
        calculators[SupportCalculatorEnum.AES_CMAC_Calculator.ordinal()] = new AES_CMAC_GUI();
        calculators[SupportCalculatorEnum.CRC_Calculator.ordinal()] = new CRC_GUI();
        calculators[SupportCalculatorEnum.MultiDivShift_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.Converter_Calculator.ordinal()] = new Converter_GUI();
        calculators[SupportCalculatorEnum.Timestamp_Calculator.ordinal()] = null;

    }

    public Calculator GetCalculator(SupportCalculatorEnum calculatorEnum) throws Exception{
        return calculators[calculatorEnum.ordinal()];
    }
}
