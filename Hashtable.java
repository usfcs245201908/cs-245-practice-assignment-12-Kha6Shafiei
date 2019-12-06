import java.util.*;

public class Hashtable {

    int size;
    ArrayList<HashNode>bucket;  
    int bucketnum;

    private class HashNode
    {
        String key;
        String value;
        HashNode next;

        public HashNode(String key, String value)
        {
            this.key=key;
            this.value=value;
            next=null;
        }
    }

    public Hashtable(){
        bucket= new ArrayList<HashNode>();
        bucketnum=2028;
        size=0;

        for (int i=0; i < bucketnum; i++){
            bucket.add(null);
        }
    

    }


    public void put (String key, String value){

        int index = getHash(key);
        HashNode head = bucket.get(index);

        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = bucket.get(index);
        HashNode newNode = new HashNode(key, value);
        newNode.next=head;
        bucket.set(index, newNode);

        if ((1.0*size)/bucketnum >= 0.5)
        {
            ArrayList<HashNode> temp = bucket;
            bucketnum = 2 * bucketnum; 
            bucket = new ArrayList<HashNode>(bucketnum); 
            size = 0; 

            for (int i = 0; i < bucketnum; i++) {
                bucket.add(null); 
            }
  
            for (HashNode headReplace : temp) 
            { 
                while (headReplace != null) 
                { 
                    put(headReplace.key, headReplace.value); 
                    headReplace = headReplace.next; 
                } 
            } 
        }
    }

    
    
    public boolean containsKey(String key) {
        
        if (get(key)==null){
            return false;
        }
        return true;
    }


  
    public String get(String key){

        int index = getHash(key);
        HashNode head = bucket.get(index);

        while(head!=null)
        {
            if((head.key).equals(key))
            {
                return head.value;
            }
            head=head.next;
        }

        return null;
    }


    private int getHash(String key){
        return (Math.abs(key.hashCode()) % bucketnum);
    }


    public String remove(String key) throws Exception { 

        int index = getHash(key);
        HashNode head = bucket.get(index);

        HashNode prev = null;
        while (head != null && head.key.equals(key)==false)
        {
            prev = head;
            head = head.next;
        }

        if (head == null){
            throw new Exception();
        }

        size--;

        if (prev != null){
            prev.next = head.next;
        }
        else{
            bucket.set(index, head.next);
        }
        return head.value;
    }
}