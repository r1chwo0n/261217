package hw5;

public class Stack { //LIFO
    Node[] arr; // regular array
    int capacity;
    int size;

    public Stack(int cap){ //constructor ของ class stack รับ parameter เป็น ความจุ (capacity)
        arr = new Node[cap]; //arr เก็บตัวแปร Node ที่มีขนาดเท่ากับ cap
        this.capacity = cap;
    }
    
    public void push(Node node){ //ฟังก์ชัน push เอาไว้ push node เข้าไปใน array (pushBack)
        if (!isFull()){ //กรณี array ยังไม่เต็ม (size != capacity)
            arr[size] = node; //add node เข้าไปใน array
            size++;//อัพเดตค่า size เพื่อบอกว่ามีการเพิ่มสมาชิกเข้าไปใน array 
        }else{
            System.out.println("Stack Overflow!!!"); //กรณี array เต็มแล้ว
        }
    }
    public Node pop(){//popBack
        if (!isEmpty()){//มีสมาชิกเหลืออยู่ใน array
            Node removeEle = arr[size-1]; //ประกาศตัวแปรขึ้นมาเพื่อเก็บค่า node ที่ต้องการจะ pop ออก
            size--;//ทำการลด size ลงเพื่ออัพเดตจำนวนสมาชิกใน array
            return removeEle;
        }else{ //ไม่มีสมาชิกเหลืออยู่ใน array แล้ว
            System.out.println("Stack Underflow!!!");
            return null;
        }
    }
    public boolean isFull(){
        return size == capacity;
    }
    public boolean isEmpty(){
        return size == 0; 
    }
    
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            //for loop เพื่อเข้าถึงสมาชิกแต่ละตัวใน array
            for (int i = 0; i < size; i++){
                System.out.print(arr[i].data + " ");
            }
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}
