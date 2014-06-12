package castro.base.utils;

// Case Insensitive String
public class CaseInsensitiveString
{
	private final String originalString;
	private final String lowercasedString;
	
	
	public CaseInsensitiveString(String string)
	{
		this.originalString   = string;
		if(originalString == null)
			lowercasedString  = null;
		else
			lowercasedString  = string.toLowerCase();
	}
	
	
	@Override
	public int hashCode()
	{
		return lowercasedString.hashCode();
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(o != null)
			return lowercasedString.equals(o.toString().toLowerCase());
		return isNull();
	}
	
	
	public boolean isNull()
	{
		return originalString == null;
	}
	
	
	@Deprecated
	@Override
	public String toString()
	{
		return originalString;
	}
	
	
	public String toOriginalString()
	{
		return originalString;
	}
	
	
	public String getOriginalString()
	{
		return originalString;
	}
	
	
	public static CaseInsensitiveString convert(Object o)
	{
		return new CaseInsensitiveString(o.toString());
	}
	
}
