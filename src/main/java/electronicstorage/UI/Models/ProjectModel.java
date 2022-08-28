package electronicstorage.UI.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    public long id;
    public String name;
    public String company;
    public Date timeStamp;
}
