package Config_Common;

import Config_Specific.AES_CMAC.AES_CMAC_GUI;
import Config_Specific.Basic_Calculator.BasicCalculator;
import Config_Specific.CRC.CRC_GUI;
import Config_Specific.Converter.Converter_GUI;
import Config_Specific.DateBetween.DateBetween_GUI;
import Config_Specific.Timestamp.Timestamp_GUI;

public class SupportCalculatorROM{

    private Calculator[] calculators;

    /*---------------------------List of Calculator is not allow------------------------- */
    private static final boolean SuppressedCalculator[] = {
        false,          // Basic Calculator
        false,          // AES CMAC
        false,          // CRC
        true,           // MultiDivShift
        false,          // Converted Unit
        false,          // Timestamp
        true,           // Date Between
        true,           // Loan
        true,           // Interest
        true,           // BMI
        true,           // BMR
        true,           // Radix

    };

    public SupportCalculatorROM() throws Exception{
        InitCalculators();

        if(!ValidateConfiguration())
            throw new IllegalArgumentException("");

    }

    private void InitCalculators(){
        calculators = new Calculator[SupportCalculatorEnum.values().length];

        /*----------------------------------List of calculator is developed----------------------------- */
        calculators[SupportCalculatorEnum.Basic_Calculator.ordinal()] = new BasicCalculator();
        calculators[SupportCalculatorEnum.AES_CMAC_Calculator.ordinal()] = new AES_CMAC_GUI();
        calculators[SupportCalculatorEnum.CRC_Calculator.ordinal()] = new CRC_GUI();
        calculators[SupportCalculatorEnum.MultiDivShift_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.Converter_Calculator.ordinal()] = new Converter_GUI();
        calculators[SupportCalculatorEnum.Timestamp_Calculator.ordinal()] = new Timestamp_GUI();
        calculators[SupportCalculatorEnum.DateBetween_Calculator.ordinal()] = new DateBetween_GUI();
        calculators[SupportCalculatorEnum.Loan_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.Interest_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.BMI_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.BMR_Calculator.ordinal()] = null;
        calculators[SupportCalculatorEnum.Radix_Calculator.ordinal()] = null;

    }

    private boolean ValidateConfiguration(){

        if((SuppressedCalculator.length != SupportCalculatorEnum.values().length) ||
            (calculators.length != SuppressedCalculator.length))
            return false;


        return true;
    }

    public Calculator GetCalculator(SupportCalculatorEnum calculatorEnum) throws Exception{
        return calculators[calculatorEnum.ordinal()];
    }

    public boolean IsSuppressed(SupportCalculatorEnum calculatorEnum) throws Exception{
        return SuppressedCalculator[calculatorEnum.ordinal()];
    }
}
