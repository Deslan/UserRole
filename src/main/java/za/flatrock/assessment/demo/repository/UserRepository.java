package za.flatrock.assessment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.flatrock.assessment.demo.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByNameAndSurname(String name, String surname);

    @Query(nativeQuery = true, value = "select * from Users users where users.id = :id")
    User findByLongId(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update User_Role userRole set userRole.ROLE_ID = :roleId where userRole.user_Id = :id")
    void updateUserWithRoleId(Long id, Long roleId);

    User findUserByNameAndSurnameAndCellNumber(String name, String surname, String cellNumber);
}
