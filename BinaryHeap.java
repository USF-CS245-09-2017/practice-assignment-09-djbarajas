import java.util.Arrays;

public class BinaryHeap {
    private int size;
    private int[] data;

    public BinaryHeap(){
        data = new int[10];
        size = 0;
    }

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

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public int remove(){
        Assert.not_false(size != 0);
        swap(data,size-1,0);
        --size;
        if(size > 0){
            shiftDown(0);
        }
        return data[size];
    }

    private void shiftDown(int pos) {
        int child;
        while(!(isLeaf(pos))){
            child = pos*2;
            if((child < size) && (data[child] > data[child+1])){
                child = child+1;
            }
            if(data[pos] <= data[child]) {
                return;
            }
            swap(data,pos,child);
            pos = child;
        }
    }

    private boolean isLeaf(int pos){
        return ((pos > size/2)&& pos <= size);
    }


    private void grow(){
        data = Arrays.copyOf(data,data.length*2);
    }
}
