package com.jvasquez.persistence.repository;

import com.jvasquez.persistence.util.DataConstants;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by ricco on 30/08/2015.
 */
public class BasicRepository<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<T> clazz;

    public BasicRepository() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> getAllCollection() {
        return mongoTemplate.findAll(clazz);
    }

    public void executeListProcedure(String procedureName, String... parameters) {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();
        scriptOps.call(procedureName, parameters);
    }

    public List<T> getAllCollection(int startPagination, int maxItemsNumber) {

        Query query = new Query();
        query.skip(startPagination);
        query.limit(maxItemsNumber);

        return mongoTemplate.find(query, clazz);
    }

    public List<T> getAllCollection(Query query) {

        return mongoTemplate.find(query, clazz);
    }

    public Long getTotalQuantity() {

        return mongoTemplate.count(new Query().limit(1), clazz);
    }

    public Long getTotalQuantity(Query query) {

        return mongoTemplate.count(query, clazz);
    }

    public T getById(String id) {
        return mongoTemplate.findById(id, clazz);
    }

    public T getObjectByAttributes(String[] attributes, String[] values) {
        Query searchQuery = new Query();
        int x = 0;
        for (String attribute : attributes) {
            searchQuery.addCriteria(Criteria.where(attribute).is(values[x]));
            x++;
        }

        return mongoTemplate.findOne(searchQuery, clazz);
    }

    public List<T> getCollectionByAttributes(String[] attributes, String[] values) {
        Query searchQuery = new Query();
        int x = 0;
        for (String attribute : attributes) {
            searchQuery.addCriteria(Criteria.where(attribute).is(values[x]));
            x++;
        }

        return mongoTemplate.find(searchQuery, clazz);
    }

    public void insertObject(T object) {
        mongoTemplate.insert(object);
    }

    public void insertCollection(List<T> collection) {
        mongoTemplate.insert(collection, clazz);
    }

    public void updateFieldsById(String[] fields, String[] values, String id) {
        Query searchQuery = new Query(Criteria.where("_id").is(id));
        Update update = new Update();

        int index = 0;
        for (String field : fields) {
            update.set(field, values[index]);
            index++;
        }

        mongoTemplate.updateFirst(searchQuery, update, clazz);
    }

    public void removeObject(T object) {
        mongoTemplate.remove(object);
        mongoTemplate.executeCommand(new BasicDBObject(DataConstants.REPAIR_DATABASE, 1));
    }

    public void removeByQuery(Query query) {
        mongoTemplate.findAllAndRemove(query, clazz);
        mongoTemplate.executeCommand(new BasicDBObject(DataConstants.REPAIR_DATABASE, 1));
    }

    public void removeCollection() {
        mongoTemplate.getCollection(mongoTemplate.getCollectionName(clazz)).drop();
        mongoTemplate.executeCommand(new BasicDBObject(DataConstants.REPAIR_DATABASE, 1));
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
