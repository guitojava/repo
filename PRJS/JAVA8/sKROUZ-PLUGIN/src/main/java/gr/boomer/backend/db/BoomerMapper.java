package gr.boomer.backend.db;

import gr.boomer.backend.model.Boomer;
import gr.boomer.backend.service.DateTimeService;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


class BoomerMapper implements RowMapper<Boomer> {
    @Override
    public Boomer map(ResultSet rs, StatementContext ctx) throws SQLException {

        Boomer boomer = new Boomer();
        boomer.setId(rs.getLong("id"));
        boomer.setSearchId(rs.getString("search_id"));
        boomer.setCreationDate(DateTimeService.toLocalDateTime(rs.getString("creation_date")));
        boomer.setSourceId( rs.getString( "source_id" ) );
        boomer.setProductId( rs.getString( "product_id" ) );
        boomer.setProductUrl( rs.getString( "product_url" ) );
        boomer.setCategory( rs.getString( "category" ) );
        boomer.setNetPrice( rs.getBigDecimal( "net_price" ) );
        boomer.setFinalPrice( rs.getBigDecimal( "final_price" ) );
        boomer.setCourierCost( rs.getBigDecimal( "courier_cost" ) );
        boomer.setPayOnDeliveryCost( rs.getBigDecimal( "pay_on_delivery_cost" ) );
        boomer.setShopName( rs.getString( "shop_name" ) );
        boomer.setItemName( rs.getString( "item_name" ) );
        boomer.setSpecs( rs.getString( "specs" ) );
        boomer.setItemUrl( rs.getString( "item_url" ) );
        boomer.setAvailability( rs.getString( "availability" ) );
        boomer.setAreacode( rs.getString( "areacode" ) );
        boomer.setRating( rs.getString( "rating" ) );
        return boomer;
    }
}
