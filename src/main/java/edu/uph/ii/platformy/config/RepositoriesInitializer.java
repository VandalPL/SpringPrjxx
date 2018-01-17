package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {

            if(roomTypeRepository.findAll().isEmpty()) {//przyjmijmy, że jeśli repozytorium typów jest puste, to trzeba zainicjalizować bazę danych


            /*
                RoomType vt = new RoomType("samochód osobowy");
                roomTypeRepository.save(vt);
                roomTypeRepository.save(new RoomType("samochód ciężarowy"));
                roomTypeRepository.save(new RoomType("motocykl"));
                roomTypeRepository.save(new RoomType("skuter"));

               Room v1 =
                        new Room(
                                "Alfa Romeo",
                                "Giulietta",
                                46900f,
                                new Date(110, 6, 1),
                                vt, new Date(117, 7, 22, 4, 32, 34));
              //  roomRepository.save(v1);

                Room v2 = new Room();
                v2.setName("Fiat");
                v2.setModel("testowy");
                v2.setProductionDate(new Date(107, 3, 21));
                v2.setPrice(18500f);
                v2.setRoomType(vt);
                v2.setCreatedDate(new Date());
              //  roomRepository.save(v2);

                Room v3 = new Room();
                v3.setName("Honda");
                v3.setModel("Civic VII");
                v3.setProductionDate(new Date(104, 8, 16));
                v3.setPrice(17400f);
                v3.setRoomType(vt);
                v3.setCreatedDate(new Date());
               // roomRepository.save(v3);

                Room v4 = new Room();
                v4.setName("Volvo");
                v4.setModel("C30");
                v4.setProductionDate(new Date(110, 3, 26));
                v4.setPrice(39500f);
                v4.setRoomType(vt);
                v4.setCreatedDate(new Date());
               // roomRepository.save(v4);*/
            }

            if(accessoryRepository.findAll().isEmpty() == true){

                accessoryRepository.save(new Accessory("Szyberdach"));
                accessoryRepository.save(new Accessory("Elektronicznie opuszczane szyby"));
                accessoryRepository.save(new Accessory("Wspomaganie kierownicy"));
                accessoryRepository.save(new Accessory("System kontroli trakcji"));

            }

            if(roleRepository.findAll().isEmpty() == true){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
