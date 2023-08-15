package hw5;

public class Node extends BTreePrinter {

    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public void printTree() {
        super.printTree(this);
    }

    public void printBFT() {
        Queue q = new Queue(50); //queue ที่จุได้ 50
        System.out.print("BFT node sequence [ ");
        q.enqueue(this); // add root node เข้า queue
        while(!q.isEmpty()){ // loop เช็คว่าถ้า queue ไม่ว่าง
            Node node = q.dequeue();// dequeue node แรกออกมา ไว้ใน node
            System.out.print(node.data + " ");//print data ใน node นั้น
            if(node.left != null) q.enqueue(node.left); //เช็คว่า node นั้นมีลูกทางซ้ายมั้ย ถ้ามีก็ enqueue เข้าไป ถ้าไม่ก็ข้าม
            if(node.right != null) q.enqueue(node.right);//เช็คว่า node นั้นมีลูกทางขวามั้ย ถ้ามีก็ enqueue เข้าไป ถ้าไม่ก็ข้าม
        }
        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50); //stack ที่จุได้ 50
        System.out.print("DFT node sequence [ ");
        s.push(this); // add root node เข้า stack
        while(!s.isEmpty()){ // loop เช็คว่าถ้า stack ไม่ว่าง
            Node node = s.pop(); // pop node แรกออกมา ไว้ใน node
            System.out.print(node.data + " "); //print data ใน node นั้น
            if(node.right != null) s.push(node.right); //เช็คว่า node นั้นมีลูกทางขวามั้ย ถ้ามีก็ push เข้าไป ถ้าไม่ก็ข้าม
            if(node.left != null) s.push(node.left); //เช็คว่า node นั้นมีลูกทางซ้ายมั้ย ถ้ามีก็ push เข้าไป ถ้าไม่ก็ข้าม
        }
        System.out.println("]");
    }
}
