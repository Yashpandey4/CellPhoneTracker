public class ExchangeList //use this to implement array list in Exchange
{
    SinglyLinkedList<Exchange> eList=new SinglyLinkedList<>();
    Exchange parent;

    public ExchangeList(Exchange parent)
    {
        this.parent=parent;
    }

    
    public ExchangeList()
    {
        this.parent=null;
    }

    public void addEx(Exchange e)
    {
        //e.setParent(this.parent);
        eList.addLast(e);
    }

    // make this work
    // public void remEx(Exchange e)  
    // {
    //     Myset obj=new Myset();
    //     obj.list=this.eList;
    //     obj.Delete(e);
    //     this.eList=obj.list;
    // }
}