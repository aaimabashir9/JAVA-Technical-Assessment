package com.assessment.payment.seeders;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Customer;
import com.assessment.payment.entity.Wallet;
import com.assessment.payment.repository.BalanceRepository;
import com.assessment.payment.repository.CustomerRepository;
import com.assessment.payment.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by aaimabashir9 on 14/08/2024.
 */
@Component
@Slf4j
public class DatabaseSeeder {

    private BalanceRepository balanceRepository;
    private WalletRepository walletRepository;
    private CustomerRepository customerRepository;
    private JdbcTemplate jdbcTemplate;

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
    @Async
    public void seed(ContextRefreshedEvent event) {
        seedBalanceTable();
        seedWalletTable();
        seedCustomerTable();
    }

    private void seedBalanceTable() {
        String sql = "SELECT * FROM balance b LIMIT 1";
        List<Balance> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs.size() <= 0) {
            Balance b = new Balance();
            b.setTotalAmount(100.0);
            balanceRepository.save(b);
            log.info("Balance table seeded");
        } else {
            log.trace("Balance Seeding Not Required");
        }
    }

    private void seedWalletTable() {
        String sql = "SELECT * FROM wallet w LIMIT 1";
        List<Wallet> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        Optional<Balance> b = balanceRepository.findFirstByOrderByIdDesc();
        if (rs.size() <= 0) {
            if (b.isPresent()) {
                Wallet w = new Wallet();
                w.setBalance(b.get());
                w.setCurrency("USD");
                walletRepository.save(w);
                log.info("Wallet table seeded");
            }
            else {
                log.trace("Balance not found. Cannot create wallet.");
            }
        } else {
            log.trace("Wallet Seeding Not Required");
        }
    }

    private void seedCustomerTable() {
        String sql = "SELECT * FROM customer c LIMIT 1";
        List<Customer> rs = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        Optional<Wallet> w = walletRepository.findFirstByOrderByIdDesc();
        if (rs.size() <= 0) {
            if (w.isPresent()) {
                Customer c = new Customer();
                c.setWalletId(w.get().getId());
                c.setName("John Doe");
                customerRepository.save(c);
                log.info("Customer table seeded");
            }
            else {
                log.trace("Wallet not found. Cannot create customer");
            }
        } else {
            log.trace("Customer Seeding Not Required");
        }
    }
}
