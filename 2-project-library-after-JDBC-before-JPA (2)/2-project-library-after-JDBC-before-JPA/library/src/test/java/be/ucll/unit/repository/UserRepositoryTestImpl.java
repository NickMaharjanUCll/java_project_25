// package be.ucll.unit.repository;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.function.Function;

// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

// import be.ucll.model.Publication;
// import be.ucll.model.User;
// import be.ucll.repository.UserRepository;

// public class UserRepositoryTestImpl implements UserRepository{
//     public List<User> users;
//     public List<Publication> publications;
    
//     public UserRepositoryTestImpl() {
//         resetData();
//     }

//     public void resetData() {
//         users = new ArrayList<>(
//             List.of(
//                 new User("John Doe", 25, "john.doe@ucll.be", "john1234"),
//                 new User("Jane Toe", 30, "jane.toe@ucll.be", "jane1234"),
//                 new User("Jack Doe", 5, "jack.doe@ucll.be", "jack1234"),
//                 new User("Sarah Doe", 4, "sarah.doe@ucll.be", "sarah1234"),
//                 new User("Birgit Doe", 18, "birgit.doe@ucll.be", "birgit1234")
//             )
//         );
//     }

//     public List<User> allUsers() {
//         return users;
//     }

//     public List<User> usersOlderThan(int age) {
//         List<User> usersOlderThan = new ArrayList<>();
//         for (User user : users) {
//             if (user.getAge() >= age) {
//                 usersOlderThan.add(user);
//             }
//         }
//         return usersOlderThan;
//     }

//     public List<User> usersInAgeRange(int min, int max) {
//         List<User> usersInAgeRange = new ArrayList<>();
//         for (User user : users) {
//             if (user.getAge() >= min && user.getAge() <= max) {
//                 usersInAgeRange.add(user);
//             }
//         }
//         return usersInAgeRange;
//     }

//     public List<User> usersByName(String name){
//         List<User> usersByName = new ArrayList<>();
//         for(User user: users){
//             if(user.getName().toLowerCase().contains(name.toLowerCase())){
//                 usersByName.add(user);
//             }
//         } 
//         return usersByName;
//     }
//     public boolean userExists(String email){
//         for(User user:users){
//             if(user.getEmail().equals(email)){
//                 return true;
//             }
//         }
//         return false;
//     }

//     public User addUser(User user) {
//         users.add(user);
//         return user;
//     }

//     public User updateUser(String email, User userFromRequest) {
//         for (User user : users) {
//             if (user.getEmail().equals(email)) {
//                 user.setName(userFromRequest.getName());
//                 user.setAge(userFromRequest.getAge());
//                 user.setEmail(userFromRequest.getEmail());
//                 user.setPassword(userFromRequest.getPassword());
//             }
//             break;
//         }
//         // The code below is not how we should do it.
//         // for (User user : users) {
//         //     if (user.getEmail().equals(userFromRequest.getEmail())) {
//         //         user = userFromRequest;
//         //         break;           
//         //     }
//         // }
        
//         return userFromRequest;
//     }
//    public String deleteUser(String email){
//         users.removeIf(user -> user.getEmail().equals(email));
//         return "Users deleted successfully.";
//    }

// @Override
// public void flush() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'flush'");
// }

// @Override
// public <S extends User> S saveAndFlush(S entity) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'saveAndFlush'");
// }

// @Override
// public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'saveAllAndFlush'");
// }

// @Override
// public void deleteAllInBatch(Iterable<User> entities) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
// }

// @Override
// public void deleteAllByIdInBatch(Iterable<Long> ids) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAllByIdInBatch'");
// }

// @Override
// public void deleteAllInBatch() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
// }

// @Override
// public User getOne(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'getOne'");
// }

// @Override
// public User getById(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'getById'");
// }

// @Override
// public User getReferenceById(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'getReferenceById'");
// }

// @Override
// public <S extends User> List<S> findAll(Example<S> example) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public <S extends User> List<S> saveAll(Iterable<S> entities) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
// }

// @Override
// public List<User> findAllById(Iterable<Long> ids) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
// }

// @Override
// public <S extends User> S save(S entity) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'save'");
// }

// @Override
// public Optional<User> findById(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findById'");
// }

// @Override
// public boolean existsById(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'existsById'");
// }

// @Override
// public long count() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'count'");
// }

// @Override
// public void deleteById(Long id) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
// }

// @Override
// public void delete(User entity) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'delete'");
// }

// @Override
// public void deleteAllById(Iterable<? extends Long> ids) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
// }

// @Override
// public void deleteAll(Iterable<? extends User> entities) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
// }

// @Override
// public void deleteAll() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
// }

// @Override
// public List<User> findAll(Sort sort) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public Page<User> findAll(Pageable pageable) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public <S extends User> Optional<S> findOne(Example<S> example) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findOne'");
// }

// @Override
// public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public <S extends User> long count(Example<S> example) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'count'");
// }

// @Override
// public <S extends User> boolean exists(Example<S> example) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'exists'");
// }

// @Override
// public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findBy'");
// }

// @Override
// public List<User> findAll() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findAll'");
// }

// @Override
// public List<User> findByAgeGreaterThan(int age) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findByAgeGreaterThan'");
// }

// @Override
// public List<User> findByAgeBetween(int min, int max) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findByAgeBetween'");
// }

// @Override
// public List<User> findByName(String name) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'findByName'");
// }

// @Override
// public boolean existsByEmail(String email) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'existsByEmail'");
// }

// @Override
// public String deleteByEmail(String email) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'deleteByEmail'");
// }
    
// }

// // "Almost finished 'Story 06 - Retrieve all Users' and 'Story 07 - Retrieve all adults'. Teacher has to verify the testing is correct."