package sample.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSourse() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSourse) throws Exception{
		//SqlSessionFactoryBean? MyBatis 설정파일을 바탕으로 SqlSessionFactory를 생성하는 역할
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//데이터베이스 연동하는 설정부분을 SqlSessionFactoryBean
		//의 멤버변수 dataSource에 대입한다.
		sqlSessionFactoryBean.setDataSource(dataSourse);
		//매퍼파일의 위치를 지정
		//src/main/resources의 mapper 폴더포함 하위폴더 전체 파일중
		//sql-로 시작하는 xml 파일은 Mapper 파일이다 라는 선언
		sqlSessionFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mapper/**/test-*.xml"));

		// 카멜 표기법과 스네이크표기법의 매핑한 mybatis 설정값을 빈에 등록
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());

		return sqlSessionFactoryBean.getObject();
	}
	
	//매퍼의 핵심적인 역할을 수행하는 클래스로 SQL 실행이나 트랜잭션 관리를 한다.
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	//application.properties 파일에서 mybatis설정만 가져온다.
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
}
