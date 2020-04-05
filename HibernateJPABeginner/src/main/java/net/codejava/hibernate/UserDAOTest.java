package net.codejava.hibernate;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOTest {
	public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
//        User newUser = new User();
//        newUser.setEmail("billjoy@gmail.com");
//        newUser.setFullname("bill Joy");
//        newUser.setPassword("billi");
         
       
//        to show particular data of the table
        String sql = "SELECT u from User u where u.email = 'gunjika.gla_mca17'";
        Query query = entityManager.createQuery(sql);  
       User user = null;
        try{
        	 user = (User) query.getSingleResult();
        }
        catch (NoResultException nre){
        }

        if(user == null){
        	 System.out.println(user.getEmail());
             System.out.println(user.getFullname());
             System.out.println(user.getPassword());
             
             entityManager.persist(user);
             
             entityManager.getTransaction().commit();
              
             entityManager.close();
             factory.close();
        
        }
//        create new data
        User newUser = new User();
        newUser.setEmail("billjoy@gmail.com");
        newUser.setFullname("bill Joy");
        newUser.setPassword("billi");
         
        entityManager.persist(newUser);
        
//        update exixting data
        User existingUser = new User();
        existingUser.setId(101);
        existingUser.setEmail("bill.joy@gmail.com");
        existingUser.setFullname("Bill Joy");
        existingUser.setPassword("billionaire");
         
        entityManager.merge(existingUser);
        
//        remove the data
        Integer primaryKey = 1;
        User reference = entityManager.getReference(User.class, primaryKey);
        entityManager.remove(reference);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        factory.close();
    }

}
