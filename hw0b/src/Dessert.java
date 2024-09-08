public class Dessert {
    int flavor;
    int price;
    static int numDesserts = 0;

    public Dessert(int f,int p){
        flavor = f;
        price = p;
        numDesserts += 1;
    }

    public void printDessert(){
        String s = new String();
        s += flavor;
        s += ' ';
        s += price;
        s += ' ';
        s += numDesserts;
        System.out.println(s);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
