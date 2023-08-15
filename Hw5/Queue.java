package hw5;

public class Queue { //FIFO
    Node[] arr; // circular array
    int capacity;
    int front;
    int back;//ชี้ช่องว่างหลังสุด
    int size;
    
    public Queue(int cap){ //constructor ของ Queue รับ parameter เป็น capacity (ความจุ)
        arr = new Node[cap]; //arr เก็บตัวแปร Node ที่มีขนาดเท่ากับ cap
        this.capacity = cap;
    }
    
    public void enqueue(Node node){ //ฟังก์ชันนี้ทำงานคล้ายการ pushBack
        if (!isFull()){ //กรณี array ยังไม่เต็ม
            arr[back] = node; //เพิ่ม node เข้าไปในช่องว่างหลังสุด
            back = (back + 1) % capacity;
            /*อัพเดตช่องว่างหลังสุด เนื่องจากเป็น circular array เมื่อถึงตัวสุดท้ายของ array แล้ว
            จะชี้ต่อไปยังช่องแรก (ถ้า array แรกว่างแล้ว)*/
            size++;//อัพเดตค่า size เพื่อบอกว่ามีการเพิ่มสมาชิกเข้าไปใน array
        }else{ //กรณีที่ array เต็มแล้ว ไม่มีช่องว่างใน array เหลือแล้ว
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){ //ฟังก์ชันนี้ทำงานคล้ายการ popfront
        if (!isEmpty()){ //กรณีมีสมาชิกเหลืออยู่ใน array
            Node removeEle = arr[front]; //ประกาศตัวแปรมาเก็บสมาชิกตัวที่เราจะ dequeue ออก
            front = (front + 1) % capacity; /*อัพเดตตำแหน่งที่สามารถ dequeue ออกได้ 
            เมื่อต้องการจะ dequeue ตัวสุดท้ายใน array จะย้ายไปชี้ที่ตัวแรกอีกรอบ */
            size--; //ทำการลด size ลงเพื่ออัพเดตจำนวนสมาชิกใน array
            return removeEle;
        }else{ //ไม่มีสมาชิกเหลืออยู่ใน array แล้ว
            System.out.println("Queue Underflow!!!");
            return null;
        }
    }
    
    public boolean isEmpty(){
        return size == 0; 
    }
    
    public boolean isFull(){
        return size == capacity; 
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            int count = 0;//ประกาศตัวแปรขึ้นมาเพื่อแสดงว่า print ไปกี่ตัวแล้ว
            //for loop ด้านล่างนี้เริ่มจาก front วนไปเรื่อย ๆ จนครบทุกตัว
            for (int i = front; count < size; i = (i + 1) % capacity){
                System.out.print(arr[i].data + " ");
                count++;
            }
            System.out.println("[Back]");
        }else{
            System.out.println("Empty Queue!!!");
        }
    }
}
