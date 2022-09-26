import java.util.*;
public class DoubleLinkedList<E> extends AbstractSequentialList<E> implements List<E>
{  // Data fields
    private Node<E> head;   // points to the head of the list
    private Node<E> tail;   //points to the tail of the list
    private int size = 0;    // the number of items in the list

    //default constructor
    public DoubleLinkedList() {
        this.setAll(null, null, 0);
    }


    @Override
    public String toString() {

        //toString method to display the linked list
        ListIterator iter = listIterator();
        String other = "[";

        while(iter.hasNext()) {

            other += iter.next();
            if(iter.hasNext()) {
                other += ", ";
            }
        }
        return other + "]";
    }

    @Override
    public boolean equals(Object o) {

        DoubleLinkedList other = (DoubleLinkedList) o;

            if(this.toString() == (other.toString())) {
                return true;
        }
            else {
                return false;
            }
    }

    @Override
    public boolean contains(Object o) {

        if(this.size == 0) {
            return false;
        }
        //iterate through the list to find if an Object with the value specified exists in the list using equals
       ListIterator iter = listIterator();

       while(iter.hasNext()) {

           if(iter.next().equals(o)) {
               return true;
           }
       }
       return false;
    }

    @Override
    public boolean add(E e) {

        listIterator(this.size).add(e);
        return true;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void setAll(Node<E> head, Node<E> tail, int size) {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    @Override
    public void add(int index, E obj)
    {
        listIterator(index).add(obj);
    }

    @Override
    public int indexOf(Object o) {

        ListIterator iter = listIterator();
        int index = 0;

        //iterate through the list from index 0 and find the object by its value and return its index
        while(iter.hasNext()) {

            Object other = iter.next();
            if(other.equals(o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        int index = -1, count = 0;

        //if list is not empty then iterate through the list from index 0 and find the object by its value and return its last index
        if(this.size != 0) {
            ListIterator iter = listIterator(0);
            while (iter.hasNext()) {
                Object other = iter.next();
                if (other.equals(o)) {
                    index = count;
                }
                count++;
            }
        }
      return index;
    }


    public void addFirst(E obj) {
        head = new Node<>(obj);
        size++;

    }
    public void addLast(E obj) {
        tail = new Node<>(obj);
        size++;
    }

    @Override
    public E get(int index)
    {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<E> iter = listIterator(index);
        return iter.next();
    }
    public E getFirst() { return head.data;  }
    public E getLast() { return tail.data;  }

    @Override
    public int size() {
        //returns size of the list
        return this.size;
    }

    @Override
    public E remove(int index)
    {     E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext())
        {   returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
    }

    public boolean remove(Object o) {
        //iterating through the list to find the object by its value and remove it using iteraor remove method
        ListIterator<E> iter = listIterator(0);
        while(iter.hasNext()) {
            Object other = iter.next();
            if(other.equals(o)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        //error checking if the index is in the range
        if(size == 0 || index >= size || element == null) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<E> iter = listIterator(index);
        E setValue = null;
        setValue = iter.next();
        iter.set(element);
        return setValue;
    }

    @Override
    public Iterator<E> iterator() {

        return new ListIter(0);

    }

    @Override
    public ListIterator<E> listIterator() {

        return new ListIter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index){

        return new ListIter(index);
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    // Inner Classes
    private static class Node<E>
    {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        public Node() {
            this.next = null;
            this.prev = null;
            this.data = null;
        }
        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
    }  // end class Node

    public class ListIter implements ListIterator<E>
    {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   //


        public ListIter(int i)  // constructor for ListIter class
        {   if (i < 0 || i > size)
        {
            throw new IndexOutOfBoundsException();
        }
            lastItemReturned = null;

            if (i == size)     // Special case of last item
            {     index = size;     nextItem = null;      }
            else          // start at the beginning
            {   nextItem = head;
                for (index = 0; index < i; index++)  nextItem = nextItem.next;
            }// end else
        }  // end constructor

        public ListIter(ListIter other)
        {   nextItem = other.nextItem;
            index = other.index;    }

        public boolean hasNext() {

            return nextItem != null;    }

        public boolean hasPrevious()
        {
            return index > 0;
        }

        public int previousIndex() {

            return index-1;    } // Fill Here
        public int nextIndex() {

            return index;    } // Fill here

        @Override
        public void set(E e) {
            //error checking if the lastItemReturned is null therefore it can't be set
            if (lastItemReturned == null)
            {
                throw new IllegalStateException();
            }
            //if no error then change or replace the data in the lastItemReturned with the new data provided and then remove the lastItemReturned
            lastItemReturned.data = e;
            lastItemReturned = null;
        }
        @Override
        public void remove(){

          //error checking if the list is empty and the last item returned is null
          if(size == 0 || lastItemReturned == null) {
                throw new IllegalStateException();
          }
          else {
              //if there is only one node in the list then just remove it
              if(size == 1) {
                  head = null;
              }
              //if we like to remove the fist node in the list
              else if(lastItemReturned.prev == null) {
                  head = nextItem;
                  nextItem.prev = null;
              }
              //if we like to remove the last node in the list
              else if(lastItemReturned.next == null) {
                  lastItemReturned.prev.next = null;
              }
              //if we like to remove a node in the middle of the list
              else {
                  lastItemReturned.prev.next = lastItemReturned.next;
                  lastItemReturned.next.prev = lastItemReturned.prev;
              }
              //decrease the size and set the lastItemRetuned to null
              size--;
              lastItemReturned = null;
          }
        }      //  implemented

        public E next()
        {
            //error checking if an element exists
            if(!(hasNext())) {
                throw new NoSuchElementException();
            }
            //set the lastItemReturned to the nextItem and increase the index to move forward in the list
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        public E previous()
        {
            if(!(hasPrevious())) {
                throw new NoSuchElementException();
            }
            if(nextItem == null) {
                nextItem = tail;
            }
            else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        public void add(E obj) {

            //if adding to an empty list
            if(head == null) {
                head = new Node<>(obj);
                tail = head;
            }
            //if adding to the beginning of the list at index 0
            else if(nextItem == head) {
                Node<E> newNode = new Node<>(obj);
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            }
            //if adding at the tail of the list if index is size - 1
            else if(nextItem == null) {
                Node<E> newNode = new Node<>(obj);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            //if none of the above are true then we are adding in the middle of the list
            else {
                Node<E> newNode = new Node<>(obj);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            //increasing the size of the list and the index after adding the new node
            size++;
            index++;
            lastItemReturned = null;
        } // End of add method
    }// end of inner class ListIter
}// end of class DoubleLinkedList
