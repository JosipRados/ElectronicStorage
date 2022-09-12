package electronicstorage.BussinesLogic.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    public long id;
    public String name;
    public String company;
    public Date timeStamp;
}
