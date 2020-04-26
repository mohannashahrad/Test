package Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Model.*;

public class Manager {

    protected Storage storage;
    protected Person person;
    protected Customer customer;
    protected Cart cart;
    private ArrayList <Filter> currentFilters;
    private ArrayList <Sort> currentSorts;

    public Manager() {
    }

    public void register (HashMap<String,String> information) throws InvalidException {
        if (!checkValidity(information.get("username")))
            throw new InvalidException("Username is not valid");
        else if (!checkValidity(information.get("password")))
            throw new InvalidException("Password is not valid");
        else if (!checkEmailValidity(information.get("Email")))
            throw new InvalidException("Email is not valid");
        else if (!checkPhoneNumberValidity(information.get("phone")))
            throw new InvalidException("Phone Number is not valid");
        else
            storage.addUser(new Person (information));
    }

    public Person login (String username , String password) throws InvalidException {
         if (!checkValidity(username))
             throw new InvalidException("Username is not valid");
        else if (!checkValidity(password))
             throw new InvalidException("Password is not valid");
        else {
             this.person = storage.getUserByUsername(username);
             return storage.getUserByUsername(username);
         }
    }

    public void logout (){
        this.person = null;
        this.cart = null;
    }

    public boolean checkValidity (String input){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean checkEmailValidity (String input){
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean checkPhoneNumberValidity (String input){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public boolean doesUsernameExist (String username){
        if(storage.getUserByUsername(username) == null)
            return false;
        else return true;
    }

    public boolean doesDiscountExist (String code){
        if (storage.getDiscountByCode(code) == null)
            return false;
        else return true;
    }

    public ArrayList<Category> viewAllCategories (){
        return storage.getAllCategories();
    }

    public ArrayList<Filter> viewAllFilters (){
        return storage.getAllFilters();
    }

    public ArrayList<Sort> viewAllSorts (){
        return storage.getAllSorts();
    }

    public ArrayList<Sale> viewAllOffs (){
        return storage.getAllSales();
    }

    public ArrayList<Filter> getCurrentFilters() {
        return currentFilters;
    }

    public ArrayList<Sort> getCurrentSorts() {
        return currentSorts;
    }

    public Product viewProduct (String productId){
        return storage.getProductById(productId);
    }

    public ArrayList<Comment> showComments (String productId){
        return storage.getProductById(productId).getComments();
    }

    public boolean doesPersonBoughtProduct (Person person , String productId){
        for (Person buyer : storage.getProductById(productId).getThisProductsBuyers()){
            if (buyer.equals(person))
                return true;
        }
        return false;
    }
}

class InvalidException extends Exception {
    public InvalidException(String message) {
        super(message);
    }
}



