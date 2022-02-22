package mission.mission2.service;

import java.util.List;

public interface Service<T> {
    void createItem(T item);
    T viewOne(int id);
    List<T> viewAll();
    void modifyItem(int id, T item);
    void deleteItem(int id);
}
