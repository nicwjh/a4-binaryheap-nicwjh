package a4;


public class PriorityQueue implements  Queue{

    private Prioritized[] heap;
    private int size;
    private static final int arraySize = 10000; // Everything in the array will initially
    // be null. This is ok! Just build out
    // from array[1]. Don't change the value of this variable.

    public PriorityQueue() {
        heap = new Prioritized[arraySize];
        size = 0;
        heap[0] = new Prioritized(Double.NaN, Double.NaN); //0th will be unused for simplicity
                                                            //of child/parent computations
    }

    // fill in the methods below based on the descriptions in the Queue interface. Do NOT change the interface or any
    // of the method signatures
    @Override
    public void enqueue(double value, double priority) {
        int i = 1;
        boolean heapStructure = false;
        Prioritized newObject = new Prioritized(value, priority);
        while (heap[i] != null){
            i++;
        }
        heap[i] = newObject;

        while (!heapStructure){
            int parentIndex = i/2;
            if (heap[i].getPriority() > heap[parentIndex].getPriority() && parentIndex != 0){
                swap(i, parentIndex);
                i = parentIndex;
            }
            else if (heap[i].getPriority() < heap[parentIndex].getPriority() || parentIndex == 0){
                heapStructure = true;
            }
        }
        size++;
    }
    private void swap(int val1, int val2){
        Prioritized temp = heap[val1];
        heap[val1] = heap[val2];
        heap[val2] = temp;
    }

    @Override
    public double dequeue() {
        double value = heap[1].getValue();
        int i = 1;
        boolean heapStructure = false;

        while (heap[i + 1] != null){
            i++;
        }
        heap[1] = heap[i];
        heap[i] = null;
        i = 1;

        while (!heapStructure){
            int leftIndex = 2*i;
            int rightIndex = 2*i + 1;

            if (heap[leftIndex] != null && heap[rightIndex] != null){
                if (heap[i].getPriority() > heap[leftIndex].getPriority() && heap[i].getPriority() > heap[rightIndex].getPriority()){
                    heapStructure = true;
                }
                else {
                    int swapIndex = heap[leftIndex].getPriority() > heap[rightIndex].getPriority() ? leftIndex : rightIndex;
                    swap(i, swapIndex);
                    i = swapIndex;
                }
            }
            else if (heap[leftIndex] != null){
                if (heap[i].getPriority() > heap[leftIndex].getPriority()){
                    heapStructure = true;
                }
                else{
                    swap(i, leftIndex);
                    i = leftIndex;
                }
            }
            else{
                heapStructure = true;
            }
        }
        size--;
        return value;
    }

    @Override
    public double front() {
        return heap[1].getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return (heap[1] == null);
    }

    @Override
    public double[] sort() {
        boolean[] flag = new boolean[size + 1];
        double[] sortedValues = new double[size];

        for (int i = 0; i < size; i++) {
            sortedValues[i] = getMin(flag);
        }
        return sortedValues;
    }
    private double getMin(boolean[] flag){
        int minIndex = 1;
        boolean swapFlag = false;

        while (flag[minIndex]){
            minIndex++;
        }
        for (int j = 1; j <= size; j++){
            if (!flag[j] && heap[j].getPriority() < heap[minIndex].getPriority()){
                minIndex = j;
                swapFlag = true;
            }
        }
        if (swapFlag) {
            flag[minIndex] = true;
        }
        return heap[minIndex].getValue();
    }

    @Override
    public void build(Prioritized[] elements) { }


    // do not change
    public Prioritized[] getHeap() {
        return heap;
    }
}
