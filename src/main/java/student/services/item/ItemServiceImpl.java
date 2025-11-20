package student.services.item;

import repository.ClothifyDatabase;
import repository.itemsTable.ItemTable;
import student.model.dto.Item;
import student.model.entity.ItemEntity;

import java.util.ArrayList;
import java.util.List;

import static student.mappers.ItemMapper.itemToModel;

public class ItemServiceImpl implements ItemService {

    ClothifyDatabase<ItemEntity> clothifyDatabase = new ItemTable();

        @Override
        public List<Item> getAllItems() {
            List<ItemEntity> entities = clothifyDatabase.getAllData();
            List<Item> items = new ArrayList<>();
            for (ItemEntity entity : entities) {
                items.add(itemToModel(entity));
            }
            return items;
        }

}
