package ServerSide;

import java.util.List;

public interface Gestion<T>{
    public T getT(String id );
    public List<T> getAllT(int id);
    public void addT(T t);
    public void editT(T t , int id);
    public void deleteT(int id );
}
