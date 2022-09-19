package deso.future_bot.futurebotweb;

import deso.future_bot.futurebotweb.model.StrategyState;

import java.time.Instant;

public class Strategy {

    private long id;
    private String coin;
    private double stopLoss;
    private double liquidation;
    private double target;
    private StrategyState state;
    private String secretKey;
    private String apiKey;
    private long userId;
    private double crossRate;
    private Long refId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(double crossRate) {
        this.crossRate = crossRate;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }
}
