package edu.ithaca.dragon.bank;

public class AccountEvent {
    protected String eventType;
    protected double eventAmount;
    protected boolean suspicious;

    AccountEvent(String eventType, double eventAmount, double totalAccountValue){
        this.eventType = eventType;
        this.eventAmount = eventAmount;
        setSuspicious(totalAccountValue);
    }

    public double getEventAmount() {
        return eventAmount;
    }

    public String getEventType() {
        return eventType;
    }

    public boolean getSuspicious(){
        return suspicious;
    }

    /**
     * @param totalAccountValue is a double representing the total value of the account before the event occurred
     * @post sets suspicious to true if the event consists of transferring out or withdrawing more than half the account's total value
     */
    public void setSuspicious(double totalAccountValue) {
        if(eventAmount > totalAccountValue / 2 && (eventType.equals("Transfer out") || eventType.equals("Withdraw")))
            suspicious = true;
        else suspicious = false;
    }

    public String toString(){
        return eventType + " " + eventAmount;
    }
}
