/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.dao.entity;

import app.deals.pojo.DealDTO;
import app.sys.errorhandling.ErrorMessageCodes;
import app.sys.errorhandling.ServiceException;
import app.sys.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.modelmapper.*;
import org.modelmapper.Converter;

import javax.persistence.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "deal")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private String documentNumber;

    @Column
    private String documentType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column
    private Date documentDate;

    @Column
    private String senderEntityCode;

    @Column
    private BigDecimal openBalance;

    @Column
    private BigDecimal financeCharges;

    @Column
    private BigDecimal totalDue;

    @Column
    private String payments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    // yyyy-MM-dd'T'HH:mm:ss.SSSXXX
    @Column
    private Timestamp creationDate;

    public static Deal mapper(DealDTO dealDTO) throws ServiceException {
        Date docDate = DateUtils.toDate(dealDTO.getDocumentDate(), "", Response.Status.INTERNAL_SERVER_ERROR, ErrorMessageCodes.SysError1002);
//        Converter<String, Date> docDateConverter2 = new AbstractConverter<String, Date>() {
//            @Override
//            protected Date convert(String source) {
//                return docDate;
//            }
//        };
        Converter<String, Date> docDateConverter =  ctx -> docDate;
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<DealDTO, Deal> typeMap =  modelMapper.createTypeMap(DealDTO.class, Deal.class);
        typeMap.addMappings(mapper -> mapper.using(docDateConverter).map(DealDTO::getDocumentDate, Deal::setDocumentDate ));
        Deal deal = modelMapper.map(dealDTO, Deal.class);
        return deal;
    }

}