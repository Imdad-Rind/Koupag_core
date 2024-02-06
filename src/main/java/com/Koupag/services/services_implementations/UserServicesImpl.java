package com.Koupag.services.services_implementations;

import com.Koupag.execptions.OldPasswordDoNotMatch;
import com.Koupag.execptions.UserNotFoundException;
import com.Koupag.models.Notification;
import com.Koupag.models.User;
import com.Koupag.repositories.NotificationRepository;
import com.Koupag.repositories.UserRepository;
import com.Koupag.services.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServicesImpl implements UserService {


    private final UserRepository userRepository;
    private final LoadingCache<String, User> cache;
    private final NotificationRepository notificationRepository;
    private PasswordEncoder encoder;


    
    @Autowired
    public UserServicesImpl(UserRepository userRepository, NotificationRepository notificationRepository) {

        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;

        cache = CacheBuilder.newBuilder()
                        .expireAfterAccess(20, TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build(new CacheLoader<String, User>() {
                            @Override
                            public User load(String email) throws Exception
                            {
                                return getUserByEmail(email);
                            }
                        });
    }

    @Override
    public User creteNewUser(User user) {
        userRepository.save(user);
        return user;
    }
    
    @Override
    public Optional<User> getUserByCNIC(String cnic) {
        return Optional.ofNullable(userRepository.findByCNIC(cnic));
    }
    
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void cacheNewUser(String email, User user) {
        cache.put(email, user);
    }
    
    @Override
    public User getCachedUser(String email) {
        return cache.getIfPresent(email);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Void updateUserPassword(UUID id, String newPass) {
        if (userRepository.findById(id).isPresent()){

            User u = userRepository.getReferenceById(id);
            u.setPassword(newPass);
            userRepository.save(u);

        }else {
            throw new UserNotFoundException("Request to Update password of user with id : "+id+" Not Found :: Error thrown From Services");
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(UUID Id) {
        return userRepository.findById(Id);
    }

    @Override
    public void updateUserById(UUID id, User user) {
        User userToBeUpdated = userRepository.getReferenceById(id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setCNIC(user.getCNIC());
        userToBeUpdated.setUserType(user.getUserType());
        userToBeUpdated.setPhoneNumber(user.getPhoneNumber());
        userToBeUpdated.setAddress(user.getAddress());

        userRepository.save(userToBeUpdated);


    }

    @Override
    public List<Notification> getUserNotifications(UUID userId) {
        return notificationRepository.findLatestNotificationsByUserId(userId);
    }

    @Override
    public void deleteUserNotification(UUID notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void deleteUserByID(UUID id) {
        userRepository.deleteById(id);
    }


}
