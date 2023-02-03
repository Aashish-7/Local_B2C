package com.b2c.Local.B2C.springevent;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Log4j2
public class HibernateSearchIndexed implements ApplicationListener<ContextRefreshedEvent> {


    private EntityManager entityManager;

    @Autowired
    public HibernateSearchIndexed(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SearchSession searchSession = Search.session(entityManager);
        MassIndexer indexer = searchSession.massIndexer();
        indexer.purgeAllOnStart(true);
        indexer.batchSizeToLoadObjects(100).idFetchSize(25);
        indexer.startAndWait();
        log.info("Completed Indexing....");
    }
}
