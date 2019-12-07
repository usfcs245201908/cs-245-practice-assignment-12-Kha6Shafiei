public class Hashtable {


    int sizeOfHashTable;
    Node[] elements;

    Hashtable(){
        this.sizeOfHashTable = 1000;
        this.elements = new Node[1000];
    }

    Hashtable(int size){
        this.sizeOfHashTable = size;
        this.elements = new Node[size];
    }


    private class Node {
        String key;
        String value;
        Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }


    boolean containsKey(String key){
        int hash = hash(key);
        if(elements[hash] != null) {
            return true;
        }


        return false;
    }


    String get(String key){
        int hash = hash(key);
        if(elements[hash] != null) {
            Node temp = elements[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }

        return null;
    }

    void put(String key, String value){
        Node hashElement = new Node(key, value);
        int hash = hash(key);
        if(elements[hash] == null) {
            elements[hash] = hashElement;
        } else {
            hashElement.next = elements[hash];
            elements[hash] = hashElement;
        }
    }

    String remove(String key){
        int hash = hash(key);
        if(elements[hash] != null) {
            Node temp = elements[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    elements[hash] = null;
                    return temp.value;
                }
                temp = temp.next;
            }


        }
        return null;
    }




    private int hash(String key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % sizeOfHashTable;
    }

}