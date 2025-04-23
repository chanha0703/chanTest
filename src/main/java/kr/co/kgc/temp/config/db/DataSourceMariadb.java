package kr.co.kgc.temp.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="kr.co.kgc.temp.biz.**.dao")
@EnableTransactionManagement
public class DataSourceMariadb {
  
  @Qualifier("dataSource")
  private final DataSource dataSource;
  
  @Autowired
  public DataSourceMariadb(DataSource dataSource) {
    this.dataSource = dataSource;
  }
  
  /* -----------------mybatis 셋팅------------------------------------- */
  @Primary
  @Bean(name = "SqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("dataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/**/*Mapper.xml"));
    sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mapper/mybatis-config.xml"));
    return sessionFactoryBean.getObject();
  }
  
  @Primary
  @Bean(name = "SqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Autowired@Qualifier("SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
  
}