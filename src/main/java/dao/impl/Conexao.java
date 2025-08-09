package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.properties.BancoDadosProperties;

public class Conexao {

    private static BancoDadosProperties properties = BancoDadosProperties.getInstance();

    public static Connection getConnectionPostgres() throws ClassNotFoundException, SQLException {
        return Conexao.getConnection();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getDriver());
        return DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
    }
}
