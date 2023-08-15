package hw4;

public class Stock {
    private List list;
    private int totalShares;
    
    public Stock(String costBasis){
        switch (costBasis) {
            case "FIFO":
                list = new Queue();
                break;
            case "LIFO":
                list = new Stack();
                break;
            default:
                System.out.println("Invalid cost basis. Choose FIFO or LIFO");
                break;
        }
    }
    
    public void buy(int boughtShares, double boughtPrice){
        //เมื่อเกิดการซื้อหุ้น ก็จะ push จำนวนหุ้นที่ซื้อและราคาหุ้นในเวลานั้นลงไปใน list ที่เราเลือกไว้
        list.push(new Node(boughtShares, boughtPrice)); 
        totalShares += boughtShares;//จำนวนหุ้นทั้งหมดที่ซื้อ
    }
    
    public void sell(int soldShares, double soldPrice){ 
        //กรณีหุ้นทั้งหมดมีมากพอที่จะขายออก
        if (soldShares <= totalShares){
            double realizedGain = 0.0; //กำไรขาดทุนที่เกิดขึ้นจริง
            double unrealizedGain = 0.0; //กำไรขาดทุนทางบัญชี           
            totalShares -= soldShares; //จำนวนหุ้นที่เหลือหลังจากขายออกไป
            
            //while loop เพื่อเช็คว่ามี node อยู่ใน list และ หุ้นที่จะขายออกมากกว่า 0 คือมีการขายออกจริงเกิดขึ้น
            while (list.top() != null && soldShares > 0){
                if(soldShares >= list.top().shares){ //เช็คถ้าจำนวนหุ้นที่จะขายมากกว่าจำนวนหุ้นใน node แรก 
                    /*แสดงว่าเกิดกำไรขาดทุนจริง โดยให้นำราคาหุ้นที่ขายลบกับราคาหุ้นในตอนนั้น (ใน node แรก)
                    คูณกับจำนวนหุ้นที่อยู่ใน node แรก*/
                    realizedGain += (soldPrice - list.top().price)*list.top().shares; 
                    soldShares -= list.top().shares; //อัพเดตจำนวนหุ้นที่จะขายว่าเหลืออยู่เท่าไหร่
                    list.pop(); //pop หุ้นใน node นี้ทิ้งเพื่อย้ายไปเช็ค node ถัดไป
                }else{ //กรณีจำนวนหุ้นที่จะขายน้อยกว่าจำนวนหุ้นใน node แรก
                    realizedGain += (soldPrice - list.top().price)*soldShares;
                    /*คิดกำไรขาดทุนที่เกิดขึ้นจริงเหมือนเดิม แต่เปลี่ยนตัวคูณเป็นจำนวนหุ้นที่จะขายออกแทน
                    เพราะเราไม่ได้ขายหุ้นทั้งหมดใน node แรก (จำนวนหุ้นใน node แรกมากกว่าจำนวนที่จะขาย)
                    เราจะขายแค่หุ้นที่เหลือที่จะต้องขาย*/
                    list.top().shares -= soldShares; //อัพเดตจำนวนหุ้นใน node แรก
                    soldShares = 0; //เซ็ตจำนวนหุ้นที่ขายออกเป็น 0 คือการขายสิ้นสุดลงเพื่อออกจาก loop
                }
            }
            
            //คำนวณหากำไรขาดทุนที่เกิดขึ้นทางบัญชี ไม่ได้ขายหุ้นใดออกไปจริงหรือคำนวณจากหุ้นที่เหลือจากการขายจริง
            Node current = list.top();//ประกาศ node current ขึ้นเพื่อเลื่อนเช็ค node แต่ละตัว
            while(current != null){//while loop เช็คจนถึงตัวสุดท้าย
                //คำนวณหาโดยนำราคาหุ้นที่ขายลบกับราคาหุ้นในตอนนั้น (ราคาหุ้นใน node นั้น) คูณกับจำนวนหุ้นที่เหลืออยู่ใน node นั้น
                unrealizedGain += (soldPrice - current.price)*current.shares;
                current = current.next;//เลื่อนไป node ถัดไป
            }             
            System.out.println("Realized P/L = " + realizedGain + " Unrealized P/L = " + unrealizedGain);
        }else{
            System.out.println("Sell command rejected");
        }
    }
    
    public void showList(){
        Node currentNode = list.top();
        System.out.print("head -> ");
        while (currentNode!=null){
            System.out.print("[" + currentNode.shares + "@" + currentNode.price + "B] -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }
}