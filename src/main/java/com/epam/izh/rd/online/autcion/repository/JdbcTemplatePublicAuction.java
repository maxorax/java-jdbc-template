package com.epam.izh.rd.online.autcion.repository;

import com.epam.izh.rd.online.autcion.entity.Bid;
import com.epam.izh.rd.online.autcion.entity.Item;
import com.epam.izh.rd.online.autcion.entity.User;
import com.epam.izh.rd.online.autcion.mappers.BidMapper;
import com.epam.izh.rd.online.autcion.mappers.ItemMapper;
import com.epam.izh.rd.online.autcion.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

@Repository
public class JdbcTemplatePublicAuction implements PublicAuction {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePublicAuction(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bid> getUserBids(long id) {


        return  jdbcTemplate.query("SELECT * FROM bids WHERE user_id = ? ", new Object[]{id}, new BidMapper());
    }

    @Override
    public List<Item> getUserItems(long id) {
        return jdbcTemplate.query("SELECT * FROM items WHERE user_id = ? ", new Object[]{id}, new ItemMapper());
    }

    @Override
    public Item getItemByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE title LIKE ?", new String[]{name}, new ItemMapper());
    }

    @Override
    public Item getItemByDescription(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE description LIKE ?", new String[]{name}, new ItemMapper());
    }

    @Override
    public Map<User, Double> getAvgItemCost() {
        Map<User, Double> map = new HashMap<>();
        List<User> user = jdbcTemplate.query("SELECT * FROM users ", new UserMapper());
        for (User e: user) {
           List<Item> item = jdbcTemplate.query("SELECT* FROM items WHERE user_id = ? ",new Long[]{e.getUserId()},new ItemMapper());
           double sum = 0;
           int i=0;
           for(Item z: item){
               sum+= z.getStartPrice();
               i++;
           }
           if(sum != 0)
           map.put(e,sum/i);
        }
        return map;
    }

    @Override
    public Map<Item, Bid> getMaxBidsForEveryItem() {
        Map<Item, Bid> map= new HashMap<>();

          List<Item> item =  jdbcTemplate.query("SELECT * FROM items",new ItemMapper());
          for(Item e: item) {
              List<Bid> bid = jdbcTemplate.query("SELECT * FROM bids WHERE item_id = ? ", new Long[]{e.getItemId()}, new BidMapper());
              Bid max = null;
              double maxBidValue = -1;
              for (Bid z : bid) {
                  if (maxBidValue < z.getBidValue()) {
                      max = z;
                      maxBidValue = z.getBidValue();
                  }
              }
              if(max!= null) {
                  map.put(e, max);
              }
          }
        return map;
        }

    @Override
    public boolean createUser(User user) {
            return jdbcTemplate.update("INSERT INTO users VALUES(?,?,?,?,?)",
                    user.getUserId(), user.getBillingAddress(), user.getFullName(), user.getLogin(), user.getPassword()) == 1;
    }

    @Override
    public boolean createItem(Item item) {
        return jdbcTemplate.update("INSERT INTO items VALUES(?,?,?,?,?,?,?,?,?)",
                item.getItemId(),item.getBidIncrement(),item.getBuyItNow(),item.getDescription(),item.getStartDate(),
                item.getStartPrice(),item.getStopDate(),item.getTitle(),item.getUserId()) == 1;
    }

    @Override
    public boolean createBid(Bid bid) {
        return jdbcTemplate.update("INSERT INTO bids VALUES(?,?,?,?,?)",
                bid.getBidId(),bid.getBidDate(),bid.getBidValue(),bid.getItemId(),bid.getUserId()) == 1;
    }

    @Override
    public boolean deleteUserBids(long id) {
        return jdbcTemplate.update("DELETE FROM bids WHERE user_id = ?",id) == 1;
    }

    @Override
    public boolean doubleItemsStartPrice(long id) {
        return jdbcTemplate.update("UPDATE items SET start_price = start_price * 2 WHERE user_id = ?",id) == 1;
    }
}
