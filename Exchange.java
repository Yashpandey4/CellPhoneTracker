public class Exchange
{
    public int data; // unique id of exchange, MobilePhone Objects will be linked to leaves of these structures :p
    public Exchange(int number)
    {
        this.data=number;
        children=new ExchangeList(this);
    }
    private ExchangeList children;
    private Exchange parent=null;
    private MobilePhoneSet setOfPhones=new MobilePhoneSet();
    public Exchange(int number, Exchange parent) // a newly initialised node, doesnt have children yet
    {
        this.data=number;
        children=new ExchangeList(this);
        this.parent=parent;
    }
    public ExchangeList getChildren()
    {
        return this.children;
    }
    public void setParent(Exchange parent)
    {
        //parent.addChild(this);
        parent.children.addEx(this);
        parent.setOfPhones.rset.Union(this.setOfPhones.rset);
        this.parent=parent;
    }
    public void addChild(int number)
    {
        Exchange child =new Exchange(number);
        child.setParent(this);
        this.children.addEx(child);
        this.setOfPhones.rset.Union(child.setOfPhones.rset);
    }
    public void addChild(Exchange child)
    {
        child.parent=this;
        this.children.addEx(child);
        //this.children.eList.addLast(child);
        this.setOfPhones.rset.Union(child.setOfPhones.rset);
    }
    public int getData()
    {
        return this.data;
    }
    public void setData(int data)
    {
        this.data=data;
    }
    public Boolean isRoot()
    {
        return (this.parent==null);
    }
    public void removeParent()
    {
        this.parent=null;
    }
    public Exchange parent() // getParent()
    {
        return (this.parent);
    }
    public int numChildren()
    {
        return this.children.eList.size;
    }
    public Exchange child(int i)
    {
        return this.children.eList.get(i);
    }
    public MobilePhoneSet residentSet()
    {
        if(this.numChildren()==0)
            return this.setOfPhones;
        else
        {
            MobilePhoneSet mps=new MobilePhoneSet();
            for(int i=0;i<this.numChildren();i++)
            {
                Exchange ex=this.child(i);
                

                mps.rset=mps.rset.Union(ex.residentSet().rset);
                // mps.rset.list.test();
            }
            return mps;
        }
    }
    public void addPhoneToExchange(MobilePhone mp)
    {
        setOfPhones.insert(mp);
        mp.location=this;
    }
    public void removePhoneFromExchange(MobilePhone mp)
    {
        setOfPhones.delete(mp);
        mp.location=null;
    }
}