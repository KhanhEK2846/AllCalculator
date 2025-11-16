package Config_Common;

public enum ErrorEvent {
    /*-----------------------------Success--------------------------- */
    BuildSuccess("Build Success",0),

    /*-----------------------------Failure--------------------------- */
    GeneralFailure("General Failure",1),

    EmptyInput("Input must not be empty",2),
    InvalidRangeInput("Input is invalid range",2),
    ConfigureInvalid("Configuration is invalid",2),





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
