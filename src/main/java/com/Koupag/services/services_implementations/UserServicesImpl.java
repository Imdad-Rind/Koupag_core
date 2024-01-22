package com.Koupag.services.services_implementations;

import com.Koupag.execptions.NoSuchUserExist;
import com.Koupag.models.User;
import com.Koupag.repositories.UserRepository;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServicesImpl implements UserService {

    private final UserRepository userRepository;
    private final CitiesServices citiesServices;
    private final LoadingCache<String, User> cache;
    
    @Autowired
    public UserServicesImpl(UserRepository userRepository, CitiesServices citiesServices) {
        this.userRepository = userRepository;
        this.citiesServices = citiesServices;
        
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

   /* @Override
    public Optional<User> getUserByUserName(String username) {

    }*/
    
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


}
