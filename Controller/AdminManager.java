package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AdminManager extends Manager {

    public AdminManager() {
    }

    public ArrayList<Person> viewAllUsers (){
        return storage.getAllUsers();
    }

    public Person viewUser (String username){
        return storage.getUserByUsername(username);
    }

    public boolean deleteUser (String username){
        if (storage.getUserByUsername(username) == null)
            return false;
        storage.deleteUser(storage.getUserByUsername(username));
        return true;
    }

    public void createManager (HashMap<String,String> information) throws UsernameInvalidException,
            PasswordInvalidException {
        if (!checkValidity(information.get("username")))
            throw new UsernameInvalidException("Username is not valid");
        else if (!checkValidity(information.get("password")))
            throw new PasswordInvalidException("Password is not valid");
        else
            storage.addUser(new Person (information));
    }

    public boolean removeProduct (String productId){
        if(storage.getProductById(productId) == null)
            return false;
        storage.deleteProduct(storage.getProductById(productId));
        return true;
    }

    public ArrayList<Discount> viewAllDiscountCodes (){
        return storage.getAllDiscounts();
    }

    public ArrayList<Request> vieAllRequests (){
        return storage.getAllRequests();
    }

    public Discount viewDiscountCode (String code){
        return storage.getDiscountByCode(code);
    }

    public boolean createDiscountCode (String code , Date startDate , Date endDate){
        if (storage.getDiscountByCode(code).equals(code))
            return false;
        storage.addDiscount(new Discount(code,startDate,endDate));
        return true;
    }

    public boolean removeDiscountCode (String code){
        if (storage.getDiscountByCode(code) == null)
            return false;
        storage.deleteDiscount(storage.getDiscountByCode(code));
        return true;
    }

    public Request viewRequest (String requestId){
        return storage.getRequestById(requestId);
    }

    public boolean removeCategory (String name){
        if (storage.getCategoryByName(name) == null)
            return false;
        storage.deleteCategory(storage.getCategoryByName(name));
        return true;
    }
}
