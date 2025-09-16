package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Pasha", "Pavlov", "pavlov@gmail.com");
      User user2 = new User("Egor", "Ivanov", "ivanov@gmail.com");
      User user3 = new User("Ivan", "Burunov", "burunov@gmail.com");
      User user4 = new User("Maks", "Laptin", "laptin@gmail.com");

      Car car1 = new Car("BMW", 763);
      Car car2 = new Car("Lada", 256);
      Car car3 = new Car("KIA", 721);
      Car car4 = new Car("Dachia", 290);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);
      car4.setUser(user4);

      userService.createUser(user1);
      userService.createUser(user2);
      userService.createUser(user3);
      userService.createUser(user4);

      // 1. Пользователи с машинами
      for (User user : userService.retrieveALLUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println(" ");
      }

      // 2. Выбрать пользователя, владеющего машиной (по ее модели и серии)
      System.out.println(userService.findUserByCar("Lada", 256));
      System.out.println(" ");

      // Нет пользователя с такой машиной
      try {
         User notFoundUser = userService.findUserByCar("Ferrari", 920);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println(" ");
      }

      context.close();
   }
}
