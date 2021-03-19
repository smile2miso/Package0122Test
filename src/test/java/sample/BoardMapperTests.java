package sample;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardMapperTests {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testSqlSession() throws Exception{
		System.out.println(sqlSession.toString());
	}
}