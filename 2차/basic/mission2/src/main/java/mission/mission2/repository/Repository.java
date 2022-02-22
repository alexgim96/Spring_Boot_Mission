package mission.mission2.repository;

import mission.mission2.entity.Board;

import java.util.List;

public interface Repository<T> {
    void save(T item);
    List<T> readAll();
    T readOne(int id);
    void update(int id, T item);
    void delete(int id);
}
