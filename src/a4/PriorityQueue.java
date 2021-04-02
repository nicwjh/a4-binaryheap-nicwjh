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
            if (heap[i].getPriority() > heap[parentIndex].getPriority()){
                enqueue_swap(heap[i], heap[parentIndex]);
                i = parentIndex;
            }
            else{
                heapStructure = true;
            }
        }
    }
    private void enqueue_swap(Prioritized val1, Prioritized val2){
        Prioritized temp = val1;
        val1 = val2;
        val2 = temp;
    }

    @Override
    public double dequeue() {
        return 0.0;
    }

    @Override
    public double front() {
        return 0.0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public double[] sort() {
        return new double[0];
    }

    @Override
    public void build(Prioritized[] elements) { }


    // do not change
    public Prioritized[] getHeap() {
        return heap;
    }
}
