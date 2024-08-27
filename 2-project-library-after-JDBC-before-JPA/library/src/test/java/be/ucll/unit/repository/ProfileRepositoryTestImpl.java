// package be.ucll.unit.repository;

// import java.util.List;
// import java.util.Optional;
// import java.util.function.Function;

// import org.springframework.data.domain.Example;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

// import be.ucll.model.Profile;
// import be.ucll.repository.ProfileRepository;

// public class ProfileRepositoryTestImpl implements ProfileRepository {
    

//     public ProfileRepositoryTestImpl(){}

//     @Override
//     public void flush() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'flush'");
//     }

//     @Override
//     public <S extends Profile> S saveAndFlush(S entity) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'saveAndFlush'");
//     }

//     @Override
//     public <S extends Profile> List<S> saveAllAndFlush(Iterable<S> entities) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'saveAllAndFlush'");
//     }

//     @Override
//     public void deleteAllInBatch(Iterable<Profile> entities) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
//     }

//     @Override
//     public void deleteAllByIdInBatch(Iterable<Long> ids) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAllByIdInBatch'");
//     }

//     @Override
//     public void deleteAllInBatch() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAllInBatch'");
//     }

//     @Override
//     public Profile getOne(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getOne'");
//     }

//     @Override
//     public Profile getById(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getById'");
//     }

//     @Override
//     public Profile getReferenceById(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getReferenceById'");
//     }

//     @Override
//     public <S extends Profile> List<S> findAll(Example<S> example) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public <S extends Profile> List<S> findAll(Example<S> example, Sort sort) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public <S extends Profile> List<S> saveAll(Iterable<S> entities) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
//     }

//     @Override
//     public List<Profile> findAll() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public List<Profile> findAllById(Iterable<Long> ids) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
//     }

//     @Override
//     public <S extends Profile> S save(S entity) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'save'");
//     }

//     @Override
//     public Optional<Profile> findById(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findById'");
//     }

//     @Override
//     public boolean existsById(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'existsById'");
//     }

//     @Override
//     public long count() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'count'");
//     }

//     @Override
//     public void deleteById(Long id) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
//     }

//     @Override
//     public void delete(Profile entity) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'delete'");
//     }

//     @Override
//     public void deleteAllById(Iterable<? extends Long> ids) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
//     }

//     @Override
//     public void deleteAll(Iterable<? extends Profile> entities) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
//     }

//     @Override
//     public void deleteAll() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
//     }

//     @Override
//     public List<Profile> findAll(Sort sort) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public Page<Profile> findAll(Pageable pageable) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public <S extends Profile> Optional<S> findOne(Example<S> example) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findOne'");
//     }

//     @Override
//     public <S extends Profile> Page<S> findAll(Example<S> example, Pageable pageable) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findAll'");
//     }

//     @Override
//     public <S extends Profile> long count(Example<S> example) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'count'");
//     }

//     @Override
//     public <S extends Profile> boolean exists(Example<S> example) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'exists'");
//     }

//     @Override
//     public <S extends Profile, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'findBy'");
//     }
// }
