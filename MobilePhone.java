public class MobilePhone
{
    private int uid;
    private Boolean status=false;
    public Exchange location;

    public MobilePhone(int number)
    {
        this.uid=number;
    }

    public MobilePhone(int number, Exchange e)
    {
        this.uid=number;
        // this.status=true;
        this.location=e;

    }

    public int number()
    {
        return this.uid;
    }

    public Boolean status()
    {
        return this.status;
    }

    public void switchOn()
    {
        this.status=true;
    }

    public void switchOff()
    {
        this.status=false;
    }

    public Exchange location()
    {
        if(!(this.status))
        {
            try
            {
                throw (new Myset.MyException());
            }
            catch(Myset.MyException ex) 
            {
                System.out.println("Error - Mobile phone with identifier "+this.uid+" is currently switched off");
            }
        }
        return this.location;
    }
}

