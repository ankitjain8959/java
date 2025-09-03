# Floyd's Cycle-Finding Algorithm (Tortoise and Hare Algorithm)
- This algorithm is used to detect cycles in a sequence of values, such as in linked lists or arrays.
- The Tortoise and Hare algorithm, also known as Floyd's Cycle-Finding Algorithm, uses two pointers,  
  - a slow one (tortoise) moving one step at a time
  - a fast one (hare) moving two steps  
to detect a cycle. If a cycle exists, the hare will eventually catch up to the tortoise.  

This algorithm concept can also be applied to arrays where index acts as node and value at that index acts as a pointer to the next node.  

## Complexity
- Time Complexity: O(n)
- Space Complexity: O(1)

## Implementation in Java
1. Initializes two pointers, tortoise and hare at the start of the array.
2. Detect a cycle: Move the tortoise by one step and the hare by two steps until they meet i.e. tortoise == hare.
3. Finds the starting point of the cycle: Reset one pointer to the start of the array and move both pointers one step at a time until they meet again. The meeting point is the start of the cycle.  

```java
public class FloydCycleFinding {
    public static int findDuplicate(int[] nums) {
        // Step 1: Initialize the tortoise and hare
        int tortoise = nums[0];
        int hare = nums[0];

        // Step 2: Move tortoise by 1 step and hare by 2 steps until they meet
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Step 3: Find the entrance to the cycle
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return tortoise; // or hare, both are at the start of the cycle
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println("The duplicate number is: " + findDuplicate(nums)); // Output: 2
    }
}
```