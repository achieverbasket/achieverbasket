package achieversbasket2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ab.spring.application.config.SpringApp;
import com.ab.spring.dao.impl.SequenceDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringApp.class)
@WebAppConfiguration
public class SequenceDaoTest {
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Test
	public void testDao() {
		Long firstValue = sequenceDao.getNextVal("TEST_SEQ");
		System.out.println(firstValue);
		Assert.assertEquals(firstValue + 1, sequenceDao.getNextVal("TEST_SEQ"));
	}
}
