// Trie

class Trie {
    TrieNode root;
    List<Character> capitalLetters;
    class TrieNode {
        Map<Character, TrieNode> nodes = new HashMap<>();
    }
    
    public Trie() { 
        root = new TrieNode(); 
        capitalLetters = new ArrayList<>();
    }
    
    public void insert(String pattern) {
        TrieNode curr = root;
        for(Character c : pattern.toCharArray()) {
            if(c <= 90) capitalLetters.add(c); // check if uppercase letter
            if(!curr.nodes.containsKey(c))
                curr.nodes.put(c, new TrieNode());
            curr = curr.nodes.get(c);            
        }
    }
    
    public boolean search(String query) {
        TrieNode curr = root;
        List<Character> currList = new ArrayList<>(capitalLetters);
		
        for(Character c : query.toCharArray()) {
            if(!curr.nodes.containsKey(c)){ //if not inside the map
                if(c <= 90) // if uppercase letter 
                    return false; // an extra letter found which is not contained in pattern
                else
                    continue;
            }
            currList.remove(new Character(c));
            curr = curr.nodes.get(c);
        }
        return currList.size() == 0;
    }
}

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        Trie trie = new Trie();
        trie.insert(pattern);
        
        List<Boolean> output = new ArrayList<>();
        for(String query : queries) output.add(trie.search(query));
        return output;
    }
}
