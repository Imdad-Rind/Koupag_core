package com.Koupag.services.services_implementations;

import com.Koupag.models.Cities;
import com.Koupag.models.User;
import com.Koupag.models.UserProfile;
import com.Koupag.repositories.UserProfileRepository;
import com.Koupag.repositories.UserRepository;
import com.Koupag.services.CitiesServices;
import com.Koupag.services.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServicesImpl implements UserService {

    private final UserRepository userRepository;
    private final CitiesServices citiesServices;
    private final UserProfileRepository userProfileRepository;
    private final LoadingCache<String, User> cache;
    
    @Autowired
    public UserServicesImpl(UserRepository userRepository, CitiesServices citiesServices, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.citiesServices = citiesServices;
        this.userProfileRepository = userProfileRepository;
        
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
        return userRepository.findByUsername(username);
    }*/
    
    @Override
    public Optional<User> getUserByCNIC(String cnic) {
        return Optional.ofNullable(userRepository.findByCNIC(cnic));
    }
    
//    @Override
//    public Boolean existsByUsername(String username) {
//        return userRepository.existsByUsername(username);
//    }
//
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
    public void createUserProfileByUserId(UserProfile userProfile, Long id) {
        Long cityId = userProfile.getAddress().getCity().getId();
        Cities cityOfUser = citiesServices.getCityById(cityId);
        User userToBeUpdated = userRepository.getReferenceById(id);
        userToBeUpdated.setUserProfile(userProfile);
        userProfile.getAddress().setCity(cityOfUser);
        userProfile.setUser(userToBeUpdated);
        userProfileRepository.save(userProfile);
    }
    
    
    
}
