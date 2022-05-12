package rifqimuhammadaziz.javafxdatabase.dao;

public class UserDaoImpl {

    public boolean login(String username, String password) {
        return username.equals("xenosty") && password.equals("xenosty");
    }
}
