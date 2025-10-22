class MedianFinder {
    
   
    // Max-heap (stores the smaller half of numbers, largest at top)
    PriorityQueue<Integer> leftMaxheap = new PriorityQueue<>(Collections.reverseOrder());
    // Min-heap (stores the larger half of numbers, smallest at top)
    PriorityQueue<Integer> rightMinheap = new PriorityQueue<>();

    public MedianFinder() {}

    // Add a number into the data structure
    public void addNum(int num) {
        // Step 1: Decide where to put the number
        // If num is smaller than or equal to max of left half → put in leftMaxheap
        // Otherwise put in rightMinheap
        if (leftMaxheap.isEmpty() || num < leftMaxheap.peek()) {
            leftMaxheap.add(num);
        } else {
            rightMinheap.add(num);
        }

        // Step 2: Balance the heaps so that:
        //  - leftMaxheap has equal size to rightMinheap
        //  - OR leftMaxheap has just 1 more element
        if (leftMaxheap.size() < rightMinheap.size()) {
            // Move smallest from rightMinheap → leftMaxheap
            leftMaxheap.add(rightMinheap.poll());
        } else if (leftMaxheap.size() > rightMinheap.size() + 1) {
            // Move largest from leftMaxheap → rightMinheap
            rightMinheap.add(leftMaxheap.poll());
        }
    }

    // Find the median of all added numbers
    public double findMedian() {
        // Case 1: Both heaps are same size → average of two middle values
        if (leftMaxheap.size() == rightMinheap.size()) {
            return (leftMaxheap.peek() + rightMinheap.peek()) / 2.0;
        }
        // Case 2: Odd number of elements → leftMaxheap will have 1 extra → its top is median
        return leftMaxheap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */