package arrayfortest;

public class ArrayforTest {


    public static void main(String[] args) {
        DynamicArray var = new DynamicArray(2);
        var.printStructure();
        var.pushBack(5);
        var.pushBack(6);
        var.printStructure();
        var.pushBack(7);
        var.printStructure();
        var.pushBack(8);
        var.printStructure();
        var.pushBack(9);
        var.printStructure();
    }
    
}
