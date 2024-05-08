package club.libridge.libridgebackend.core.rulesets;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.core.Strain;
import club.libridge.libridgebackend.core.rulesets.abstractrulesets.Ruleset;

public final class RulesetFromStrainString {

    private RulesetFromStrainString() {
        throw new IllegalStateException("Utility class");
    }

    private static Map<String, Ruleset> shortDescriptionOfRulesets = new HashMap<String, Ruleset>();

    // Static initialization block to avoid doing this calculation every time
    // identify(..) is called.
    static {
        for (Strain strain : Strain.values()) {
            Ruleset current = strain.getPositiveRuleset();
            shortDescriptionOfRulesets.put(strain.getName(), current);
        }
    }

    public static Ruleset identify(String strain) {
        return shortDescriptionOfRulesets.get(strain);
    }

}