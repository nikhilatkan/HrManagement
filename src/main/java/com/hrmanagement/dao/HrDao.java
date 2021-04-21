package com.hrmanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hrmanagement.entities.HrEntity;

@Component
public class HrDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    public void addUser(HrEntity user) {
        this.hibernateTemplate.save(user);
    }

    public HrEntity getUser(String username) {
        return this.hibernateTemplate.get(HrEntity.class, username);
    }

}
