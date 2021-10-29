package ru.job4j.grabber;

        import ru.job4j.grabber.utils.SqlRuDateTimeParser;

        import java.io.IOException;
        import java.io.InputStream;
        import java.sql.*;
        import java.time.LocalDateTime;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (InputStream inputStream =
                     PsqlStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            PsqlStore psqlStore = new PsqlStore(properties);
            Post post2 = new Post();
            post2.setLink("https://www.sql.ru/forum/1338827/java-razrabotchik");
            post2.setCreated(new SqlRuDateTimeParser().parse("16 сен 21, 09:57"));
            psqlStore.save(post2);
            List<Post> list = psqlStore.getAll();
            Post post = psqlStore.findById(1);
            for (Post p : list) {
                System.out.println(p);
            }
            System.out.println(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Post post) {
        String sql = "insert into schema(name, text, link, created) values (?, ?, ?, ?);";
        if (!availabilityPost(post)) {
            try (PreparedStatement statement =
                         cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, post.getTitle());
                statement.setString(2, post.getDescription());
                statement.setString(3, post.getLink());
                statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
                statement.execute();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private boolean availabilityPost(Post post) {
        boolean availability = false;
        String sql = "select link from schema where link = ?;";
        try (PreparedStatement statement = cnn.prepareStatement(sql)) {
            statement.setString(1, post.getLink());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    availability = true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return availability;
    }

    @Override
    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        String sql = "select * from schema;";
        try (PreparedStatement statement = cnn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Post post = createPost(resultSet);
                list.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        String sql = "select * from schema where id = ?;";
        try (PreparedStatement statement = cnn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    post = createPost(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    public Post createPost(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getInt("id"));
        post.setTitle(resultSet.getString("name"));
        post.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        post.setDescription(resultSet.getString("text"));
        post.setLink(resultSet.getString("link"));
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}