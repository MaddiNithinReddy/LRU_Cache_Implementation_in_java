import java.util.*;

class Node {
    int key, value;
    Node prev, next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class CircularDoublyLinkedList {
    Node head;

    CircularDoublyLinkedList() {
        head = null;
    }

    Node insertAtFront(int key, int value) {
        Node newNode = new Node(key, value);
        newNode.next = newNode;
        newNode.prev = newNode;

        if (head == null) {
            head = newNode;
            return head;
        } else {
            Node lastNode = head.prev;
            newNode.next = head;
            head.prev = newNode;
            lastNode.next = newNode;
            newNode.prev = lastNode;
            head = newNode;
            return head;
        }
    }

    void printList() {
        if (head == null)
            return;

        System.out.print("  ( " + head.key + " , " + head.value + " )  ");
        Node temp = head.next;

        while (temp != head) {
            System.out.print("  ( " + temp.key + " , " + temp.value + " )  ");
            temp = temp.next;
        }
        System.out.println();
    }

    int removeLeastRecentlyUsed() {
        if (head == null)
            return -1;

        Node lastNode = head.prev;

        if (head.next == head) {
            head = null;
            return lastNode.key;
        }

        Node secondLastNode = head.prev.prev;
        secondLastNode.next = head;
        head.prev = secondLastNode;

        return lastNode.key;
    }

    void moveToFront(Node nodeToMove) {
        if (nodeToMove == null || nodeToMove == head) return; // Already at front

        // Remove node from its current position
        Node previousNode = nodeToMove.prev;
        Node nextNode = nodeToMove.next;
        previousNode.next = nextNode;
        nextNode.prev = previousNode;

        // Insert node at the front
        Node lastNode = head.prev;
        nodeToMove.next = head;
        head.prev = nodeToMove;
        lastNode.next = nodeToMove;
        nodeToMove.prev = lastNode;
        head = nodeToMove;
    }
}

class LRUCache {
    int capacity;
    int currentSize;
    CircularDoublyLinkedList cacheList;
    Map<Integer, Node> cacheMap;

    LRUCache(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.cacheList = new CircularDoublyLinkedList();
        this.cacheMap = new HashMap<>();
    }

    int get(int key) {
        if (!cacheMap.containsKey(key))
            return -1;

        Node node = cacheMap.get(key);
        int returnValue = node.value;
        cacheList.moveToFront(node);
        return returnValue;
    }

    void put(int key, int value) {
        if (cacheMap.containsKey(key)) { // Update existing key
            Node node = cacheMap.get(key);
            node.value = value;
            cacheList.moveToFront(node);
        } else {
            if (currentSize < capacity) {
                Node newNode = cacheList.insertAtFront(key, value);
                cacheMap.put(key, newNode);
                currentSize++;
            } else { // Evict least recently used entry
                int removedKey = cacheList.removeLeastRecentlyUsed();
                cacheMap.remove(removedKey);
                Node newNode = cacheList.insertAtFront(key, value);
                cacheMap.put(key, newNode);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter LRU Cache capacity: ");
        int capacity = sc.nextInt();
        LRUCache cache = new LRUCache(capacity);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1 - PUT (key, value)");
            System.out.println("2 - GET (key)");
            System.out.println("3 - EXIT");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                System.out.print("Enter value: ");
                int value = sc.nextInt();
                cache.put(key, value);
                System.out.println("Inserted (" + key + ", " + value + ") into cache.");
            } else if (choice == 2) {
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                int result = cache.get(key);
                System.out.println("Value for key " + key + ": " + result);
            } else if (choice == 3) {
                System.out.println("Exiting..... \n Thank You");
                break;
            } else {
                System.out.println("Invalid choice, try again.");
            }

            // Display cache state after each operation
            displayCache(cache);
        }

        sc.close();
    }

    static void displayCache(LRUCache cache) {
        System.out.print("Current Cache: ");
        cache.cacheList.printList();
    }
}
