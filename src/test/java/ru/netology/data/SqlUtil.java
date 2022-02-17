package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtil {

    private SqlUtil() {
    }


    public static Connection getConnection() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    public static String getPaymentId() throws SQLException {
        String payment_id = null;
        val idSQL = "SELECT payment_id FROM order_entity order by created desc limit 1;";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(idSQL)) {
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    payment_id = rs.getString("payment_id");
                }
            }
        }
        return payment_id;
    }

    public static String getStatusDebitCard(String payment_id) throws SQLException {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, payment_id);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }


    public static String getStatusCreditCard(String payment_id) throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, payment_id);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    public static String getPaymentAmount(String payment_id) throws SQLException {
        String amountSQL = "SELECT amount FROM payment_entity WHERE transaction_id =?; ";
        String amount = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(amountSQL)) {
            statusStmt.setString(1, payment_id);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
            }
        }
        return amount;
    }

//    public static void cleanData() throws SQLException {
//        val pays = "DELETE FROM payment_entity";
//        val credits = "DELETE FROM credit_request_entity";
//        val orders = "DELETE FROM order_entity";
//        try (val conn = SqlUtil.getConnection();
//             val prepareStatPay = conn.createStatement();
//             val prepareStatCredit = conn.createStatement();
//             val prepareStatOrder = conn.createStatement();
//        ) {
//            prepareStatPay.executeUpdate(pays);
//            prepareStatCredit.executeUpdate(credits);
//            prepareStatOrder.executeUpdate(orders);
//        }
//    }
}

