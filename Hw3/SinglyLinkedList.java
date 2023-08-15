package hw3;

public class SinglyLinkedList {
    Node head;
    String listName;
    
    public SinglyLinkedList(String name){
        listName = name;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            Node current = head;
            if(head.next == null) head = null;
            //ถ้ามี node แค่ตัวเดียว list ให้ head ชี้ลง null เลย
            else{
                while(current.next.next != null){ //เช็คว่าตัวถัดไปชี้ลง null มั้ย
                    /*while loop เพื่อให้ current ชี้ที่ตัวก่อนหน้าของตัวสุดท้าย*/
                    current = current.next;
                }
                current.next = null;
                /*ให้ตัว current ชี้ลง null ไปเลย เพื่อที่ให้ node สุดท้ายถูก java ลบทิ้ง
                เพราะไม่มีอะไรไปชี้มันแล้ว*/
                
            }
        }     
    }
    
    public void popFront(){ //O(1)
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            head = head.next; //ให้ head ไปชี้ที่ตัวถัดไปแทน เพื่อที่จะให้ตัวแรกหลุดจาก list
        }
    }
    
    public Node topFront(){ //แสดงตัวแรกสุดของ list
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return new Node(head.student_id,head.name,head.gpa);
        }
    }
    
    public Node topBack(){ 
        if (isEmpty()){
           System.out.println("ERROR");
           return new Node("Empty List!");
        } else {
            Node current = head;
            while(current.next!=null){
                current = current.next;
                /*while loop เพื่อให้ current ไปชี้ที่ตัวสุดท้าย*/
            }
        return new Node(current.student_id,current.name,current.gpa);
        //return ตัวสุดท้ายออกมา
        }
    }
    
    public void pushFront(Node node){ //O(1)
        if (isEmpty()){
            //ถ้าเป็น list เปล่า ให้ head ชี้ไปที่ node ที่ต้องการ push เข้า
            head = node;
        }else{
            /*ถ้า list ไม่ว่าง ให้ node ที่ต้องการ push ชี้ไปที่เดียวกับที่ head ชี้
            แล้วย้าย head ไปชี้ที่ node ที่ต้องการ push เข้า*/
            node.next = head;
            head = node;
        }
    }
    
    public void pushBack(Node node) { //O(n) เพราะต้องวนให้ถึงตัวสุดท้าย
        Node current = head;
        if (isEmpty()){
            //ถ้าเป็น list เปล่า ให้ head ชี้ไปที่ node ที่ต้องการ push เข้า
            head = node;
        } else {
            //กรณีมี node มากกว่า 1 node
            while(current.next != null){
                current = current.next;
                //while loop เพื่อให้ current ไปชี้ที่ตัวสุดท้าย
            }
            //ข้างล่างนี้รวมกรณีมี  node เดียวอยู่ใน list ด้วย
            current.next = node;
            //ย้าย current แต่เดิมที่ชี้ null ให้ชี้ที่ node ที่ push เข้า
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node current = head;
            while(current.student_id != id){
                current = current.next;
                if(current == null) return new Node("Student Not Found!");
                /*while loop เพื่อทำให้ current ชี้ไปยัง node ที่ต้องการหา ถ้าไม่เจอให้ return 
                ว่า ไม่มีนักเรียนอยู่ในนี้ */
            }
            return new Node(current.student_id,current.name,current.gpa);
            //return node ที่เจอแล้วออกมา
        }
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            if (head.student_id == id){
                /*ถ้าอยากลบ node ตัวแรกทิ้ง ให้ประกาศ temp เข้ามาเพื่อจำสิ่งที่ head ชี้
                แล้วทำการลบตัวแรกสุดทิ้งโดยการ popFront ออก แล้ว return temp ที่เก็บ node
                แรกเอาไว้เมื่อกี้*/
                Node temp = head;
                popFront();
                return temp;
            }else{
                Node cur = head;
                Node temp = head;
                while(cur.student_id != id && cur.next != null){
                    /*while loop เพื่อทำให้ cur ไปชี้ตัวที่มี student_id = id ที่เรารับมา
                    แล้วให้ temp ชี้ไปที่ตัวก่อนหน้า cur และถ้า cur ชี้ที่ตัวสุดท้ายก็จะหลุด loop
                    เช่นกัน*/
                    temp = cur;
                    cur = cur.next;
                }
                if(cur.student_id == id && cur.next != null){
                    /*ถ้า node ที่ต้องการจะลบไม่ใช่ node สุดท้าย ให้สร้าง node del ขึ้นมาเพื่อ 
                    copy node ที่ต้องการจะลบ ให้ตัวก่อนหน้าชี้ไปยังถัดจากตัวที่ต้องการจะลบ และให้
                    ตัวที่เราต้องการลบชี้ลง null ไปเลย แล้ว return node ที่เราต้องการลบออกมา
                    */
                    Node del = cur;
                    temp.next = cur.next;
                    cur.next = null;
                    return del;
                }else if(cur.student_id == id && cur.next == null){
                    /*ถ้า node ที่เราต้องการลบเป็น node สุดท้าย ให้ตัวก่อนหน้าชี้ลง null ไปเลย
                    แล้ว return node ที่เราต้องการลบออกมา*/
                    temp.next = null;
                    return cur;
                }else{
                    return new Node("Student Not Found!");
                }
            }
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
        Node current = head;      
        while (current.next != null && current.student_id != node1.student_id){
            current = current.next;
            /*while loop หา student_id ที่เหมือนกับ id ที่เราต้องการค้นหา มันจะไปหยุดที่ 
            node ที่เราต้องการพอดี*/
        }
        if(current.student_id == node1.student_id && current.next != null){
            /*ถ้าไม่ได้ไป add node หลังตัวสุดท้าย ให้ node2 ที่เราต้องการจะ add ไปชี้ที่เดียวกับ
            node1 ชี้ แล้วให้ node1 ไปชี้ที่ node2 แทน*/
            node2.next = current.next;
            current.next = node2;
        }else if(current.student_id == node1.student_id && current.next == null)
        {
            pushBack(node2);
            //ถ้า add node หลังตัวสุดท้าย ให้ pushBack เข้าไปเลย
        }
    }
    
    public void addNodeBefore(Node node1, Node node2){
        Node cur = head;
        Node temp = head;
        if(head.student_id == node1.student_id){
            //ถ้าจะ add ไปหน้าตัวแรกให้เรียนใช้ pushFront ได้เลย
            pushFront(node2);
        }else{
            while(cur.student_id != node1.student_id && cur.next != null){
                temp = cur;
                cur = cur.next;
                /*while loop เพื่อทำให้ cur ไปชี้ตัวที่มี student_id = id ที่เรารับมา
                แล้วให้ temp ชี้ไปที่ตัวก่อนหน้า cur และถ้า cur ชี้ที่ตัวสุดท้ายก็จะหลุด loop
                ช่นกัน*/
            }
            if(cur.student_id == node1.student_id && cur.next != null){
                /*ถ้า node1ไม่ใช่ node สุดท้าย ให้นำ node2 
                ไปชี้ที่เดียวกับ node1 ให้ temp ไปชี้ node2*/
                node2.next = node1;
                temp.next = node2;
            }else{
                /*กรณีที่ node1 เป็น node สุดท้าย ให้นำ node2 
                ไปชี้ที่เดียวกับ node1 ให้ temp ไปชี้ node2*/
                node2.next = node1;
                temp.next = node2;
            }
        }
    }
    
    public boolean isEmpty(){
        if(head == null) return true;
        return false;
        //เช็ค head = null (ว่างหรือไม่) ถ้าว่างให้ return true ถ้าไม่ return false
    }
    public void merge(SinglyLinkedList list){
        Node current = this.head;
        while(current.next != null){
            current = current.next;
            //วนลูปหาตัวสุดท้ายของ list แรก 
        }
        current.next = list.head;
        /*นำ current.next ของ list แรกที่เดิมชี้ null อยู่ ไปชี้ที่เดียวกับ head ของ list 
        ที่นำมา merge*/
    }
    
    public void printStructure(){
        Node current = head;
        System.out.print(listName + ": head -> ");
        while(current != null){
            System.out.print("{" + current.student_id + "}" + " -> ");
            current = current.next;
        }
        System.out.print("null");
        System.out.println("");
    }
    
    public Node whoGotHighestGPA(){
        Node highGpa = head;
            Node current = head;
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {         
            while(current != null){
                if(current.gpa >= highGpa.gpa){
                    highGpa = current;
                }
                current = current.next;
            }          
        }
        return new Node(highGpa.student_id, highGpa.name, highGpa.gpa);
        /*มอง highGpa เป็นหลัก แล้วมอง current เป็นตัวไล่ checked ไปเรื่อย ๆ 
        จนถึงตัวสุดท้ายของ list เมื่อเจอคนที่เกรดมากกว่าก็ให้ไปเปลี่ยน highGpa เป็น node ใหม่(คนใหม่)
        แล้ว return คนนั้นออกมาเลยเมื่อทำเสร็จ*/
    }
}

