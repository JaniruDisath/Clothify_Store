package repository;

import org.hibernate.Session;

import java.util.List;

public interface ClothifyDatabase<T> {

    List<T> getAllData(Session session);
    //List<String> getListOfPrimaryKeys();

    //Insert Items
    void insertAnItem(Session session,T t);

    //Update An Item
    T getAnItem(Session session,String primaryID);

    //Update An Item
    void updateAnItem(Session session,T t);

    //Delete An Item
    void deleteAnItem(Session session,String primaryID);

    //Get The last Added Record
    T getLastAddedItem(Session session);
}
