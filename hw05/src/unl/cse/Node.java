package unl.cse;

public class Node {
	
	public Node next = null;
	public Node previous = null;
	private Invoice obj = null;

	public Node(Node next_, Node prev_, Invoice Invoice_) {
		this.next = next_;
		this.previous = prev_;
		this.obj = Invoice_;
	}

	public Invoice getObject() {
		return obj;
	}
	
	public String toString() {
		
		if ( obj == null ) 
			return "Node has no Invoice assigned.";
		else
			return "Node contains: " + obj.toString();	
	}
	
	@Override
	public boolean equals(Object other) {
	    if (this == other)
	        return true;
	    if (other == null)
	        return false;
	    if (!(other instanceof Node))
	        return false;
		
	    return obj.equals(((Node)other).getObject());
	}
	
	@Override
	public int hashCode() {
		if ( obj == null )
			return 0;
		return obj.hashCode();
	}
}
