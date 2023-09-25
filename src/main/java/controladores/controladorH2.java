package controladores;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class controladorH2 {
    private static controladorH2 instance;
    private controladorH2(){
     creaTabla();
     datosTabla();
     select();
     pikachu();
    }
    public static controladorH2 getInstance() {
        if (instance == null) {
            instance = new controladorH2();
        }
        return instance;
    }
    private String csv(){
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "Data";
        String paisesFile = dir + File.separator + "pokemon.csv";
        return paisesFile;
    }
    private void creaTabla(){
       try{
           Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
           Statement statement = connection.createStatement();
           String createTableSQL = "CREATE TABLE IF NOT EXISTS pokedex (id INT PRIMARY KEY, num VARCHAR(255), name VARCHAR(255), height VARCHAR(255), weight VARCHAR(255))";
           statement.execute(createTableSQL);
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
    private void datosTabla(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            String insertSQL = "INSERT INTO pokedex (id, num, name, height, weight) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            Reader reader = new FileReader(csv());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : csvParser) {
                preparedStatement.setInt(1, Integer.parseInt(record.get("id")));
                preparedStatement.setString(2, record.get("num"));
                preparedStatement.setString(3, record.get("name"));
                preparedStatement.setString(4, record.get("height"));
                preparedStatement.setString(5, record.get("weight"));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                System.out.println("Datos cargados en la base de datos con éxito.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
private void select(){
    try {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        String querySQL = "SELECT * FROM pokedex";
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String numero = resultSet.getString("num");
            String nombre = resultSet.getString("name");
            String altura = resultSet.getString("height");
            String peso = resultSet.getString("weight");
            System.out.println("ID: " + id);
            System.out.println("Numero: " + numero);
            System.out.println("Nombre: " + nombre);
            System.out.println("Altura: " + altura);
            System.out.println("Peso: " + peso);
            System.out.println();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void pikachu(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            String querySQL = "SELECT * FROM pokedex WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setString(1, "pikachu");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numero = resultSet.getString("num");
                String nombre = resultSet.getString("name");
                String altura = resultSet.getString("height");
                String peso = resultSet.getString("weight");
                System.out.println("ID: " + id);
                System.out.println("Numero: " + numero);
                System.out.println("Nombre: " + nombre);
                System.out.println("Altura: " + altura);
                System.out.println("Peso: " + peso);
                System.out.println();
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
