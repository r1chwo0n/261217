package hw5;

public class Main {
    
    public static Node constructTree1(){ //ฟังก์ชั้นเพิ่ม node เข้าไปใน tree (สร้าง tree)
    Node root = new Node(3); // root node
    //ถ้าเราอยากเข้าถึงในแต่ละ leaf ก็ให้ .left หรือ .right ต่อกันไปตามตำแหน่งที่ต้องการ
    //โค้ดด้านล่างนี้ทำการ add node เข้าไปในแต่ละ leaf
    //ฝั่งใบทางซ้ายของ root
    root.left = new Node(7);
    root.left.left = new Node(2);
    root.left.right = new Node(6);
    root.left.right.left = new Node(1);
    root.left.right.right = new Node(8);
    //ฝั่งใบทางขวาของ root
    root.right = new Node(5);
    root.right.right = new Node(9);
    root.right.right.left = new Node(4);
    return root;
}
    public static Node constructTree2(){//ฟังก์ชั้นเพิ่ม node เข้าไปใน tree (สร้าง tree)
    Node root = new Node(1); // root node
    //ถ้าเราอยากเข้าถึงในแต่ละ leaf ก็ให้ .left หรือ .right ต่อกันไปตามตำแหน่งที่ต้องการ
    //โค้ดด้านล่างนี้ทำการ add node เข้าไปในแต่ละ leaf
    //ฝั่งใบทางซ้ายของ root
    root.left = new Node(2);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(8);
    root.left.right.right.right = new Node(10);
    //ฝั่งใบทางขวาของ root
    root.right = new Node(3);
    root.right.right = new Node(6);
    root.right.right.left = new Node(9);
    
    return root;
}
    
    public static void main(String[] args) {      
        Node tree = Main.constructTree2();
        tree.printTree();
        tree.printBFT();
        tree.printDFT();  
    }
}
