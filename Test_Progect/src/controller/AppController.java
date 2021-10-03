package controller;

import model.ERole;
import model.User;
import service.UserService;
import service.api.IUserService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    private IUserService iUserService;

    public AppController(){
        this.iUserService=UserService.getInstance();

    }
    public void start(){
        try (BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Выберите операцию:\n" +
                    "1-создать пользователя\n" +
                    "2-найти пользователя\n" +
                    "3-внести изменения\n" +
                    "4-посмотреть всех пользователей\n" +
                    "5-удалить пользователя\n" +
                    "6-выйти");
            char a;
                a= (char) bufferedReader.read();
                switch (a){
                    case '1':
                        createUser();
                        break;
                    case '2':
                        getUser();
                        break;
                    case '3':
                        updateUser();
                        break;
                    case '4':
                        getAllUsers();
                        break;
                    case '5':
                        deleteUser();
                        break;
                    default:
                        finish();
                }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения данных! Мы уже разбираемся с этим!");
        }
    }

    public void createUser(){
        try {
            User user = connectUtils();
            boolean isSave = this.iUserService.save(user);
            if (isSave) {
                System.out.println("Пользователь сохранен! Можете выбрать другую операцию или выйти");
            } else {
                System.out.println("Некорректно введен номер телефона или email!");
            }
            start();
        }catch (IOException e){
            throw new IllegalStateException("Ошибка чтения данных! Мы уже разбираемся с этим!");
        }
    }

   public void getUser() {
       try (BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in))){
           System.out.println("Введите email пользовтаеля");
           String email= bufferedReader.readLine();
           User user = this.iUserService.get(email);
           if(user!=null){
               System.out.printf("%s %s\n%s\n",
                       user.getFirstName(),user.getLastName(),user.getEmail());
               for(String number:user.getNumber()){
                   System.out.println(number);
               }
               for(ERole role:user.getRole()){
                   System.out.println(role);
               }System.out.println("----------");
           }else System.out.println("Пользователь с такими данными не зарегистрирован! Попробуйте еще раз");

           start();
       }catch (Exception e){
           throw new IllegalStateException("Ошибка чтения! Мы уже разбираемся с этим!");
       }
   }

   public void updateUser(){
       try (BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in))){
           System.out.println("Введите email пользовтаеля, которого вы хотите редактировать");
           String currentEmail= bufferedReader.readLine();
           User user1 = this.iUserService.get(currentEmail);
           if(user1!=null){
           System.out.println("Новые данные");
           User user = connectUtils();
           boolean update = this.iUserService.update(user, currentEmail);
           if(update) {
               System.out.println("Пользователь успешно отредактирован");
           }else System.out.println("Проверьте корректность номера или email");
           }else System.out.println("Вы не можете редактировать несохраненных пользователей");

           start();
       }catch (Exception e){
           throw new IllegalStateException("Ошибка чтения данных! Мы уже разбираемся с этим!");
       }
   }

   public void getAllUsers(){
       List<User> all = this.iUserService.getAll();
       if(all.size()!=0) {
           for (User user : all) {
               System.out.printf("%s %s\n%s\n",
                       user.getFirstName(),user.getLastName(),user.getEmail());
               for(String number:user.getNumber()){
                   System.out.println(number);
               }
               for(ERole role:user.getRole()){
                   System.out.println(role);
               }System.out.println("----------");
           }
       }else System.out.println("Нет сохраненных пользователей");
       start();
   }

   public void deleteUser(){
       try (BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in))){
           System.out.println("Введите email пользовтаеля, которого вы хотите удалить");
           String currentEmail= bufferedReader.readLine();
           boolean delete = this.iUserService.delete(currentEmail);
           if(delete){
               System.out.println("Пользователь удален!");
           } else System.out.println("Пользователя нет среди сохраненных");
           start();
       }catch (Exception e){
           throw new IllegalStateException("Ошибка чтения данных! Мы уже разбираемся с этим!");
       }
   }

   public void finish(){
       System.out.println("До новых встреч!");
   }

   public User connectUtils()throws IOException{
       User user = new User();
       BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
           System.out.println("Введите имя");
           String firstName = bufferedReader.readLine();
           System.out.println("Введите фамилию");
           String lastName = bufferedReader.readLine();
           System.out.println("Введите email");
           String email = bufferedReader.readLine();
           System.out.println("Выберите роль\n" +
                   "1-SUPER_ADMIN \n"+
                   "2-CUSTOMER + PROVIDER \n" +
                   "3-USER + PROVIDER \n" +
                   "4-CUSTOMER + ADMIN \n" +
                   "5-USER + ADMIN");
           String s = bufferedReader.readLine();
           List<ERole>role=new ArrayList<>();
       switch (s.charAt(0)){
               case '1':
                   role.add(ERole.SUPER_ADMIN);
                   break;
               case '2':
                   role.add(ERole.CUSTOMER);
                   role.add(ERole.PROVIDER);
                   break;
               case '3':
                   role.add(ERole.USER);
                   role.add(ERole.PROVIDER);
                   break;
               case '4':
                  role.add(ERole.CUSTOMER);
                  role.add(ERole.ADMIN);
                   break;
               case '5':
                  role.add(ERole.USER);
                  role.add(ERole.ADMIN);
               default:
                   role.add(ERole.USER);
           }
       List<String> numbers = new ArrayList<>();
           for(int i=1;i<=3;i++){
               System.out.println("Введите номер телефона "+i);
               String number = bufferedReader.readLine();
               if(!number.equals("")){
                   numbers.add(number);
               }
           }
           user.setFirstName(firstName);
           user.setLastName(lastName);
           user.setEmail(email);
           user.setRole(role);
           user.setNumber(numbers);

       return user;
   }
}




