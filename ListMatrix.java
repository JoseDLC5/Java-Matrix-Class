package sa1;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  /*public ListMatrix(int rows, int columns) {
	  this.rows = rows;
	  this.columns = columns;
	  setNodeCount(rows * columns);
	  
	  for(int i = 0; i < columns;i++) {
		  
		  SingleLL<Integer> temp = new SingleLL<Integer>();
		  
		  for(int j = 0; j<rows;j++) {
			  temp.append(0);
		  }
		  
		  collection.add(temp);
	  }
  }*/
  
  public ListMatrix(int rows, int columns) {
	  this.rows = rows;
	  this.columns = columns;
	  setNodeCount(rows * columns);
	  
	  for(int i = 0; i < rows;i++) {
		  
		  SingleLL<Integer> temp = new SingleLL<Integer>();
		  
		  for(int j = 0; j<columns;j++) {
			  temp.append(0);
		  }
		  
		  collection.add(temp);
	  }
  }
  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }

  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not properly coincide
   * @param other
   * @complexity O(n^3). this.setElem(i, j, this.getElem(i, j) + other.getElem(i,j)) is called rows*columns times in the code so it is called n^2 times.
   * The time complexity of getElem is linear since its i + j or n+n
   * the time complexity of setElem is linear since its also i + j or n+n
   * therefore the time complexity of the line
   * this.setElem(i, j, this.getElem(i, j) + other.getElem(i,j));
   * is 3n
   * and its called n^2 times so the overall time complexity is n^2(3n)
   * which is O(n^3)
   * 
   */
  public void add(ListMatrix other) {
	  if(rows != other.rows || columns != other.columns) {
		  throw new IllegalArgumentException("Matrix is not of the same dimensions");
	  }
	  else {
		  for(int i = 0;i<rows;i++) {
			  for(int j = 0;j<columns;j++) {
				  this.setElem(i, j, this.getElem(i, j) + other.getElem(i,j));
			  }
		  }
	  }
  }

  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
  
  public static ListMatrix transpose(ListMatrix matrix) {
	  ListMatrix newlm = new ListMatrix(matrix.columns,matrix.rows);
	  for(int i = 0; i<matrix.columns;i++) {
		  SingleLL<Integer> temp = new SingleLL<Integer>();
		  for(SingleLL<Integer> list: matrix.collection) {
			  temp.append(list.get(i));
		  }
		  newlm.collection.set(i, temp);
	  }
	return newlm;  
  }

  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not properly coincide
   * @param other
   * @return
   */
  public ListMatrix multiply(ListMatrix other) {
	  if(this.columns != other.rows) {
		  throw new IllegalArgumentException("column size of A must equal row size of B");
	  }
	  ListMatrix lm = new ListMatrix(this.rows,other.columns);
	  
	  for(int i = 0; i<lm.rows;i++) {
		  
		  for(int j = 0;j<lm.columns;j++) {
			  SingleLL<Integer> l1 = new SingleLL<Integer>();
			  
			  for(int k = 0; k<other.rows;k++) {
				  
				  l1.append(other.getElem(k, j));
			  }
			  lm.setElem(i, j, dotProduct(this.getList(i), l1));
			  
		  }
	  }
	  return lm;
  }
  
  public static int dotProduct(SingleLL<Integer> l1, SingleLL<Integer> l2) {
	  int ans = 0;
	  for(int i = 0;i<l1.size();i++) {
		  ans += l1.get(i) * l2.get(i);
	  }
	  return ans;
  }
  
  public static void main(String[] args) {
	  ListMatrix lm1 = new ListMatrix(2,3);
	  ListMatrix lm2 = new ListMatrix(3,2);
	  SingleLL<Integer> l1 = new SingleLL<Integer>();
	  SingleLL<Integer> l2 = new SingleLL<Integer>();
	  for(int i = 1;i<4;i++) {
		  l1.append(i);
	  }
	  for(int i = 1;i<3;i++) {
		  l2.append(i);
	  }
	  lm1.collection.set(0, l1);
	  lm1.collection.set(1, l1);
	  
	  lm2.collection.set(0, l2);
	  lm2.collection.set(1, l2);
	  lm2.collection.set(2, l2);
	  System.out.println(lm1);
	  System.out.println(lm2);
	  System.out.println(transpose(lm2));
	  
  }

}
