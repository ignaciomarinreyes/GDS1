
package dao;

interface IDAOBase<T> {
    public void create(T createClase); 
    public T read(int id); 
    public void update(T updateClase); 
    public void remove(int id) throws IllegalStateException; 
}
