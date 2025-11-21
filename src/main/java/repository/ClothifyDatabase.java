package repository;

import java.util.List;

public interface ClothifyDatabase<T> {

    List<T> getAllData();
    //List<String> getListOfPrimaryKeys();

    //Insert Items
    void insertAnItem(T t);

    //Update An Item
    T getAnItem(String primaryID);

    //Update An Item
    void updateAnItem(T t);

    //Delete An Item
    void deleteAnItem(String primaryID);

    //Get The last Added Record
    T getLastAddedItem();
}
