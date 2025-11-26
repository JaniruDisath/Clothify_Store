package student.services.db.item;

import org.hibernate.Session;
import repository.ClothifyDatabase;
import repository.itemsTable.ItemTable;
import student.model.dto.Item;
import student.model.entity.ItemEntity;
import student.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static student.mappers.ItemMapper.itemToModel;

public class ItemServiceImpl implements ItemService {

    private final ClothifyDatabase<ItemEntity> clothifyDatabase = new ItemTable();
    private Session session = HibernateUtil.getSessionFactory().openSession();

        @Override
        public List<Item> getAllItems() {
            List<ItemEntity> entities = clothifyDatabase.getAllData(session);
            List<Item> items = new ArrayList<>();
            for (ItemEntity entity : entities) {
                items.add(itemToModel(entity));
            }
            return items;
        }

}
