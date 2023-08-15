package hw3;

public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;
    
    public DoublyLinkedList(String name){
        this.listName = name;
    }
    
    public void popBack() {
      if (isEmpty()){
          System.out.println("ERROR");
      }else{
          //กรณีที่ tail กับ head ชี้ตัวเดียวกันคือเหลืออยู่ node เดียวใน list ให้ทั้งคู่ชี้ลง null เลย คือการไม่ชี้ใดๆ ที่ node นั้นเลย
          if(tail == head) {
              tail = null;
              head = null;
            }else{
              /*กรณีปกติให้ tail ชี้ที่เดียวกับที่ node สุดท้าย(node ที่ต้องการ pop)ชี้ 
                คือชี้ node รองสุดท้าย แล้วให้ node รองสุดท้ายที่ตอนแรกชี้ที่ node สุดท้ายอยู่
                ให้ชี้ลง nullจะได้ไม่มีอะไรมาชี้ตัวที่เราจะ pop ออก แล้วให้ node ที่ตอนนี้กลายเป็น 
                node สุดท้ายแล้วให้ชี้ next ลง null*/
              tail = tail.previous;
              tail.next.previous = null;
              tail.next = null;
            }
      }
    }
     
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            if(tail == head){
                tail = null;
                head = null;
            }else{
                /*กรณีปกติให้ head ชี้ที่เดียวกับที่ node แรก(node ที่ต้องการ pop)ชี้ 
                คือชี้ node ที่อยู่ถัดไป แล้วให้ node ที่ 2 ที่ตอนแรกชี้ที่ node แรกอยู่ให้ชี้ลง null
                จะได้ไม่มีอะไรมาชี้ตัวที่เราจะ pop ออก แล้วให้ node ที่ตอนนี้กลายเป็น node แรกแล้ว
                ให้ชี้ previous ลง null*/
                head = head.next;
                head.previous.next = null;
                head.previous = null;               
            }          
        }
    }
    
    public Node topFront(){ 
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            return new Node(head.student_id, head.name, head.gpa);
        }
    }
    
    public Node topBack(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
          return new Node(tail.student_id, tail.name, tail.gpa);

        }
    }
    
    public void pushFront(Node node){        
        if (isEmpty()){
            //ถ้าเป็น list เปล่า ให้ head และ tail ชี้ที่ node ที่เรา push เข้าไป
            head = node;   
            tail = node;
        }else{
            /*ถ้า list ไม่ว่าง ให้ node ที่เราต้องการ push เข้าชี้ที่เดียวกับที่ head ชี้
            ให้ previous ของ node แรกที่ head ชี้อยู่เปลี่ยนจากชี้ null เป็น 
            ชี้ที่ node ที่เราต้องการ push เข้า แล้วย้าย head ไปชี้ที่ node ที่เรา push เข้ามา*/
            node.next = head;
            head.previous = node;         
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()) {
            //ถ้าเป็น list เปล่า ให้ head และ tail ชี้ที่ node ที่เรา push เข้าไป
            tail = node;
            head = node;
        } else {
            /*ถ้า list ไม่ว่าง ให้ node ตัวสุดท้ายชี้ที่ตอนแรกชี้ที่ null อยู่ ให้เปลี่ยนมาชี้
            ที่ node ที่เราจะ push เข้า แล้วให้ previous ของ node ที่เรา push เข้า
            ชี้ที่เดียวกับ tail ชี้คือชี้ตัวสุดท้ายในตอนแรก แล้วย้าย tail มาชี้ที่ node ที่เรา
            push เข้าไป ให้กลายเป็น node สุดท้ายของ list*/
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }

    public Node findNode(int id){
        Node cur = head;
        if (isEmpty()){
            return new Node("Empty List!");
        }else{
            while (cur.student_id != id && cur.next != null) {
                cur = cur.next;
                /*วนลูปหา student_id ที่เหมือนกับ id ที่เราต้องการค้นหา มันจะไปหยุดที่ 
                node ที่เราต้องการพอดี*/
            }
            if(cur.next == null && cur.student_id != id) return new Node("Student Not Found!");
            //ถ้าหาจนครบแล้วจนถึงตัวสุดท้ายและไม่เจอ id ที่ตรงกันเลยให้ return บอกว่า ไม่มีนักเรียนอยู่ในนี้
            else return cur;
        }
    }
    
    public Node eraseNode(int id){
        Node cur = head;
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        }else{
            while (cur.next != null && cur.student_id != id) {
                cur = cur.next;
                /*วนลูปหา student_id ที่เหมือนกับ id ที่เราต้องการค้นหา มันจะไปหยุดที่ 
                node ที่เราต้องการพอดี*/
            }  
            if(cur.student_id == id && cur.next != null && cur.previous != null){ 
                /*กรณีที่ node ไม่ได้เป็นตัวแรกและตัวสุดท้าย ให้ copy node ของ cur ที่ต้องการลบ
                เข้าไปที่ temp ให้ตัวก่อนหน้าของ cur ชี้ไปที่ตัวถัดไปจาก cur และให้ตัวถัดไปจาก cur 
                ชี้ไปที่ตัวก่อนหน้าของ cur เพื่อไม่ให้อะไรชี้ไปที่ตัวที่เราต้องการจะลบเลย และ return 
                ตัวที่เราต้องการลบออกมา*/
                Node temp = cur;
                cur.previous.next = cur.next;
                cur.next.previous = cur.previous;
                return temp;
            }else if(cur.student_id == id && cur.next == null){
                /*กรณีที่ต้องการลบตัวสุดท้าย ให้ tail ชี้ไปที่ตัวก่อนหน้าของ cur (ตัวรองสุดท้าย)
                และตัวรองสุดท้ายให้ชี้ตัวถัดไปลง null เพื่อจะได้ตัดเส้นที่ชี้ตัวสุดท้ายออก 
                และให้เส้นชี้ตัวก่อนหน้าของ cur ชี้ลง null ไป และ return ตัวที่เราต้องการลบออกมา*/
                tail = cur.previous;
                tail.next = null;
                cur.previous = null;
                return cur;
            }else if(cur.student_id == id && cur.previous == null){
                /*กรณีที่ต้องการลบตัวแรกสุด ให้ copy node ของ cur ที่ต้องการลบ
                เข้าไปที่ temp แล้วให้ head ชี้ไปที่ตัวถัดไปที่จะต้องเป็น head เมื่อลบตัวแรกออก
                แล้วให้ตัวก่อนหน้าของ head ชี้ลง null เพื่อไม่ให้ไปชี้ตัวที่ต้องการจะลบ และให้
                ตัวถัดไปของตัวที่ต้องการจะลบชี้ลง null เพื่อไม่ให้มันมาชี้ตัวเดียวกับที่ head ชี้ 
                และ return ตัวที่เราต้องการลบออกมา*/
                Node temp = head;
                head = temp.next;
                head.previous = null;
                temp.next = null;
                return temp;
            }
            else return new Node("Student Not Found!");
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
       Node cur = head ;
        while(cur.student_id != node1.student_id && cur.next != null ){
            cur = cur.next;
            /*วนลูปหา student_id ที่เหมือนกับ id ที่เราต้องการค้นหา มันจะไปหยุดที่ 
            node ที่เราต้องการพอดี*/
        }
        if(cur.next != null){
            /*ถ้าไม่ได้ไป add node หลังตัวสุดท้าย ให้ node ที่เราต้องการที่จะ add ชี้ที่เดียวกับ
            ที่ cur ชี้ ตัวถัดไปของ cur ที่ชี้ไปที่มันให้ชี้ไปที่ node ที่เราจะ add แล้วให้ตัวถัดไป
            ของ cur ชี้ไปที่ node ที่เรา add เพื่อที่จะแทรกระหว่างกลาง แล้วให้ node ที่เรา add 
            ชี้ตัวก่อนหน้าไปหา cur*/
            node2.next = cur.next;
            cur.next.previous = node2;
            cur.next = node2;
            node2.previous = cur;}
       else pushBack(node2);  
        //ถ้า add node หลังตัวสุดท้าย ให้ pushBack เข้าไปเลบ
    }
    
    public void addNodeBefore(Node node1, Node node2){
        Node cur = tail ;
        while(cur.student_id != node1.student_id && cur.previous != null ){
            cur = cur.previous;
        }
        if(cur.previous != null){
            /*ถ้าไม่ได้ไป add node หน้าตัวหน้าสุด ให้ node ที่ต้องการ add ไปชี้ตัวก่อนหน้าที่เดียวกับ
            ตัวก่อนหน้าที่ cur ชี้ แล้วให้ตัวก่อนหน้า cur ไปชี้ที่ node ที่ต้องการ add 
            แล้วให้ previous ของ cur ไปชี้ที่ node ที่ต้องการ add เพื่อที่จะแทรกระหว่างกลาง 
            แล้วให้ node ที่เรา add ชี้ตัวถัดไปไปหา cur*/
        node2.previous = cur.previous;
        cur.previous.next = node2;
        cur.previous = node2;
        node2.next = cur;}
       else pushFront(node2);            
    }
    
    public boolean isEmpty(){
        if (head == null) return true;
        else return false;
    }
    public void merge(DoublyLinkedList list){
        this.tail.next = list.head;
        list.head.previous = this.tail;
        /*ให้ tail ของ list แรกชี้ที่ head ของ list ที่ 2 
        ให้ head ของ list ที่ 2 ชี้ที่ tail ของ list แรก*/
    }
    
    public void printStructure(){
        // ปริ้นลิสต์ทั้งหมดจาก head ไป tail
        Node current=head;
        System.out.print(listName + ": head <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.next;
        }
        System.out.println("tail");
       // System.out.println("Hello World!");
    }
    
    // This may be useful for you for implementing printStructure()
    public void printStructureBackward(){ 
        Node current=tail;
        System.out.print(listName + ": tail <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
    }
    
    public Node whoGotHighestGPA(){
        Node highGpa = head;
        Node current = head;
//        double max = 0;
        if (isEmpty()) {         
            return new Node("Empty List!");
        } else {
            while(current != null){
                if(current.gpa >= highGpa.gpa){ //ใช้ >= เพื่อกรณีเกรดเท่ากัน เพื่อ return ออกคนใกล้ tail สุด
                    highGpa = current;
                }
                current = current.next;
            }
            return new Node(highGpa.student_id, highGpa.name, highGpa.gpa);
            /*มอง highGpa เป็นหลัก แล้วมอง current เป็นตัวไล่ checked ไปเรื่อย ๆ 
            จนถึงตัวสุดท้ายของ list เมื่อเจอคนที่เกรดมากกว่าก็ให้ไปเปลี่ยน highGpa เป็น node ใหม่(คนใหม่)
            แล้ว return คนนั้นออกมาเลยเมื่อทำเสร็จ*/
        }
    }
}
