package electronicstorage.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    public long id;
    public String name;
    public String company;
    public Date timeStamp;
}
