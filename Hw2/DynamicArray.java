package arrayfortest;

public class DynamicArray {
    private int[] arr;
    private int capacity;
    private int size;
    
    public DynamicArray (int cap){
        this.arr = new int[cap];
        this.capacity = cap;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void pushBack(int data){
        if (size == capacity){
            capacity *= 2;
            int[] newArr = new int[capacity];
            for(int i = 0; i < size; i++){
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[size] = data;
        size++;
    }
    
    public int popBack(){
        if(isEmpty()) {
            System.out.println("ERROR");
            return 0;
        }else{
            int popEle = arr[size-1];
            size--;
            return popEle;
        }
    }
    
    public int get(int i){
        if(i < 0 || i >= size || isEmpty()){
            System.out.println("ERROR");
            return 0;
        }else{
            return arr[i];
        }
    }
    
    public void set (int i,int value){
        if(i < 0 || i >= size || isEmpty()){
            System.out.println("ERROR");
        }else{
            arr[i] = value;
        }
    }
    
    public void remove(int i){
        if(i < 0 || i >= size || isEmpty()){
            System.out.println("ERROR");
        }else{
            for(int j = i; j < size - 1; j++){ //ค่าตัวหลังสุดที่เดิมมันจะไม่มีละ
                arr[j] = arr[j+1];
            }
            size--;
        }  
    }
    
    public int getSize(){
        return size;
    }
    
    public void printStructure(){
        System.out.print("Size = " + size + " Cap = " + capacity + " arr = [ ");
        for(int i = 0; i < size; i++){
            System.out.print(arr[i]);
            if(i != size - 1) System.out.print(", ");
        }
        System.out.println(" ]");
    }
}
