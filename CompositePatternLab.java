import java.util.ArrayList;

interface HepsiTrendy11 {

    void Add(HepsiTrendy11 h);
    void Remove(HepsiTrendy11 h);
    void Display(int indent);
    public String getName();
    public int getPrice();
    public String getDescription();
}

class Product implements HepsiTrendy11 {
    private final String name;
    private final int price;
    private final String description;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Product(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public final void Add(HepsiTrendy11 h) {
        System.out.println("Cannot add an element");
    }

    @Override
    public final void Remove(HepsiTrendy11 h) {
        System.out.println("Cannot remove an element");
    }

    @Override
    public final void Display(int indent) {
        for(int i = 1;i <= indent;i++)     System.out.print("-");
        System.out.println(" "  + name);
    }
}

class Category implements HepsiTrendy11{
    private String name;
    private int price;
    private String description;

    private ArrayList<HepsiTrendy11> elements = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category(String name) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public void Add(HepsiTrendy11 h) {
        elements.add(h);
    }

    @Override
    public void Remove(HepsiTrendy11 h) {
        for (int i = 0; i < elements.size(); i++){
            if (elements.get(i).getName() == h.getName()){
                elements.remove(i);
                return;
            }
        }
    }

    @Override
    public void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());

        // Display each child element on this node
        for (int i= 0; i< elements.size(); i++) {
            elements.get(i).Display(indent+2);
        }
    }

    public boolean find(String name){
        if(this.getName().equals(name)){
            return true;
        }
        for (HepsiTrendy11 element : elements){
            if (element instanceof Category){
                if (((Category) element).find(name)){
                    return true;
                }
                else if (element.getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }

    public int totalPrice(String categoryName) {
        int totalPrice = 0;
        if (this.getName().equals(categoryName)) {
            for (HepsiTrendy11 element : elements) {
                if (element instanceof Product) {
                    totalPrice += ((Product) element).getPrice();
                } else if (element instanceof Category) {
                    totalPrice += ((Category) element).totalPrice(categoryName);
                }
            }
        } else {
            for (HepsiTrendy11 element : elements) {
                if (element instanceof Category) {
                    totalPrice += ((Category) element).totalPrice(categoryName);
                }
            }
        }
        return totalPrice;
    }

}

public class CompositePatternLab {
    //product- leaf
    //category- composite
    //hepsitrendy- component
    public static void main(String[] args) {

        HepsiTrendy11 root = new Category("Menu");
        HepsiTrendy11 elec = new Category("Electronics");
        HepsiTrendy11 tv = new Category("TV");
        tv.Add(new Product("OLED TV", 1000, "tv description"));
        tv.Add(new Product("QLED TV", 2000, "tv description"));
        elec.Add(tv);
        root.Add(elec);

        HepsiTrendy11 pc = new Category("PC");
        pc.Add(new Product("RAM", 200, "ram Description"));

        HepsiTrendy11 fash = new Category("Fashion");
        HepsiTrendy11 men = new Category("Men");
        men.Add(new Product("Suit", 10, "s"));
        HepsiTrendy11 woman = new Category("Women");
        woman.Add(new Product("shirt", 10, "shirt"));
        woman.Add(new Product("skirt", 10, "skirt"));
        fash.Add(men);
        fash.Add(woman);
        root.Add(fash);

        root.Display(1);

        System.out.println("Finding 'TV': " + ((Category) root).find("TV")); // true
        System.out.println("Finding 'Non-existent Product': " + ((Category) root).find("Non-existent Product")); // false

        System.out.println("Total price under 'Electronics': " + ((Category) root).totalPrice("Electronics")); // 3000
        System.out.println("Total price under 'Fashion': " + ((Category) root).totalPrice("Fashion")); // 30
        System.out.println("Total price under 'Non-existent Category': " + ((Category) root).totalPrice("Non-existent Category")); // 0
    }
}


