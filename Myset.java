public class Myset // change everywhere to Myset<E>
{
    SinglyLinkedList<Object> list=new SinglyLinkedList<>();

    public Boolean IsEmpty()
    {
        return this.list.isEmpty();
    }

    public Boolean IsMember(Object o)
    {
        if(this.IsEmpty()) 
            return false;
        SinglyLinkedList<Object>.Node<Object> n=this.list.head;
        int k=0;
        while(n!=null)
        {
            if((n.element).equals(o))
            {
                k=1; break;
            }
            n=n.next;
        }
        if(k==1)
            return true;
        return false;
    }

    public void Insert(Object o)
    {
        if(this.IsMember(o))
            return;
        this.list.addLast(o);
    }

    static class MyException extends Exception{}

    public void Delete(Object o)
    {
        if(!(this.IsMember(o)))
        {
            try
            {
                throw new MyException();
            }
            catch(MyException ex) 
            {
                //System.out.println("Error - No mobile phone with identifier "+o.number()+" found in the network");
                System.out.println("Error - No mobile phone with the given identifier found in the network");
            }
        }

        SinglyLinkedList<Object>.Node<Object> temp=this.list.head, prev=null;
        if(temp!=null && (temp.element).equals(o))
        {
            this.list.head=temp.next;
            this.list.size--;
            if(this.list.size==0)
                this.list.tail=null;
            return;
        }
        while(temp!=null && !((temp.element).equals(o)))
        {
            prev=temp;
            temp=temp.next;
        }
        prev.next=temp.next;
        if(temp.next==null)
            this.list.tail=prev;
        this.list.size--;
        if(this.IsMember(o))
            this.Delete(o);
    }

    public Myset Union(Myset a)
    {
        Myset unset=new Myset();
        SinglyLinkedList<Object>.Node<Object> ptr=this.list.head;
        while(ptr!=null)
        {
            // ptr2.element=ptr1.element;
            // if(ptr1.next=null) 
            //     unset.list.tail=ptr2;
            // ptr1=ptr1.next;
            // ptr2=ptr2.next;
            (unset.list).addLast(ptr.element);
            ptr=ptr.next;
        }
        ptr=a.list.head;
        while(ptr!=null)
        {
            if(!(unset.IsMember(ptr.element)))
                (unset.list).addLast(ptr.element);
            ptr=ptr.next;
        }
        return unset;
    }

    public Myset Intersection(Myset a)
    {
        Myset inset=new Myset();
        SinglyLinkedList<Object>.Node<Object> ptr=this.list.head;
        while(ptr!=null)
        {
            if(a.IsMember(ptr.element))
                (inset.list).addLast(ptr.element);
            ptr=ptr.next;
        }
        return inset;
    }


    public void tester()
    {
        SinglyLinkedList<Object>.Node<Object> ptr=this.list.head;
        while(ptr!=null)
        {
            System.out.print(ptr.element+" ");
            ptr=ptr.next;
        }
        System.out.println();
    }
}

class SinglyLinkedList<E>
    {
        
        class Node<E>
        {
            public E element;
            public Node<E> next;
            public Node(E e,Node<E> n)
            {
                element=e;
                next=n;
            }
            // public E getElement()
            // {
            //     return element;
            // }
            // public Node<E> getNext()
            // {
            //     return next;
            // }
            // public void setNext(Node<E> n)
            // {
            //     next=n;
            // }
        }
        
        public Node<E> head=null;
        public Node<E> tail=null;
        public int size=0;
        public SinglyLinkedList() {}
        // public int size()
        // {
        //     return size;
        // }
        public boolean isEmpty()
        {
            return size==0;
        }
        // public E first()
        // {
        //     if(isEmpty()) return null;
        //     return head.getElement();
        // }
        // public E last()
        // {
        //     if(isEmpty()) return null;
        //     return tail.getElement();
        // }
        public void addFirst(E e)
        {
            head=new Node<>(e,head);
            if(size==0)
                tail=head;
            size++;
        }
        public void addLast(E e)
        {
            Node<E> newest =new Node<>(e,null);
            if(isEmpty())
                head=newest;
            else
                tail.next=newest;
            tail=newest;
            size++;
        }
        public E removeFirst()
        {
            if(isEmpty())
                return null;
            E answer=head.element;
            head=head.next;
            size--;
            if(size==0)
                tail=null;
            return answer;
        }

        public E get(int n) // n is 0 to size-1
        {
            if(isEmpty() || n<0 || n>=size)
                return null;
            Node<E> ptr=head;
            for(int i=0; i<size;i++)
            {
                if(i==n)
                    break;
                ptr=ptr.next;
            }
            return ptr.element;
        }

        // public void test()
        // {
        //     Node<E> ptr=head;
        //     for(int i=0; i<size;i++)
        //     {
        //         System.out.println(ptr.element+" ");
        //         ptr=ptr.next;
               
        //     }
        // }
    
    }