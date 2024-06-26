package club.libridge.libridgebackend.core;

public final class MessageTypes {

    private MessageTypes() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DEAL_MESSAGE = "deal";
    public static final String FINISH_DEAL_MESSAGE = "finishDeal";
    public static final String INITIALIZE_DEAL_MESSAGE = "initializeDeal";
    public static final String INVALID_RULESET_MESSAGE = "invalidRuleset";
    public static final String VALID_RULESET_MESSAGE = "validRuleset";
    public static final String STRAIN_CHOOSER_MESSAGE = "strainChooser";

}
