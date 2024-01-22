package app;

import java.util.Comparator; 

//https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
  
// node class is the basic structure 
// of each node present in the Huffman - tree. 
public class HuffmanNode{  
  
    int data; //frequency
    char c;   //char
  
    HuffmanNode left; 
    HuffmanNode right; 
} 
  
// comparator class helps to compare the node 
// on the basis of one of its attribute. 
// Here we will be compared 
// on the basis of data values of the nodes. 
class MyComparator implements Comparator<HuffmanNode> { 
    public int compare(HuffmanNode x, HuffmanNode y) 
    { 
        return x.data - y.data; 
    } 
} 
  

  
// This code is contributed by Kunwar Desh Deepak Singh 