public class ArrayDeque<T> {

  private T[] elements;
  private int size;

  private int head;
  private int tail;

  public void addFirst(T item){
      if(size == elements.length) {
        expand();
      }
      if(size == 0){
        elements[head] = item;
      }else{
        head = head - 1 < 0? elements.length - 1: head-1 ;
        elements[head] = item;
      }
      size++;
  }


  public void addLast(T item){
    if(size == elements.length) {
      expand();
    }
    if(elements[tail] != null) {
      tail = (tail + 1)%elements.length;
    }
    elements[tail] = item;
    size++;
  }


  public boolean isEmpty(){
    return size == 0;
  }


  public int size(){
    return size;
  }


  public void printDeque(){
    for(int i = 0; i < size; i++){
      int index = (head + i)%elements.length;
      System.out.print(elements[index] + " ");
    }
    System.out.print("\n");
  }


  public T removeFirst(){
    if(size <= elements.length/ 4) {
      shrink();
    }

    T value = elements[head];
    elements[head] = null;
    head = (head + 1)%elements.length;
    size--;
    return value;
  }


  public T removeLast(){
    if(size <= elements.length/4) {
      shrink();
    }

    T value = elements[tail];
    elements[tail] = null;
    tail = (tail - 1) % elements.length;
    size--;
    return value;
  }


  public T get(int index) {
    if(index >= 0  && index < size && size > 0){
      int idx = (head + index)%elements.length;
      return elements[idx];
    }
    return null;
  }

  private void expand(){
    int originalSize = elements.length;
    int newSize = elements.length * 2;
    T[] newElements = (T[]) new Object[newSize];
    for(int i = 0; i < size; i++){
      newElements[i] = elements[(head + i)%originalSize];
    }
    elements = newElements;
    head = 0;
    tail = head + size - 1;
  }

  private void shrink(){
    int originalSize = elements.length;
    int newSize = elements.length / 2;
    T[] newElements = (T[]) new Object[newSize];
    for(int i = 0; i < size; i++){
      newElements[i] = elements[(head + i)%originalSize];
    }
    elements = newElements;
    head = 0;
    tail = head + size - 1;
  }

  public ArrayDeque(){
    elements = (T[]) new Object[8];
    head = 0;
    tail = 0;
    size =0;
  }
}
