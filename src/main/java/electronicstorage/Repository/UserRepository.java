package electronicstorage.Repository;

import electronicstorage.BussinesLogic.Models.RoleDTO;
import electronicstorage.BussinesLogic.Models.UserDTO;

import java.util.List;

public interface UserRepository {
    List<RoleDTO> GetAllRoles();

    RoleDTO GetRole(long roleId);

    UserDTO GetUser(String username);
}
