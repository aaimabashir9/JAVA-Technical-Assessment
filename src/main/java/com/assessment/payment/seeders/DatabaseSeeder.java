package com.assessment.payment.seeders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Customer;
import com.assessment.payment.entity.Wallet;
import com.assessment.payment.repository.BalanceRepository;
import com.assessment.payment.repository.CustomerRepository;
import com.assessment.payment.repository.FeeRepository;
import com.assessment.payment.repository.WalletRepository;

/**
 * Created by smatt on 29/06/2017.
 */
@Component
public class DatabaseSeeder {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private BalanceRepository balanceRepository;
    private WalletRepository walletRepository;
    private CustomerRepository customerRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
            BalanceRepository balanceRepository,
            WalletRepository walletRepository,
            CustomerRepository customerRepository,
            JdbcTemplate jdbcTemplate) {
        this.balanceRepository = balanceRepository;
        this.walletRepository = walletRepository;
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedBalanceTable();
        seedWalletTable();
        seedCustomerTable();
    }

    private void seedBalanceTable() {
        String sql = "SELECT * FROM balance b LIMIT 1";
        List<Balance> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs.size() <= 0) {
            Balance b = new Balance(1, 100.0);
            balanceRepository.save(b);
            logger.info("Balance table seeded");
        } else {
            logger.trace("Balance Seeding Not Required");
        }
    }

    private void seedWalletTable() {
        String sql = "SELECT * FROM wallet w LIMIT 1";
        List<Wallet> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        Optional<Balance> b = balanceRepository.findFirstByOrderByIdDesc();
        if (rs.size() <= 0) {
            if (b.isPresent()) {
                Wallet w = new Wallet(1, b.get(), "USD");
                walletRepository.save(w);
                logger.info("Wallet table seeded");
            }
            else {
                logger.trace("Balance not found. Cannot create wallet.");
            }
        } else {
            logger.trace("Wallet Seeding Not Required");
        }
    }

    private void seedCustomerTable() {
        String sql = "SELECT * FROM customer c LIMIT 1";
        List<Customer> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        Optional<Wallet> w = walletRepository.findFirstByOrderByIdDesc();
        if (rs.size() <= 0) {
            if (w.isPresent()) {
                Customer c = new Customer(1, 1, "John Doe");
                customerRepository.save(c);
                logger.info("Customer table seeded");
            }
            else {
                logger.trace("Wallet not found. Cannot create customer");
            }
        } else {
            logger.trace("Customer Seeding Not Required");
        }
    }
}
