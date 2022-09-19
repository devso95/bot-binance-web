package deso.future_bot.futurebotweb.model;

import java.util.List;

public class StrategyInfo {
    private long id;
    private int openOrderCount;
    private int waitingOrderCount;
    private double averageEntry;
    private double quantity;
    private String pair;
    private double currentPosition;
    private double currentValue;
    private double currentLevel;
    private double markPrice;
    private double liquidation;
    private double pnl;
    private List<OrderResponse> openOrders;
    private List<OrderResponse> waitingOrders;
    private String errors;

    public int getOpenOrderCount() {
        return openOrderCount;
    }

    public void setOpenOrderCount(int openOrderCount) {
        this.openOrderCount = openOrderCount;
    }

    public int getWaitingOrderCount() {
        return waitingOrderCount;
    }

    public void setWaitingOrderCount(int waitingOrderCount) {
        this.waitingOrderCount = waitingOrderCount;
    }

    public double getAverageEntry() {
        return averageEntry;
    }

    public void setAverageEntry(double averageEntry) {
        this.averageEntry = averageEntry;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double currentPosition) {
        this.currentPosition = currentPosition;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(double currentLevel) {
        this.currentLevel = currentLevel;
    }

    public double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(double markPrice) {
        this.markPrice = markPrice;
    }

    public double getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(double liquidation) {
        this.liquidation = liquidation;
    }

    public double getPnl() {
        return pnl;
    }

    public void setPnl(double pnl) {
        this.pnl = pnl;
    }

    public List<OrderResponse> getOpenOrders() {
        return openOrders;
    }

    public void setOpenOrders(List<OrderResponse> openOrders) {
        this.openOrders = openOrders;
    }

    public List<OrderResponse> getWaitingOrders() {
        return waitingOrders;
    }

    public void setWaitingOrders(List<OrderResponse> waitingOrders) {
        this.waitingOrders = waitingOrders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getErrors() {
        return errors;
    }
}
