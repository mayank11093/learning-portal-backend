package learning.portal.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "LINKS")
public class Links {
    private String id;
    private String topic;
    private String links;

    public Links(String id, String topic, String links) {
        this.id = id;
        this.topic = topic;
        this.links = links;
    }

    public Links() {
    }

    @DynamoDBHashKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @DynamoDBAttribute
    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
