/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */
package app.deals.dao;

import app.deals.dao.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DealDao extends JpaRepository<Deal, Integer> {

    @Query("SELECT p FROM Deal p WHERE p.accountNumber = :accountNumber " +
            "AND  p.documentNumber = :documentNumber " +
            "AND  p.documentType = :documentType " +
            "AND  p.documentDate =  :documentDate " +
            "AND  p.senderEntityCode = :senderEntityCode " +
            "ORDER BY p.creationDate DESC "
    )
    List<Deal> find(
            @Param("accountNumber") String accountNumber,
            @Param("documentNumber") String documentNumber,
            @Param("documentType") String documentType,
            @Param("documentDate") Date documentDate,
            @Param("senderEntityCode") String senderEntityCode
    );

    @Query("SELECT p FROM Deal p WHERE p.creationDate <= :offsetDate" )
    List<Deal> findOld(
            @Param("offsetDate") Date offsetDate
    );
}