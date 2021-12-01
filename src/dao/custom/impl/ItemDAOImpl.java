package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Integer.parseInt(resultSet.getString(4)),
                    Double.parseDouble(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7)),
                    Double.parseDouble(resultSet.getString(8))
            ));
        }
        return items;*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Item";
        Query query = session.createQuery(hql);
        ArrayList<Item> items = (ArrayList<Item>) query.list();
        return items;

    }

    @Override
    public ArrayList<String> getAllItemsId() throws SQLException, ClassNotFoundException {


        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT itemCode FROM Item");
        ArrayList<String> itemIds = new ArrayList<>();
        while (resultSet.next()) {
            itemIds.add(resultSet.getString(1));
        }
        return itemIds;*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT itemCode FROM Item";
        Query query = session.createQuery(hql);
        ArrayList<String> list= (ArrayList<String>) query.list();
        return list;

    }

    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {

        /*return (boolean) CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)", item.getItemCode(), item.getDescription(), item.getPackSize(), item.getQtyOnHand(), item.getUnitPrice(), item.getDiscount(), item.getEveryItem(), item.getMaxDiscount());*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public Item get(String id) throws SQLException, ClassNotFoundException {

        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Item WHERE itemCode=?",id);
        Item item = null;
        if (resultSet.next()) {
            item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Integer.parseInt(resultSet.getString(4)),
                    Double.parseDouble(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7)),
                    Double.parseDouble(resultSet.getString(8))
            );
        }
        return item;*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, id);
        transaction.commit();
        session.close();
        return item;

    }

    @Override
    public boolean update(Item item, String actualId) throws SQLException, ClassNotFoundException {

       /*return (boolean) CrudUtil.execute("UPDATE Item SET itemCode=?, description=?, packSize=?, qtyOnHand=?, unitPrice=?, discount=?, everyItem=?, maxDiscount=? WHERE itemCode=?", item.getItemCode(), item.getDescription(), item.getPackSize(), item.getQtyOnHand(), item.getUnitPrice(), item.getDiscount(), item.getEveryItem(), item.getMaxDiscount(),actualId);*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Item SET itemCode=:itemCode, description=:description, packSize=:packSize, qtyOnHand=:qtyOnHand, unitPrice=:unitPrice, discount=:discount, everyItem=:everyItem, maxDiscount=:maxDiscount WHERE itemCode=:itemCode";
        Query query = session.createQuery(hql);
        query.setParameter("itemCode",item.getItemCode());
        query.setParameter("description",item.getDescription());
        query.setParameter("packSize",item.getPackSize());
        query.setParameter("qtyOnHand",item.getQtyOnHand());
        query.setParameter("unitPrice",item.getUnitPrice());
        query.setParameter("discount",item.getDiscount());
        query.setParameter("everyItem",item.getEveryItem());
        query.setParameter("maxDiscount",item.getMaxDiscount());
        query.setParameter("itemCode",actualId);

        if (query.executeUpdate()>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }





    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        /*return (boolean) CrudUtil.execute("UPDATE Item SET qtyOnHand=? WHERE itemCode=?",item.getQtyOnHand(),item.getItemCode());*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Item SET qtyOnHand=:qtyOnHand WHERE itemCode=:itemCode";
        Query query = session.createQuery(hql);
        query.setParameter("qtyOnHand",item.getQtyOnHand());
        query.setParameter("itemCode",item.getItemCode());

        if (query.executeUpdate()>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }

    }

    @Override
    public ArrayList<Item> getItemLike(String itemId) throws SQLException, ClassNotFoundException {
        /*ArrayList<Item> items = new ArrayList<>();

        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Item WHERE itemCode LIKE ?",itemId+"%");
        while (resultSet.next()) {
            items.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Integer.parseInt(resultSet.getString(4)),
                    Double.parseDouble(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7)),
                    Double.parseDouble(resultSet.getString(8))
            ));
        }
        return items;*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Item WHERE itemCode LIKE :itemCode";
        Query query = session.createQuery(hql);
        ArrayList<Item> items = (ArrayList<Item>) query.list();

        transaction.commit();
        session.close();

        return items;

    }

    @Override
    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {

        /*return (boolean) CrudUtil.execute("DELETE FROM Item WHERE itemCode=?", itemId);*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Item.class,itemId));
            transaction.commit();
            return true;
        }catch (Exception exception){
            return false;
        }finally {
            session.close();
        }


    }
}
