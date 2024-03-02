/*
add elements to List<List<Integer>>

wrong
-----
List<Integer> w = new ArrayList<Integer>();
  List<List<Integer>> a = new ArrayList<ArrayList<Integer>>();
  for(int i=1;i<n; i++){
      w.add(i);
      a.add(w);
  }

correct
=======
List<Integer> w = new ArrayList<Integer>();
List<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>(); //Use Arraylist inside

for(int i=1;i<10; i++){
    w.add(i);
    a.add(new ArrayList(w));
}

System.out.println(w);
System.out.println(a);


- If you will add a.add(w) it will add reference to the list w inside all elements of list a, and since you are constantly changing the list w, by adding more numbers, all elements in a list will point to same w, hence will contain like this [[1,2,3,4,5,6,7,8,.9], [1,2,3,4,5,6,7,8,9] .....] instead of [[1], [1,2], [1,2,3], ....[1,2,3,4,5,6,7,8,9]]
*/
