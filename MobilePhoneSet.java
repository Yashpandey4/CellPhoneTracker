public class MobilePhoneSet
{
    public Myset rset=new Myset();
    public Exchange e;
    public MobilePhoneSet(Exchange ex)
    {
        this.e=ex;
    }
    public MobilePhoneSet() {}
    public void insert(MobilePhone p)
    {
        rset.Insert(p);
    }
    public void delete(MobilePhone p)
    {
        rset.Delete(p);
    }
    public MobilePhone getNodeInLocation(int n)
	{
        return (MobilePhone)this.rset.list.get(n);
    }

    public MobilePhone getNodeWithID(int n)
	{
		if(this.rset.list.isEmpty())
			return null;
		
        for(int i=0;i<this.rset.list.size;i++)
        {
            MobilePhone mp=(MobilePhone)this.rset.list.get(i);
            if(mp.number()==n && mp.status())
                return mp;
        }
		return null;
	}
    
}