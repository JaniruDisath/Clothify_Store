import model.dto.CustomerEntity;
import repository.ClothifyDatabase;
import repository.customerTable.CustomerTable;

public class Main {
    public static void main(String[] args) {
        ClothifyDatabase clothifyDatabase = new CustomerTable();
        clothifyDatabase.insertAnItem(new CustomerEntity("test","Name","0713769480","Email","Address"));

        Starter.main(args);
    }
}