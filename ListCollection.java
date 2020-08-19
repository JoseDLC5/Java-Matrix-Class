package sa1;

import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
  private int nodeCount;
  protected List<SingleLL<E>> collection;

  /**
   * Base constructor, initializes an empty ListCollection.
   */
  public ListCollection() {
	  nodeCount = 0;
	  collection = new ArrayList<SingleLL<E>>();
  }

  /**
   * Initializes a ListCollection with+ `numLists` lists.
   * 
   * @param numLists
   */
  public ListCollection(int numLists) {
	  nodeCount = numLists;
	  for(int i = 0;i<numLists;i++) {
		  SingleLL<E> temp = new SingleLL<E>();
		  collection.add(temp);
	  }
  }

  /**
   * @return The size of the `ListCollection`
   */
  public int size() {
	  return collection.size();
  }

  /**
   * Sets the nodeCount
   * 
   * @param nodeCount
   */
  public void setNodeCount(int nodeCount) {
	  this.nodeCount = nodeCount;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount() {
	  return nodeCount;
  }

  /**
   * Adds the specified `SingleLL` to the end of the `ListCollection`
   * 
   * @param list
   */
  public void addList(SingleLL<E> list) {
	  collection.add(list);
	  nodeCount += list.size();  
}

  /**
   * Adds the specified `List` to the `ListCollection`
   * 
   * @param list
   * @complexity O(n^2). The function calls on temp.append n times. The append function itself has an O(n) based on the size of the SingleLL.
   *  The first time append is called it has a run time of 1 which increases by one every time it is called until the last call where it will have a run time of n.
   *  Therefore the runtime of the addlist function is 1+2+3+...n which can be simplified to ((n)(n+1))/2 or (n^2+n)/2 so addList has a runtime of n^2.
   * 
   */
  public void addList(List<E> list) {
	  SingleLL<E> temp = new SingleLL<E>();
	  for(E n : list) {
		  temp.append(n);
	  }
	  collection.add(temp);
	  nodeCount += temp.size();
  }

  /**
   * Returns the list at the specified index
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @return the list
   */
  public SingleLL<E> getList(int listIndex) {
	  if(listIndex < 0 || listIndex >= collection.size()) {
		  throw new IllegalArgumentException("Index out of bounds");
	  }
	  else {
		  return collection.get(listIndex);
	  }
  }

  /**
   * Adds an element to the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   * @complexity O(n). In the worst case the user will ask to add an Element to the end of the longest list. 
   * I'm assuming the getList function is instant since I didn't make it so then insert would just take
   * n steps where n is the size of that list.
   */
  public void addElem(int listIndex, int elemIndex, E elem) {
	  if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex > this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("index out of bounds");
	  }
	  getList(listIndex).insert(elemIndex, elem);
	  nodeCount += 1;
  }

  /**
   * Sets the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   */
  public void setElem(int listIndex, int elemIndex, E elem) {
	  if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("index out of bounds");
	  }
	  getList(listIndex).set(elemIndex, elem);
  }

  /**
   * Returns the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @return
   */
  public E getElem(int listIndex, int elemIndex) {
	  if(listIndex < 0 || listIndex >= collection.size() || elemIndex < 0 || elemIndex >= this.getList(listIndex).size()) {
		  throw new IllegalArgumentException("index out of bounds");
	  }
	  return getList(listIndex).get(elemIndex);
  }

  /**
   * Returns the `ListCollection` in string form, following STRICTLY the rule of
   * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5] [6, 7, 8]]
   */
  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (SingleLL<E> list: collection) {
	      sb.append(list.toString());
	      sb.append(", ");
	    }
	    sb.deleteCharAt(sb.length()-1);
	    sb.deleteCharAt(sb.length()-1);
	    sb.append("]");
	    return sb.toString();
	  }


}
