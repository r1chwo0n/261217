package hw4;

public class Main {

    public static void main(String[] args) {
        Stock ptt = new Stock("FIFO");
        ptt.buy(10, 100);
        ptt.buy(10, 150);
        ptt.buy(20, 110);
        ptt.buy(20, 160);
        ptt.showList();
        ptt.sell(25, 130);
        ptt.showList();

    }
    
}
