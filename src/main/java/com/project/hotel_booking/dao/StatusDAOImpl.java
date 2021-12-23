package com.project.hotel_booking.dao;

import com.project.hotel_booking.entity.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class StatusDAOImpl implements StatusDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Status findById(int statusId) {

        Session session=entityManager.unwrap(Session.class);
        Query<Status> statusQuery= session.createQuery("from Status where id=:statusId",Status.class);

        statusQuery.setParameter("statusId",statusId);


        return statusQuery.getSingleResult();
    }


}
