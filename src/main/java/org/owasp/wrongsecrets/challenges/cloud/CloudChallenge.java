package org.owasp.wrongsecrets.challenges.cloud;

import org.owasp.wrongsecrets.RuntimeEnvironment;
import org.owasp.wrongsecrets.ScoreCard;
import org.owasp.wrongsecrets.challenges.Challenge;

public abstract class CloudChallenge extends Challenge {

    private final RuntimeEnvironment runtimeEnvironment;

    protected CloudChallenge(ScoreCard scoreCard, RuntimeEnvironment runtimeEnvironment) {
        super(scoreCard);
        this.runtimeEnvironment = runtimeEnvironment;
    }

    public boolean isAWS() {
        return this.runtimeEnvironment.getRuntimeEnvironment() == RuntimeEnvironment.Environment.AWS;
    }

    public boolean isGCP() {
        return this.runtimeEnvironment.getRuntimeEnvironment() == RuntimeEnvironment.Environment.GCP;
    }

    public boolean isAzure() {
        return this.runtimeEnvironment.getRuntimeEnvironment() == RuntimeEnvironment.Environment.AZURE;
    }

    @Override
    public String getExplanation() {
        return getData(super.getExplanation());
    }

    @Override
    public String getHint() {
        return getData(super.getHint());
    }

    @Override
    public String getReason() {
        return getData(super.getReason());
    }

    private String getData(String defaultAWsPath) {
        RuntimeEnvironment.Environment env = runtimeEnvironment.getRuntimeEnvironment();
        return switch (env) {
            case GCP -> String.format("%s%s", defaultAWsPath, "-gcp");
            case AZURE -> String.format("%s%s", defaultAWsPath, "-azure");
            default -> String.format("%s", defaultAWsPath); // Default is AWS
        };
    }
}
