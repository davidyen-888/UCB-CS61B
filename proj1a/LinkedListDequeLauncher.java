public class LinkedListDequeLauncher{
    public static void main(String[] args){
        LinkedListDeque<String> LLD1=new LinkedListDeque<>("test1");
        LLD1.addFirst("addFirst_test");
        LLD1.addLast("addLast_test");
        LLD1.removeFirst();
        LLD1.printDeque();
        System.out.println(LLD1.getRecursive(0));
    }
}