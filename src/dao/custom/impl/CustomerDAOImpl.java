package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT cusId FROM Customer ORDER BY cusId DESC LIMIT 1");
        if (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            id = ++id;
            if (id < 9) {
                return "C-00" + id;
            } else if (id < 99) {
                return "C-0" + id;
            } else {
                return "C-" + id;
            }
        }
        return "C-001";
    }

    @Override
    public ArrayList<String> getAllCustomersId() throws SQLException, ClassNotFoundException {

        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT cusId FROM Customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (resultSet.next()) {
            customerIds.add(resultSet.getString(1));
        }
        return customerIds;*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT cusId FROM Customer";
        Query query = session.createQuery(hql);
        ArrayList<String> list= (ArrayList<String>) query.list();
        return list;


    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {

        /*return (boolean) CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)", customer.getCusId(), customer.getCusTitle(), customer.getCusName(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostCode());*/
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Customer object) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return customers;*/

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Customer";
        Query query = session.createQuery(hql);
        ArrayList<Customer> list= (ArrayList<Customer>) query.list();
        return list;


    }

    @Override
    public Customer get(String id) throws SQLException, ClassNotFoundException {

        /*ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM Customer WHERE custId=?",id);
        Customer customer = null;
        if (resultSet.next()) {
            customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return customer;*/

        Transaction transaction=null;
        Session session=null;
        try{
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, id);
            transaction.commit();
            return customer;
        }catch (Exception exception){
            exception.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }


    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        /*return (boolean) CrudUtil.execute("DELETE FROM Customer WHERE custId=?",id);*/

        Session session=null;
        Transaction transaction=null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.delete(session.load(Customer.class,id));

            transaction.commit();
            return true;
        }catch (Exception exception){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }




    }

}
