
public class RoutingMapTree
{
	Exchange root;
	public RoutingMapTree() 
	{
		this.root=new Exchange(0);
	}

	public RoutingMapTree(Exchange e)
	{
		this.root=e;
	}

	public boolean isEmpty()
	{
		return (this.root==null);
	}

	public Boolean containsNode(Exchange e)
	{
		if(this.isEmpty())
			return false;
		else if(this.root.getData()==e.getData())
			return true;
		else if(this.root.numChildren()==0)
			return false;
		else
		{
			for(int i=0;i<this.root.numChildren();i++)
			{
				RoutingMapTree t=new RoutingMapTree(this.root.child(i));
				if(t.containsNode(e))
					return true;
			}
		}
		return false;
	}

	public void switchOn(MobilePhone a,Exchange b)
	{
		if(!(a.status()))
		{
			a.switchOn();
			b.addPhoneToExchange(a);
		}
		else
		{
			System.out.println("Phone with identifier "+ a.number()+" was already on and registered with "+a.location.data);
		}
	}

	public void switchOff(MobilePhone a)
	{
		if(a.status())
		{
			a.switchOff();
			Exchange b=a.location();
			b.removePhoneFromExchange(a);
		}
		else
		{
			System.out.println("Phone with identifier "+ a.number()+" was already off.");       //what if phone not in network at all?
		}
	}

	public Exchange getNodeWithID(int n)
	{
		if(this.isEmpty())
			return null;
		else if(this.root.getData()==n)
			return root;
		else if(this.root.numChildren()==0)
			return null;
		else
		{
			for(int i=0;i<this.root.numChildren();i++)
			{
				RoutingMapTree t=new RoutingMapTree(this.root.child(i));
				Exchange e=t.getNodeWithID(n);
				if(e!=null)
					return e;
			}
		}
		return null;
	}

	//*********** following are methods as given in input file***************

	public void addExchange(int a, int b) //IA - adding b already in a
	{
		Exchange exch=new Exchange(a);
		if(this.containsNode(exch))
		{
			// if(!exch.getChildren().eList.IsMember(new Exchange(b,exch)))
			// {
				Exchange child=new Exchange(b,exch);
				child.setParent(exch);
				this.getNodeWithID(a).getChildren().eList.addLast(child);
			// }
			// else
			// 	System.out.println("addExchange "+a+" "+b+": Error - Exchange with identifier "+b+" already present in "+a);
		}
		else
		{
			try
			{
				throw new Myset.MyException();
			}
			catch(Myset.MyException ex) 
			{
				System.out.println("addExchange "+a+" "+b+": Error - No Exchange with identifier "+exch.getData()+" found in the network");
			}
		}
	}

	public void switchOnMobile(int a,int b) //mobile a, exchange b
	{
		if(this.containsNode(new Exchange(b)))
		{
			if(this.getNodeWithID(b).getChildren().eList.isEmpty())
			{
				if(this.root.residentSet().rset.IsMember(new MobilePhone(a)))
				{
					try
					{
						throw new Myset.MyException();
					}
					catch(Myset.MyException ex) 
					{
						System.out.println("switchOnMobile "+a+" "+b+": Error - Phone with identifier "+a+" already registered in the network.");
					}
				}
				else
				{
					Exchange ex=this.getNodeWithID(b);
					this.switchOn(new MobilePhone(a,ex), ex);
				}
			}
			else // non empty children of exchange node in subtree
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					System.out.println("switchOnMobile "+a+" "+b+": Error - Exchange with identifier "+b+" is not a base level Leaf node exchange.");
				}
			}

		}
		else
		{
			try
			{
				throw new Myset.MyException();
			}
			catch(Myset.MyException ex) 
			{
				System.out.println("switchOnMobile "+a+" "+b+": Error - Exchange with identifier "+b+" does not exist.");
			}
		}
	}

	public void switchOffMobile(int a)
	{
		// if(this.root.residentSet().rset.IsMember(new MobilePhone(a)))
		// {
		// 	MobilePhone mp=this.root.residentSet().getNodeWithID(a);
		// 	mp.switchOff();
		// }
		// else
		// {
		// 	try
		// 	{
		// 		throw new Myset.MyException();
		// 	}
		// 	catch(Myset.MyException ex) 
		// 	{
		// 		System.out.println("switchOffMobile "+a+": Error - Phone with identifier "+a+" does not exist.");
		// 	}
		// }
		MobilePhone mp=this.root.residentSet().getNodeWithID(a);
		try
		{
		 	mp.switchOff();
		}
		catch(Exception e)
		{
			System.out.println("switchOffMobile "+a+": Error - Phone with identifier "+a+" does not exist.");
		}
	}

	public String queryNthChild(int a, int b)
	{
		Exchange exch=this.getNodeWithID(a);
		if(exch==null)
		{
			try
			{
				throw new Myset.MyException();
			}
			catch(Myset.MyException ex) 
			{
				return "queryNthChild "+a+" "+b+": Error - Node with identifier "+a+" does not exist in the tree.";
			}
		}
		else
		{
			try
			{
				int ans=exch.child(b).getData();
			}
			catch(Exception e)
			{
				return ("queryNthChild "+a+" "+b+": Error - Exchange with identifier "+a+" does not have a child in position number "+b);
			}
			return ("queryNthChild "+a+" "+b+": "+exch.child(b).getData());
		}
	}

	public String queryMobilePhoneSet(int a)
	{
		Exchange exch=this.getNodeWithID(a);
		if(exch==null)
		{
			try
			{
				throw new Myset.MyException();
			}
			catch(Myset.MyException ex) 
			{
				return "queryMobilePhoneSet "+a+": Error - Node with identifier "+a+" does not exist in the tree.";
			}
		}
		else
		{
			String ans="";
			ans=ans+"queryMobilePhoneSet "+a+": ";
			int counter=0,flag=1; 
			for(int i=0;i<exch.residentSet().rset.list.size;i++)
			{
				MobilePhone mp=exch.residentSet().getNodeInLocation(i);;
				if(mp.status())
					counter++;
			}
			for(int i=0;i<exch.residentSet().rset.list.size;i++)
			{
				MobilePhone mp=exch.residentSet().getNodeInLocation(i);;
				if(mp.status() && flag<counter)
				{
					ans=ans+mp.number()+", ";
					flag++;
				}
				else if(mp.status() && flag==counter)
				{
					ans=ans+mp.number();
				}
			}
			return ans;
		}
	}

	//*******************Assignement 1 has ended*********************
	//*******************Assignment 2 Begins*************************

	public Exchange findPhone(MobilePhone m)
	{
		for(int i=0;i<this.root.residentSet().rset.list.size;i++)
		{
			MobilePhone p=this.root.residentSet().getNodeInLocation(i);
			try
			{
				Boolean test=p.status();
			}
			catch(Exception e)
			{
				System.out.println("queryFindPhone "+m.number()+": Phone is switched off.");
				return (new Exchange(-1));
			}
			if(p.status() && p.number()==m.number())
				return p.location();
		}
		return null;
	}

	public Exchange lowestRouter(int a,int b)
	{
		Exchange ex1=this.getNodeWithID(a);
		Exchange ex2=this.getNodeWithID(b);
		if(ex1==null)
		{
			System.out.println("queryLowestRouter "+a+" "+b+" :Error - No exchange with identifier "+a+" found in the network.");
			return null;
		}
		if(ex2==null)
		{
			System.out.println("queryLowestRouter "+a+" "+b+" :Error - No exchange with identifier "+b+" found in the network.");
			return null;
		}
		if(a==b)
			return ex1;
		if(ex1.isRoot())
			return ex1;
		if(ex2.isRoot())
			return ex2;
		RoutingMapTree rmt1=new RoutingMapTree(ex1);
		RoutingMapTree rmt2=new RoutingMapTree(ex2);
		if(rmt1.containsNode(ex2))
			return ex1;
		else if(rmt2.containsNode(ex1))
			return ex2;
		else
			return lowestRouter(ex1.parent().data,ex2.parent().data);
	}

	public Exchange lowestRouter(Exchange a,Exchange b)
	{
		return this.lowestRouter(a.data, b.data);
	}

	public ExchangeList routeCall(MobilePhone a, MobilePhone b)
	{
		if(this.findPhone(a).equals(null))
		{
			System.out.println("findCallPath "+a.number()+" "+b.number()+" :Error - Mobile Phone with identifier "+a.number()+" not found in Network.");
			return null;
		}
		if(this.findPhone(b).equals(null))
		{
			System.out.println("findCallPath "+a.number()+" "+b.number()+" :Error - Mobile Phone with identifier "+b.number()+" not found in Network.");
			return null;
		}
		SinglyLinkedList<Exchange> list=this.findCallPath(a.number(),b.number());
		ExchangeList e1=new ExchangeList();
		e1.eList=list;
		return e1;
	}

	public SinglyLinkedList<Exchange> findCallPath(int a,int b)
	{
		// MobilePhone mp1=this.root.residentSet().getNodeWithID(a);
		// MobilePhone mp2=this.root.residentSet().getNodeWithID(b);
		// try{
		// 	Boolean test=mp1.status();
		// }
		// catch(Exception e)
		// {
		// 	System.out.println("queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+a+" is currently switched off");
		// 	return null;
		// }
		// try{
		// 	Boolean test=mp2.status();
		// }
		// catch(Exception e)
		// {
		// 	System.out.println("queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+b+" is currently switched off");
		// 	return null;
		// }
		{ //dangling else
		if(a==b)
		{
			SinglyLinkedList<Exchange> e=new SinglyLinkedList<>();
			e.addLast(findPhone(this.root.residentSet().getNodeWithID(a)));
			return e;
		}
		else
		{
			Exchange ea=(findPhone(this.root.residentSet().getNodeWithID(a)));
			Exchange eb=(findPhone(this.root.residentSet().getNodeWithID(b)));
			Exchange elr=this.lowestRouter(ea, eb);
			SinglyLinkedList<Exchange> e1=new SinglyLinkedList<>();
			SinglyLinkedList<Exchange> e2=new SinglyLinkedList<>();
			SinglyLinkedList<Exchange> e3=new SinglyLinkedList<>();
			if(ea.getData()==elr.getData())
			{
				e1.addLast(ea);
				ea=ea.parent();
			}
			else
			{
				e1.addLast(ea);
				while(ea!=null && ea.getData()!=elr.getData())
				{
					ea=ea.parent();
					e1.addLast(ea);
				}
			}
			while(eb!=null && eb.getData()!=elr.getData())
			{
				e2.addLast(eb);
				eb=eb.parent();
			}
			for(int i=0;i<e2.size;i++)
			{
				e3.addFirst(e2.get(i));
			}
			for(int i=0;i<e3.size;i++)
			{
				Exchange etemp=e3.get(i);
				e1.addLast(etemp);
			}
			return e1;
		}}
	}

	public void movePhone(int a,int b)
	{
		Exchange e=this.getNodeWithID(b);
		MobilePhone mp=this.root.residentSet().getNodeWithID(a);
		if(e==null)
		{
			System.out.println("movePhone "+a+" "+b+ ":Error - Exchange with identifier "+b+" not found in the network.");
			return;
		}
		if(mp==null)
		{
			System.out.println("movePhone "+a+" "+b+ ":Error - Mobile Phone with identifier "+a+" not found in the network.");
			return;
		}
		if(!mp.status())
		{
			System.out.println("movePhone "+a+" "+b+ ":Error - Mobile Phone with identifier "+a+" is not switched on.");
			return;
		}
		if(e.numChildren()!=0)
		{
			System.out.println("movePhone "+a+" "+b+ ":Error - Exchange with identifier "+b+" is not a zero level exchange.");
		}
		else
		{
			this.switchOffMobile(a);
			this.switchOnMobile(a, b);
		} //dangling else
	}

	public String performAction(String actionMessage)
	{
		String[] arr=actionMessage.trim().split("\\s+");
		if(arr[0].equalsIgnoreCase("addExchange"))
		{
			if(arr.length==3)
				this.addExchange(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("switchOnMobile"))
		{
			if(arr.length==3)
				this.switchOnMobile(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("switchOffMobile"))
		{
			if(arr.length==2)
				this.switchOffMobile(Integer.parseInt(arr[1]));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("queryNthChild"))
		{
			if(arr.length==3)
				return this.queryNthChild(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("queryMobilePhoneSet"))
		{
			if(arr.length==2)
				return this.queryMobilePhoneSet(Integer.parseInt(arr[1]));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		//******* Post Assignment 1 *********
		else if(arr[0].equalsIgnoreCase("findPhone"))
		{
			if(arr.length==2)
			{
				try
				{
					int ans=this.findPhone(this.root.residentSet().getNodeWithID(Integer.parseInt(arr[1]))).data;
					if(ans!=(-1))
					return "queryFindPhone "+arr[1]+": "+ans;
				}
				catch(Exception ex) 
				{
					return ("queryFindPhone "+arr[1]+": Error - No mobile phone with identifier "+arr[1]+" found in the network");
				}
			}
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return ("queryFindPhone "+arr[1]+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("lowestRouter"))
		{
			if(arr.length==3)
				return "queryLowestRouter "+arr[1]+" "+arr[2]+": "+String.valueOf(this.lowestRouter(Integer.parseInt(arr[1]), Integer.parseInt(arr[2])).data);
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return ("queryLowestRouter "+arr[1]+" "+arr[2]+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("findCallPath"))
		{
			if(arr.length==3)
			{
				int a=Integer.parseInt(arr[1]); int b=Integer.parseInt(arr[2]);
				MobilePhone mp1=this.root.residentSet().getNodeWithID(a);
				MobilePhone mp2=this.root.residentSet().getNodeWithID(b);
				try{
					Boolean test=mp1.status();
				}
				catch(Exception e)
				{
					return ("queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+a+" is currently switched off");
				}
				try{
					Boolean test=mp2.status();
				}
				catch(Exception e)
				{
					return ("queryFindCallPath "+a+" "+b+": Error - Mobile phone with identifier "+b+" is currently switched off");
				}
				SinglyLinkedList<Exchange> l=findCallPath(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
				if(l!=null){
				String ans="";
				for(int i=0;i<l.size-1;i++)
				{
					try
					{
						ans=ans+l.get(i).data+", ";
					} 
					catch(Exception e)
					{
						ans=ans+"0, ";
					}
					//System.out.println(ans);
				}
				return "queryFindCallPath "+arr[1]+" "+arr[2]+": "+ans+l.get(l.size-1).data;}
			}
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return ("queryFindCallPath "+arr[1]+" "+arr[2]+": Invalid Input");
				}
			}	
		}
		else if(arr[0].equalsIgnoreCase("movePhone"))
		{
			if(arr.length==3)
				this.movePhone((Integer.parseInt(arr[1])),(Integer.parseInt(arr[2])));
			else
			{
				try
				{
					throw new Myset.MyException();
				}
				catch(Myset.MyException ex) 
				{
					return (actionMessage+": Invalid Input");
				}
			}	
		}
		else
			return (actionMessage+": Invalid Input");
		return "";
	}
}