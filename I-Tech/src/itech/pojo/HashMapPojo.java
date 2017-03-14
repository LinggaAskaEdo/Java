package itech.pojo;

import java.util.HashMap;

public class HashMapPojo
{
	@SuppressWarnings("rawtypes")
	public HashMap hm = new HashMap(); 
	
	public HashMapPojo()
	{}

	@SuppressWarnings("unchecked")
	public void setMap(String a, String b) 
	{		
		hm.put(a, b);
	}

	public String getMap(String c)
	{
		String data = (String) hm.get(c);
		return data;
	}	
}
