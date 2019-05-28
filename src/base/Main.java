package base;

public class Main {

    public static void main(String[] args) {

        SearchTree<Integer> Tree = new SearchTree<>();
        Tree.insertion(5);
        Tree.insertion(4);
        Tree.insertion(10);
        Tree.insertion(11);
        Tree.deletion(4);
        Tree.insertion(1);
        Tree.insertion(4);


        System.out.println(Tree.search(12));
        System.out.println(Tree.max());
        System.out.println(Tree.min());

        System.out.println(Tree.search(4));
        System.out.println(Tree.toSortedArrayList());


        int i = 0;






    }
}
