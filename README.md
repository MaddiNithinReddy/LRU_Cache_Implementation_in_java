# LRU_Cache_Implementation_in_java

This project implements an LRU (Least Recently Used) Cache in Java using a combination of HashMap and Circular Doubly Linked List. The LRU cache efficiently supports O(1) time complexity for both get and put operations. In LRU cache, when the capacity is full, the least recently used entry is removed.

## 1. What is caching? Need?

### Caching

- Caching is a process used to store frequently accessed data temporarily in a high-speed storage structure (CPU cache).
- CPU cache is a small, high-speed memory located close to the processor.

### Caching Needs

- Speed up the data retrieval (faster access) & improve performance.
- Minimizes redundant computations.
- Optimizes the overall system's efficiency.

## 2. Understanding Memory in computer organization: Primary, Secondary & Cache
### Primary Memory (Volatile Memory)

- **RAM (Random Access Memory)**: Temporary memory where programs and data are stored for quick access.
- **Cache Memory**: Super-fast memory for storing frequently accessed data.
- **Registers**: Ultra-fast storage inside the CPU for immediate operations.

**Example**:
- RAM is like your computer’s workspace where all active tasks happen. If you turn off the power, everything in RAM is lost!

### Secondary Memory (Non-Volatile Memory)

- **HDD (Hard Disk Drive)**: Large storage but slow access speed.
- **SSD (Solid State Drive)**: Faster than HDD but still slower than RAM.
- **Flash Drives / External Storage**: Portable storage for backups and file transfer.

**Example**:
- Your hard disk stores all your files and software permanently. Even after shutting down, your data remains.

### Cache Memory

**Example**:
- Let's take an example that you went to a library looking for a book.
  - **Option 1**: You go to the basement, search through thousands of books, and finally find the one you need. (Slow!)
  - **Option 2**: The librarian already kept the most popular books on a small shelf near the entrance, so you grab it instantly. (Fast!)

**Explanation**:
- That small shelf is like Cache Memory!

Cache memory is a small, super-fast storage located close to the CPU. It stores the most frequently used data so that the processor can access it quickly, instead of fetching it from slower RAM or hard drives.

### Why Do We Need Cache Memory?

- Faster processing: Reduces the time the CPU waits for data.
- Improves performance: Makes your computer run smoother.
- Reduces RAM access: Since frequently used data is already in cache.


## 3. In-memory cache: Redis and Memcache introduction

**Explanation**:
- Imagine you own a library, and people frequently ask for the same books. Instead of searching for the book on shelves every time, you keep the most popular books on your desk for quick access.

**Definition**:
- This is exactly what an in-memory cache does!

In-memory caching is a type of caching technique where frequently accessed data is stored in RAM instead of disk-based storage, hence making it fast.

### Redis

- Redis is like a smart bookshelf that not only stores books but also organizes them in different formats. It can store:
  - Simple key-value pairs ("name" → "Alice")
  - Lists ("queue" → [Task1, Task2, Task3])
  - Hashes ("user:1" → {name: "John", age: 30})
  - Sets ("fruits" → {"apple", "banana"})
  - Sorted Sets ("leaderboard" → {("Alice", 100), ("Bob", 90)})

**Bonus**:
- Redis can also store data permanently (if needed), making it work as both a cache and a database.

### Memcached

- Memcached is like a simple sticky note system where you can quickly write down and look up notes, but you can't organize them in different ways. It only supports:
  - Key-Value Pairs ("user:123" → "John Doe")
  - Fast, temporary storage (Data disappears when Memcached restarts)
  - Simple caching (No complex data structures like Redis)

**Prioritizes**:
- Speed and simplicity over advanced features. It keeps things fast by:
  - Avoiding complex queries.
  - Not maintaining relationships between keys.


## 4. Different cache replacement strategies

**Analogy**:
- Imagine you have a small fridge that can store only 5 bottles of water. Every time you bring a new bottle, you must remove an old one to make space. How do you decide which bottle to remove?

Cache replacement strategies work the same way—when the cache is full, they decide which data to remove to make space for new data.

### Strategies

1. **Least Recently Used (LRU)** - "Forget the Oldest Used Item"
   - Think of it like this: If you haven’t used a bottle of water in a long time, you remove it first.
   - In caching: The least recently used data is removed first.
   - Good for: Keeping frequently accessed data.
   - Example: LRU Cache (Used in memory caches and databases).

2. **First In First Out (FIFO)** - "Remove the Oldest Added Item"
   - Think of it like this: The first bottle you put in the fridge is the first one you take out.
   - In caching: The oldest stored data is removed first, even if it's still needed.
   - Good for: Simple and predictable cache management.
   - Example: Queues in Operating Systems.

3. **Least Frequently Used (LFU)** - "Remove the Least Popular Item"
   - Think of it like this: If one bottle is used every day and another hasn't been touched for weeks, remove the unused one.
   - In caching: The data accessed the least number of times is removed first.
   - Good for: Prioritizing frequently used data.
   - Example: Web caching, database caching.

4. **Random Replacement** - "Pick Any Item to Remove"
   - Think of it like this: You randomly pick any bottle to remove, without thinking about which one is older or frequently used.
   - In caching: Any random data item is removed.
   - Good for: When access patterns are unpredictable.
   - Example: Some hardware caches use this to keep things simple.


## 5. How Primary and Secondary Memory Communicate in the LRU Cache

**Data Flow in an LRU Cache System**:
- Cache Memory <-- Primary Memory (RAM) <-- Secondary Storage (Disk/Database)

When an application (like a website or a software program) requests data, the system follows these steps:

1. **Check LRU Cache (Super-fast Cache Memory)**
   - If the data is found in the cache, it is returned instantly (O(1) access time).
   - If not found, go to the next step.

2. **Fetch from RAM (Primary Memory)**
   - If the data is stored in RAM, it is retrieved quickly and also added to the cache for future use.
   - If not found in RAM, go to the next step.

3. **Retrieve from Hard Disk or Database (Secondary Memory)**
   - The data is retrieved from slow storage (HDD/SSD or external database).
   - It is then stored in RAM for quick future access.

The LRU cache updates its memory with the new data.

**Why Use an LRU Cache?**
- Since cache has limited space, it uses the Least Recently Used (LRU) Cache strategy to remove old or less-used data and make space for new data.
- This ensures that frequently accessed data stays available without repeatedly going back to slow storage.
- LRU Cache ensures you don’t waste time searching in slow storage!


## 6. Designing LRU cache using doubly linked list and hash map

An LRU (Least Recently Used) Cache is a data structure that stores a limited number of items and removes the least recently used one when it's full.

### Considerations

- **Array or ArrayList**: Using an array or ArrayList leads to O(n) for removal or addition operations because we need to shift the items.
- **Singly Linked List (SLL)**: Using SLL overcomes the drawback of array or ArrayList but getting the previous pointer is difficult.
- **Doubly Linked List (DLL)**: To directly access the previous and next pointers, we use the Doubly Linked List (DLL).

I have implemented LRU Cache using the Circular Doubly Linked List (CDLL).

### Implementation

To efficiently implement an LRU Cache, use:
- **HashMap** (O(1) Lookup Time): To store key-node pairs.
- **Circular Doubly Linked List (CDLL)** (O(1) Insert/Delete): To maintain access order.

**How It Works**:
1. HashMap stores key-node pairs for quick access.
2. CDLL maintains the order of access:
   - Most Recently Used (MRU) node is at the front (next to head).
   - Least Recently Used (LRU) node is at the back (before head).

3. **On "Get" Operation**:
   - If the key is found, move the node to the front (MRU).
   - Else, return -1.

4. **On "Put" Operation**:
   - If the key exists, update its value and move it to the front.
   - If full, remove the LRU node (just before the head) and insert the new node at the front.

## Walkthrough with Example

**Initialize LRU Cache with Capacity 3**

- The cache is empty at the beginning.

**Insert (1, 10) → Cache: (1,10)**

- Since the cache is empty, (1,10) is inserted.

**Insert (2, 20) → Cache: (2,20) → (1,10)**

- The cache is not full, so (2,20) is inserted at the front.

**Insert (3, 30) → Cache: (3,30) → (2,20) → (1,10)**

- The cache is still not full, so (3,30) is inserted at the front.

**Access Key 1 (GET 1)**

- Key 1 is found with value 10, and it is moved to the front.
- Cache after access: (1,10) → (3,30) → (2,20)

**Insert (4, 40) → Evicts LRU Key (2)**

- The cache is full, so the least recently used key (2) is evicted.
- Cache after insertion: (4,40) → (1,10) → (3,30)

**Access Key 2 (GET 2)**

- Since key 2 was evicted, it is not found, and -1 is returned.
