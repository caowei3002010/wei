package unl.cse;

public class DSLinkedList implements List {
	
	public Node head;

	public DSLinkedList() {
		
		head = null;
	}
	public DSLinkedList(Node head_) {
		
		head = head_;
	}
	
	public DSLinkedList(DSLinkedList other) { // Copy constructor. 
		

		for(int i=0;i<other.size();i++){
			this.add(other.get(i));
		}
	}
	
	public DSLinkedList sort(){
		
		DSLinkedList tmp=new DSLinkedList(this);
		DSLinkedList ret = new DSLinkedList();

		while(tmp.isEmpty()==false){
			Invoice max=tmp.get(0);
			int k=0;
			for(int i=1;i<tmp.size();i++){
				if(max.compareTo(tmp.get(i))<0){
					max=tmp.get(i);
					k=i;
				}
			}
			ret.add(max);
			tmp.remove(k);
		}
		return ret;
	}

	public Invoice remove(int index) {
		
		if(index<0 || index>=size() || this.isEmpty()){
			throw new IndexOutOfBoundsException();
		}

		Node node = head;
		if(index==0){
			Invoice ret = head.getObject();
			head = head.next;
			return ret;
		}
		for(int i=0;i<size();i++){
			if(i>=index){
				break;
			}
			node=node.next;
		}
		Node pre=node.previous;
		pre.next = node.next;
		if(node.next!=null){
			node.next.previous=pre;
		}
		return node.getObject();
		
	}
	
	public int indexOf(Invoice obj) {
		
		for(int i=0;i<size();i++){
			if(this.get(i).equals(obj)){
				return i;
			}
		}
		return -1;
	}

	public Invoice get(int index) {
		
		if(index<0 || index>=size()){
			throw new IndexOutOfBoundsException();
		}else{
			int i = 0;
			Node node = head;
			while(i<index){
				i++;
				node=node.next;
			}
			return node.getObject();
		}
	}

	public boolean isEmpty() {
		return size()==0;
	}

	public int size() {
		
		Node node= head;
		int s=0;
		while(node!=null){
			node=node.next;
			s++;
		}
		return s;
	}
	
	@Override
	public String toString() {
		
		String ret="";
		for(int i=0;i<size();i++){
			ret+=this.get(i);
			if(i<size()-1){
				ret+=" ";
			}
		}
		return ret;
	}

	public boolean add(Invoice obj) {
		
		if(obj==null){
			throw new NullPointerException();
		}else{
			if(this.isEmpty()){					
				head = new Node(null,null,obj);
				return true;
			}
			Node node=head;
			while(node.next!=null){
				node=node.next;
			}
			Node newnode = new Node(null,node,obj);
			node.next=newnode;
			
			return true;
		}
	}

	public boolean add(int index, Invoice obj) {
		
		if(obj==null){
			throw new NullPointerException();
		}else if(index<0 || index>size()){
			throw new IndexOutOfBoundsException();
		}else{
			if(this.isEmpty()){
				head = new Node(null,null,obj);
				return true;
			}else if(index==0){
				Node newnode = new Node(head,null,obj);
				head = newnode;
				return true;
			}
			Node node = head;
			for(int i=0;i<size();i++){
				if(i>=index){
					break;
				}
				node=node.next;
			}
			if(node==null){
				add(obj);
				return true;
			}
			Node newnode = new Node(node,node.previous,obj);
			node.previous.next=newnode;
			return true;
			
		}

	}

	public boolean contains(Invoice obj) {
		
		if(obj==null){
			throw new NullPointerException();
		}
		Node node= head;
		while(node!=null){
			if(node.getObject().equals(obj)){
				return true;
			}else{
				node=node.next;
			}
		}
		return false;
	}

	public boolean remove(Invoice obj) {
		
		if(obj==null){
			throw new NullPointerException();
		}else{
			int index = indexOf(obj);
			if(index<0){
				return false;
			}else{
				remove(index);
				return true;
			}
		}
	}
	
	@Override
	public int hashCode() {
		
		int ret =0 ;
		for(int i=0;i<size();i++){
			ret+=get(i).hashCode();
		}
		return new Integer(ret).hashCode();
	}

	@Override
	public boolean equals(Object other) {
		
		DSLinkedList o = (DSLinkedList)other;
		if(o.size()==this.size()){
			for(int i=0;i<size();i++){
				if(get(i).equals(o.get(i))==false){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
}
