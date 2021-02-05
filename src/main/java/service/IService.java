package service;

import java.sql.SQLException;
import java.util.List;

public interface IService <T>{

List<T> findAll() throws ClassNotFoundException, SQLException;

T findByIdy(int id);

void update(int id, T t );

void delete(int id);

void create(T t);
}
