package com.jinaiya.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.jinaiya.utils.amqp.Sender;
import com.jinaiya.utils.utils.BloomFilters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private Sender sender;

	@Test
	public void hello() throws Exception {
		sender.send();
	}


	@Test
	public void bloomFilterTest(){
		long start = System.currentTimeMillis();
		BloomFilters bloomFilters = new BloomFilters(10000000) ;
		for (int i = 0; i < 10000000; i++) {
			bloomFilters.add(i + "") ;
		}
		Assert.assertTrue(bloomFilters.check(1+""));
		Assert.assertTrue(bloomFilters.check(2+""));
		Assert.assertTrue(bloomFilters.check(3+""));
		Assert.assertTrue(bloomFilters.check(999999+""));
		Assert.assertFalse(bloomFilters.check(400230340+""));
		long end = System.currentTimeMillis();
		System.out.println("执行时间：" + (end - start));
	}

	@Test
	public void guavaTest() {
		long star = System.currentTimeMillis();
		BloomFilter<Integer> filter = BloomFilter.create(
				Funnels.integerFunnel(),
				10000000,
				0.01);

		for (int i = 0; i < 10000000; i++) {
			filter.put(i);
		}

		Assert.assertTrue(filter.mightContain(1));
		Assert.assertTrue(filter.mightContain(2));
		Assert.assertTrue(filter.mightContain(3));
		Assert.assertFalse(filter.mightContain(10000000));
		long end = System.currentTimeMillis();
		System.out.println("执行时间：" + (end - star));
	}

}
