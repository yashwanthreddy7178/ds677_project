package com.hackernews;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackernews.post.PostController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HackerNewsApplicationTests {

	@Test
	public void contextLoads() {
		PostController postcontroller = new PostController();
		String res = postcontroller.home();
		assertEquals("Hello World! this is OAuth2", res);
	}

}
