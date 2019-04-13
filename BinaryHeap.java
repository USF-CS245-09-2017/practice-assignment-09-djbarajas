import java.util.Arrays;

public class BinaryHeap {
    private int size;
    private int[] data;
    /*
    * BinaryHeap()
    * creates an array for the Heap of size 10 as specified in the
    * assignment and the amount of elements to 0
     */
    public BinaryHeap(){
        data = new int[10];
        size = 0;
    }
    /*
    *add()
    * if the new size is equal to the array length then grow the array
    * else, add the item to the end and swap the new child with the
    * parent when necessary.
     */
    public void add(int item){
        if(size+1 == data.length){
            grow();
        }
        data[size++] = item;
        int current=size-1;
        int parent = (current-1)/2;
        while(current != 0 && data[parent] > data[current]){
            swap(data,current,parent);
            current = parent;
            parent = (parent-1)/2;
        }
    }
    /*
    * swap()
    * swaps 2 elements in an array
     */
    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    /*
    * remove()
    * first, make the first element the last position and subtract from the size
    * if the size is grater than 0 then do the shift down method which checks if
    * the children are smaller than the parent and swaps when necessary
     */
    public int remove() {
        //Assert.not_false(size != 0);
        swap(data,size-1,0);
        --size;
        if(size > 0){
            shiftDown(0);
        }
        return data[size];
    }

    /*
    * shiftDown()
    * while the node is not a leaf node, compare the 2 children
    * to find the smallest and if the parent is smaller then return
    * but if the child is smaller then swap the child with the parent
    * and make the new position the child to go down a level
    */
    private void shiftDown(int pos) {
        int child;
        while(!(isLeaf(pos))){
            child = pos*2+1;
            if((child < size-1) && (data[child] > data[child+1])){
                child = child+1;
            }
            if(data[pos] <= data[child]) {
                return;
            }
            swap(data,pos,child);
            pos = child;
        }
    }

    /*
    * isLeaf()
    * if position is within those 2 numbers then it must be a leaf
    * node and therefore the shiftdown method can end
     */
    private boolean isLeaf(int pos){
        return ((pos >= (size)/2) && pos < size);
    }

    /*
    * grow()
    * doubles the size of the BinaryHeap
     */
    private void grow(){
        data = Arrays.copyOf(data,data.length*2);
    }
}
