import model.dto.CustomerEntity;
import repository.ClothifyDatabase;
import repository.customerTable.CustomerTable;

public class Main {
    public static void main(String[] args) {
        ClothifyDatabase clothifyDatabase = new CustomerTable();
        clothifyDatabase.insertAnItem(new CustomerEntity("test2","Name","0713769480","Email","Address"));
        System.out.println(clothifyDatabase.getAnItem("test2"));
        clothifyDatabase.updateAnItem(new CustomerEntity("test2","Name","0713769480","Email","Address Test"));
        System.out.println(clothifyDatabase.getAnItem("test2"));
        clothifyDatabase.deleteAnItem("test1");
        System.out.println(clothifyDatabase.getAllData());
//        Starter.main(args);
    }
}