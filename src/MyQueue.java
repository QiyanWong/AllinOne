import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
  Deque<Integer> in;
  Deque<Integer> out;
  int size;

  /** Initialize your data structure here. */
  public MyQueue() {
    in = new ArrayDeque<>();
    out = new ArrayDeque<>();
    size = 0;
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    in.offerLast(x);
    size++;
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    move();
    if(out.isEmpty()){
      return -1;
    }
    size--;
    return out.pollLast();

  }

  /** Get the front element. */
  public int peek() {
    move();
    if(out.isEmpty()){
      return -1;
    }
    return out.peekLast();
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return size == 0;
  }

  /** move all elements in 'in' stack to 'out' stack */
  private void move() {
    if(in.isEmpty()) return;
    while(!in.isEmpty()){
      out.offerLast(in.pollLast());
    }
    return;
  }
}