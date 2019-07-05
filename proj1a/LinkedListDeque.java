/*
 * generic version of LinkedListDeque
 * @param<T> the type of the value being boxed
 */
public class LinkedListDeque<T> {
  // an inner class that represents a list node
  private class ListNode {
    T value;
    ListNode prev;
    ListNode next;

    ListNode(T value) {
      this.value = value;
    }
  }

  // a sentinel that represents both head and tail
  private ListNode head;
  private ListNode tail;
  private int size;

  public void addFirst(T item) {
    ListNode newNode = new ListNode(item);
    ListNode next = head.next;
    newNode.next = next;
    next.prev = newNode;
    newNode.prev = head;
    head.next = newNode;
    size++;
  }


  public void addLast(T item) {
    ListNode newNode = new ListNode(item);
    ListNode prev = tail.prev;
    newNode.prev = prev;
    tail.prev = newNode;
    prev.next = newNode;
    newNode.next = tail;
    size++;
  }


  public boolean isEmpty() {
    return size == 0;
  }


  public int size() {
    return size;
  }


  public void printDeque() {
    if (size > 0) {
      ListNode cur = head.next;
      while (cur != tail) {
        System.out.print(cur.value + " ");
        cur = cur.next;
      }
      System.out.print("\n");
    }
  }


  public T removeFirst() {
    if (size > 0) {
      T value = head.next.value;

      ListNode nextNext = head.next.next;
      nextNext.prev = head;
      head.next = nextNext;
      size--;

      return value;
    }
    return null;
  }


  public T removeLast() {
    if (size > 0) {
      T value = tail.prev.value;

      ListNode prevPrev = tail.prev.prev;
      prevPrev.next = tail;
      tail.prev = prevPrev;
      size--;

      return value;
    }
    return null;
  }


  public T get(int index) {

    if (index < size && index >= 0 && size > 0) {
      ListNode cur = head.next;
      for (int i = 0; i < index; i++) {
        cur = cur.next;
      }
      return cur.value;
    }
    return null;
  }


  public LinkedListDeque() {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    head.prev = null;
    tail.next = null;
    tail.prev = head;

    size = 0;
  }

  public T getRecursive(int index) {
    if (size == 0) {
      return null;
    }
    return getRecursiveHelper(index, head.next);
  }

  private T getRecursiveHelper(int index, ListNode node) {
    if (index < 0 || index >= size) {
      return null;
    }
    if (index == 0) {
      return node.value;
    }
    return getRecursiveHelper(--index, node.next);
  }
}
