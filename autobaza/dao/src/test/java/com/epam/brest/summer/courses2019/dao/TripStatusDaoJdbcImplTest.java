package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.TripStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Transactional
@Rollback
public class TripStatusDaoJdbcImplTest {

    @Autowired
    TripStatusDao tripStatusDao;

    @Test
    public void findAll() {
        List<TripStatus> tripStatuses = tripStatusDao.findAll();
        assertNotNull(tripStatuses);
        assertTrue(tripStatuses.size() > 0);
    }
}
