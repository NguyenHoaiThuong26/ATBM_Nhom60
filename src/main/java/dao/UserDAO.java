package dao;

import bean.Product;
import bean.User;
import db.JDBIConnector;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAO {
    public static User getUserByEmail(String email) {
        Optional<User> user = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("select * from users where email = ?")
                        .bind(0, email)
                        .mapToBean(User.class).stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }

    public static User getUserByUsername(String username) {
        Optional<User> user = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("select * from users where username = ?")
                        .bind(0, username)
                        .mapToBean(User.class).stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }

    public static User getUserById(int id) {
        Optional<User> user = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("select * from users where users.id = ?")
                        .bind(0, id)
                        .mapToBean(User.class).stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }

    public static User adminViewUser(int id) {
        User user = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy user
        );
        return user;
    }

    public static void changeInfoUserWithRole(int id, int role) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("UPDATE users set " +
                                "role = :role" +
                                " where id = :id")
                        .bind("id", id)
                        .bind("role", role)
                        .execute()
        );
    }

    public static void changeSpecificInfo(int id, String email, int phone, String firstName, String lastName, String birthDate, String gender) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("UPDATE users set email = :email, phone = :phone, firstName = :firstName, lastName = :lastName, birthDate = :birthDate, gender =:gender" +
                                " where id = :id")
                        .bind("id", id)
                        .bind("email", email)
                        .bind("phone", phone)
                        .bind("firstName", firstName)
                        .bind("lastName", lastName)
                        .bind("birthDate", birthDate)
                        .bind("gender", gender)
                        .execute()
        );
    }



    public static List<User> getListUserById(int id) {
        List<User> userList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE user.id = ?")
                        .bind(0, id)
                        .mapToBean(User.class)
                        .collect(Collectors.toList())
        );
        return userList;
    }


    public static List<User> getUserList() {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select * from users")
                        .mapToBean(User.class)
                        .collect(Collectors.toList())
        );
    }

    public User getUserById(String id) {
        Optional<User> user = JDBIConnector.me().withHandle((handle ->
                handle.createQuery("select * from users where id = ?")
                        .bind(0, id)
                        .mapToBean(User.class).stream().findFirst()
        ));
        return user.isEmpty() ? null : user.get();
    }

    public static void registerUser(String username, String email, String password) {
        int id = 0;
        int phone = 0;
        String first = "";
        String last = "";
        Date date = new Date();
        String gender = "";
        int role = 0;
        int status = 0;
        JDBIConnector.me().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO users VALUE (:id, :username, :password, :email, :phone, :first, :last, :date, :gender, :role, :status)")
                    .bind("id", id)
                    .bind("username", username)
                    .bind("password", password)
                    .bind("email", email)
                    .bind("phone", phone)
                    .bind("first", first)
                    .bind("last", last)
                    .bind("date", date)
                    .bind("gender", gender)
                    .bind("role", role)
                    .bind("status", status)
                    .execute();
        });
    }

    public static void verifyUser(String email) {
        JDBIConnector.me().withHandle(handle -> {
            return handle.createUpdate("UPDATE users SET status = 1 where email = :email")
                    .bind("email", email).execute();
        });
//        System.out.println("done");
    }

    public static void changePassword(String email, String password) {
        String passwordChanged = util.Encode.toSHA1(password);
        JDBIConnector.me().withHandle(handle -> {
            return handle.createUpdate("UPDATE users SET password = :password where email = :email")
                    .bind("password", passwordChanged)
                    .bind("email", email).execute();
        });
        System.out.println("done");
    }

    public static void changeInfo(User u) {
        JDBIConnector.me().withHandle(handle -> {
            return handle.createUpdate("UPDATE users SET email = :email, phone = :phone, firstName = :firstName, lastName = :lastName, birthDate = :birthDate, gender = :gender where username = :username")
                    .bind("email", u.getEmail())
                    .bind("phone", u.getPhone())
                    .bind("firstName", u.getFirstName())
                    .bind("lastName", u.getLastName())
                    .bind("birthDate", u.getBirthDate())
                    .bind("gender", u.getGender())
                    .bind("username", u.getUsername()).execute();

        });
        System.out.println("done");
    }

    public static List<User> adminSearchUser(String value) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE username LIKE :value OR email LIKE :value")
                        .bind("value", "%" + value + "%")
                        .mapToBean(User.class)
                        .collect(Collectors.toList())
        );
    }

    public static void main(String[] args) {
//        changePassword("cunoccho0601@gmail.com", "hahaha");

        changeInfoUserWithRole(5, 0);

    }
}