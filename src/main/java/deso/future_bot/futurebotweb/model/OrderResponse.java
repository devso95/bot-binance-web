package deso.future_bot.futurebotweb.model;

import org.jetbrains.annotations.NotNull;

public class OrderResponse {
    @NotNull
    private String side;
    private double price;
    private double qty;
    private double executedQty;

    private String status;

    private long time;

    private String explanation;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(double executedQty) {
        this.executedQty = executedQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
