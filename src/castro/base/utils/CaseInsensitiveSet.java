package castro.base.utils;

import java.util.HashSet;

// Case Insensitive Set
public class CaseInsensitiveSet extends HashSet<CaseInsensitiveString>
{
	private static final long serialVersionUID = 1L;
	
	
	public boolean add(String value)
	{
		return super.add(new CaseInsensitiveString(value));
	}
	
	
	@Override
	public boolean remove(Object key)
	{
		return super.remove(new CaseInsensitiveString(key.toString()));
	}
	
	
	@Override
	public boolean contains(Object key)
	{
		return super.contains(CaseInsensitiveString.convert(key));
	}
}
