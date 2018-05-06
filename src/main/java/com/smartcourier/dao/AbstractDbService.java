package com.smartcourier.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AbstractDbService {

	@PersistenceContext
	private EntityManager entityManager;

	public Object findById(Class<? extends Object> entity ,String id) {
		return entityManager.find(entity, id);
	}
	
	public Boolean add(Object entity) {
		//if(entityManager.find(entity.getClass(), id) == null){
			entityManager.persist(entity);
			return true;
		//}
		//return false;
	}

	public Boolean remove(Object entity, String id) {
		if(entityManager.find(entity.getClass(), id) != null){
			entityManager.remove(entity);
			return true;
		}
		return false;
	}
	
    public List<? extends Object> findByAttributes(Class<? extends Object> entityClass, Map<String, String> attributes) {
        List<? extends Object> results;
        String sqlSerarchPrefix = "%", sqlSerarchSuffix = "%";
        sqlSerarchPrefix = "";
        sqlSerarchSuffix = "";
        
        //set up the Criteria query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<? extends Object> cq = cb.createQuery(entityClass);
        Root<? extends Object> a = cq.from(entityClass);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for(String s : attributes.keySet())
        {
            if(a.get(s) != null){
                predicates.add(cb.like((Expression) a.get(s), sqlSerarchPrefix + attributes.get(s) + sqlSerarchSuffix ));
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<? extends Object> q = entityManager.createQuery(cq);
 
        results = q.getResultList();
        return results;
    }
	

}