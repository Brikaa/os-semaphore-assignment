package primewriter.gui;

public class Notification {
    // One observer only : the stop watch
    private IObserver[] clock = new IObserver [1];
    
    public void register(IObserver observer){clock[0] = observer;};
    // paramter not used till more observers are noted
    public void unregister(IObserver observer){clock[0]= null;};
    public long notifiy(String message){return clock[0].update(message);};
}