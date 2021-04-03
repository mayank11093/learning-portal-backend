package learning.portal.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import learning.portal.model.Links;
import learning.portal.model.User;
import lombok.extern.log4j.Log4j2;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableDynamoDBRepositories(basePackages = "learning.portal.repository")
@Log4j2
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Autowired
    private ApplicationContext context;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        System.out.println("AmazonDynamoDB bean");
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }
        return amazonDynamoDB;
    }
    @Bean
    @Primary
    public DynamoDBMapper getDynamoDBMapper(AmazonDynamoDB dynamoDB) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        if (!dynamoDB.listTables().getTableNames().contains("USERS")) {
            log.info("USERS table not found, creating table");
            CreateTableRequest tableRequest = mapper.generateCreateTableRequest(User.class);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            dynamoDB.createTable(tableRequest);
        }
        if (!dynamoDB.listTables().getTableNames().contains("LINKS")) {
            log.info("LINKS table not found, creating table");
            CreateTableRequest tableRequest = mapper.generateCreateTableRequest(Links.class);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            dynamoDB.createTable(tableRequest);
        }
        return mapper;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean(name = "mvcHandlerMappingIntrospectorCustom")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospectorCustom() {
        return new HandlerMappingIntrospector(context);
    }
}
