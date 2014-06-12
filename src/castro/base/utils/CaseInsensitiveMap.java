package castro.base.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Case Insensitive Map
public class CaseInsensitiveMap<V> extends HashMap<CaseInsensitiveString, V>
{
    private static final long serialVersionUID = 1L;


	public void put(String key, V value)
	{
		super.put(new CaseInsensitiveString(key), value);
	}
	
	
	@Override
	public V get(Object key)
	{
		return super.get(CaseInsensitiveString.convert(key));
	}
	
	
	@Override
	public boolean containsKey(Object key)
	{
		return super.containsKey(CaseInsensitiveString.convert(key));
	}
	
	
	@Override
	public boolean containsValue(Object key)
	{
		return super.containsKey(CaseInsensitiveString.convert(key));
	}
	
	
	public Set<String> originalKeySet()
	{
		Set<String> keys = new HashSet<>();
		for(CaseInsensitiveString key : keySet())
			keys.add(key.getOriginalString());
		return keys;
	}
}
