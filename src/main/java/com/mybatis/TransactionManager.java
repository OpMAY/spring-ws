package com.mybatis;

import lombok.extern.log4j.Log4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Log4j
public class TransactionManager {
    private DataSourceTransactionManager manager;

    private DefaultTransactionDefinition defaultTransactionDefinition;

    public TransactionManager(DataSourceTransactionManager manager) {
        this.manager = manager;
    }

    public TransactionStatus buildTransactionStatus(String name) {
        this.defaultTransactionDefinition = new DefaultTransactionDefinition();
        this.defaultTransactionDefinition.setName(name);
        this.defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return this.manager.getTransaction(this.defaultTransactionDefinition);
    }

    public void commit(TransactionStatus ts) {
        this.manager.commit(ts);
    }

    public void rollback(TransactionStatus ts) {
        this.manager.rollback(ts);
    }
}
