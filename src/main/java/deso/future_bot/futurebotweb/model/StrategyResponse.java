package deso.future_bot.futurebotweb.model;

import java.time.Instant;

public class StrategyResponse {
    private String coin;
    private long id;
    private double stopLoss;
    private double liquidation;
    private double target;
    private StrategyState state;
    private Instant createdDate;
    private double crossRate;
    private String apiKey;
    private String secretKey;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(double liquidation) {
        this.liquidation = liquidation;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public StrategyState getState() {
        return state;
    }

    public void setState(StrategyState state) {
        this.state = state;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public double getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(double crossRate) {
        this.crossRate = crossRate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
