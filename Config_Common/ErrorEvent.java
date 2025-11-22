package Config_Common;

public enum ErrorEvent {
    /*-----------------------------Success--------------------------- */
    BuildSuccess("Build Success",0),
    PreConditionPassed("Pre-condition passed",0),

    /*-----------------------------Failure--------------------------- */
    GeneralFailure("General Failure",1),
    UnderMaintainance("Calculator is under maintenance",1),
    UnsupportedCalculator("Calculator is not supported",1),

    EmptyInput("Input must not be empty",2),
    InvalidRangeInput("Input is invalid range",2),
    ConfigureInvalid("Configuration is invalid",2),
    InvalidInputFormat("Input format is invalid",2)





    ;
    private final String description;
    public final int priority;

    ErrorEvent (String description,int priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

}
